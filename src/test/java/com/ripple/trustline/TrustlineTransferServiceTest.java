package com.ripple.trustline;

import com.ripple.trustline.dto.Amount;
import com.ripple.trustline.dto.TransferRequest;
import com.ripple.trustline.dto.TransferResponse;
import com.ripple.trustline.dto.TransferStatus;
import com.ripple.trustline.exception.TrustlineException;
import com.ripple.trustline.model.Account;
import com.ripple.trustline.model.PersonalInfo;
import com.ripple.trustline.model.Transaction;
import com.ripple.trustline.service.service.AccountRepositoryService;
import com.ripple.trustline.service.service.TrustlineTransferServiceImpl;
import com.ripple.trustline.spi.RepositoryService;
import com.ripple.trustline.spi.TrustlineTransferClient;
import com.ripple.trustline.spi.TrustlineTransferService;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author vreddy
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrustlineTransferServiceTest.TestConfiguration.class)
@TestPropertySource(properties = {"trustline.api.v1=http://localhost:8081/api/ripple/trust-line/services/", "trustline.account.id=7861d56c-8b65-4ebc-be0b-0e25cf230079"})
public class TrustlineTransferServiceTest {

    @Autowired
    private AccountRepositoryService accountRepositoryService;

    @Autowired
    private TrustlineTransferClient trustlineTransferClient;

    @Autowired
    private TrustlineTransferService trustlineTransferService;


    @Test
    public void tranferTest() throws TrustlineException {
        Account account = new Account(UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), "", new Amount("USD", new BigDecimal(0)), new PersonalInfo("bob", ""));
        Mockito.when(accountRepositoryService.findById(Matchers.any(UUID.class))).thenReturn(account);
        TransferResponse transferResponse = new TransferResponse("", TransferStatus.COMPLETED);
        Mockito.when(trustlineTransferClient.transfer(Matchers.any(TransferRequest.class))).thenReturn(transferResponse);
        Transaction transaction = new Transaction(UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), new Amount("USD", new BigDecimal("1000")));
        trustlineTransferService.transfer(transaction);
        TestCase.assertEquals(new BigDecimal(-1000), account.getBalance().getValue());
    }


    @Test(expected = TrustlineException.class)
    public void tranferExceptionTest() throws TrustlineException {
        Account account = new Account(UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), "", new Amount("USD", new BigDecimal(0)), new PersonalInfo("bob", ""));
        Mockito.when(accountRepositoryService.findById(Matchers.any(UUID.class))).thenReturn(account);
        TransferResponse transferResponse = new TransferResponse("", TransferStatus.COMPLETED);
        Mockito.when(trustlineTransferClient.transfer(Matchers.any(TransferRequest.class))).thenThrow(new TrustlineException(""));
        //TransferRequest transferRequest = new TransferRequest(UUID.randomUUID(), UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), new Amount("USD", new BigDecimal(1000)));
        Transaction transaction = new Transaction(UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), new Amount("USD", new BigDecimal("1000")));
        trustlineTransferService.transfer(transaction);
    }

    @Test
    public void acceptTest() throws TrustlineException {
        Account account = new Account(UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), "", new Amount("USD", new BigDecimal(0)), new PersonalInfo("bob", ""));
        Mockito.when(accountRepositoryService.findById(Matchers.any(UUID.class))).thenReturn(account);
        Transaction transaction = new Transaction(UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), new Amount("USD", new BigDecimal("2000")));
        trustlineTransferService.accept(transaction);
        TestCase.assertEquals(new BigDecimal(2000), account.getBalance().getValue());
    }


    @Configuration
    public static class TestConfiguration {

        @Bean("transfer_client")
        public TrustlineTransferClient trustlineTransferClient() {
            return Mockito.mock(TrustlineTransferClient.class);
        }

        @Bean
        public AccountRepositoryService accountRepositoryService() {
            return Mockito.mock(AccountRepositoryService.class);
        }

        @Bean
        public TrustlineTransferService trustlineTransferService() {
            return new TrustlineTransferServiceImpl();
        }
    }

}
