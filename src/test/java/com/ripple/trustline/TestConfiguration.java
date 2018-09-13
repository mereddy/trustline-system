package com.ripple.trustline;

import com.ripple.trustline.dto.TransferRequest;
import com.ripple.trustline.dto.TransferResponse;
import com.ripple.trustline.dto.TransferStatus;
import com.ripple.trustline.exception.TrustlineException;
import com.ripple.trustline.model.Transaction;
import com.ripple.trustline.spi.TrustlineTransferClient;
import com.ripple.trustline.spi.TrustlineTransferService;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author vreddy
 */
@Configuration
@ComponentScan(basePackages = "com.ripple.trustline.controller")
public class TestConfiguration {

    @Bean("transfer_client")
    public TrustlineTransferClient trustlineTransferClient() {
        return Mockito.mock(TrustlineTransferClient.class);
    }

    @Bean
    public TrustlineTransferService trustlineTransferService() throws TrustlineException {
        TrustlineTransferService trustlineTransferService=Mockito.mock(TrustlineTransferService.class);
//        TransferResponse transferResponse=new TransferResponse();
//        transferResponse.setMesssge("success");
//        transferResponse.setTransferStatus(TransferStatus.COMPLETED);
//        Mockito.when(trustlineTransferService.transfer(Matchers.any(Transaction.class))).thenReturn(transferResponse);

        return trustlineTransferService;
    }

}
