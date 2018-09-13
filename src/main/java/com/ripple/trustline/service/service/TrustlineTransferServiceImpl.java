package com.ripple.trustline.service.service;


import com.ripple.trustline.dto.TransferRequest;
import com.ripple.trustline.dto.TransferResponse;
import com.ripple.trustline.dto.TransferStatus;
import com.ripple.trustline.exception.TrustlineException;
import com.ripple.trustline.model.Account;
import com.ripple.trustline.model.Transaction;
import com.ripple.trustline.spi.TrustlineTransferClient;
import com.ripple.trustline.spi.TrustlineTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author vreddy
 */

@Component
public class TrustlineTransferServiceImpl implements TrustlineTransferService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountRepositoryService accountRepositoryService;

    @Value("${trustline.account.id}")
    private UUID accountId;

    @Qualifier("transfer_client")
    @Autowired
    private TrustlineTransferClient trustlineTransferClient;

    private static final ReentrantReadWriteLock.WriteLock accountLock = new ReentrantReadWriteLock().writeLock();


    @Override
    public void transfer(Transaction transaction) throws TrustlineException {

        Account account = accountRepositoryService.findById(transaction.getFrom());
        try {
            accountLock.lock();
            logger.info("trustline balance {}", account.getBalance());
            logger.info("paying {} to {}", transaction.getAmount(), transaction.getTo());

            //send money
            TransferRequest transferRequest = new TransferRequest(transaction.getFrom(), transaction.getTo(), transaction.getAmount());
            TransferResponse response = trustlineTransferClient.transfer(transferRequest);

            if (response.getTransferStatus() != TransferStatus.COMPLETED) {
                logger.error("exception in transfer from={},to{}", transferRequest.getFrom(), transaction.getTo());
                throw new TrustlineException(String.format("exception in transfer from=%s,to %s", transferRequest.getFrom(), transaction.getTo()).toString());
            }

            //update new balance
            BigDecimal newBalance = account.getBalance().getValue().subtract(transaction.getAmount().getValue());
            account.updateBalance(newBalance);
            accountRepositoryService.persist(account);
            logger.info("successfully sent {} from {} to {}", transaction.getAmount(), transaction.getFrom(), transaction.getTo());
        } finally {
            logger.info("trustline balance is {}", account.getBalance());
            accountLock.unlock();
        }
    }

    @Override
    public void accept(Transaction transaction) throws TrustlineException {
        Account account = accountRepositoryService.findById(transaction.getTo());
        try {
            accountLock.lock();
            logger.info("trustline balance {}", account.getBalance());

            //update new balance
            BigDecimal newBalance = account.getBalance().getValue().add(transaction.getAmount().getValue());
            account.updateBalance(newBalance);
            accountRepositoryService.persist(account);
            logger.info("you were paid {}", transaction.getAmount());
        } finally {
            logger.info("trustline balance is {}", account.getBalance());
            accountLock.unlock();
        }
    }

}
