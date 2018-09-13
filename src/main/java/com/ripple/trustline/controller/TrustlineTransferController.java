package com.ripple.trustline.controller;

import com.ripple.trustline.dto.TransferRequest;
import com.ripple.trustline.dto.TransferResponse;
import com.ripple.trustline.dto.TransferStatus;
import com.ripple.trustline.exception.TrustlineException;
import com.ripple.trustline.model.Transaction;
import com.ripple.trustline.spi.TrustlineTransferClient;
import com.ripple.trustline.spi.TrustlineTransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vreddy
 */
@RestController
public class TrustlineTransferController implements TrustlineTransferClient {

    private Logger logger = LoggerFactory.getLogger(TrustlineTransferController.class);

    @Autowired
    private TrustlineTransferService trustlineTransferService;

    @PostMapping("/transfer")
    @Override
    public TransferResponse transfer(@RequestBody TransferRequest request) throws TrustlineException {

        logger.info("initiated transfer from {} to {} and amount={}", request.getFrom(), request.getTo(), request.getAmount());
        //TOOD: add validators for Id and Amount
        trustlineTransferService.transfer(transaction(request));
        TransferResponse response = new TransferResponse();
        response.setTransferStatus(TransferStatus.COMPLETED);
        response.setMesssge(String.format("Successfully transferred %s from %s t0 %s ", request.getAmount(),
                request.getFrom(), request.getTo()).toString());

        return response;
    }

    @PostMapping("/accept")
    @Override
    public TransferResponse accept(@RequestBody TransferRequest request) throws TrustlineException {

        logger.info("accepting incoming transfer from {} to {} and amount={}", request.getFrom(), request.getTo(), request.getAmount());
        //TOOD: add validators for Id and Amount
        trustlineTransferService.accept(transaction(request));
        TransferResponse response = new TransferResponse();
        response.setTransferStatus(TransferStatus.COMPLETED);
        response.setMesssge(String.format("Successfully accepted %s from %s t0 %s ", request.getAmount(),
                request.getFrom(), request.getTo()).toString());

        return response;
    }

    private Transaction transaction(TransferRequest request) {
        return new Transaction(request.getFrom(), request.getTo(), request.getAmount());
    }

}
