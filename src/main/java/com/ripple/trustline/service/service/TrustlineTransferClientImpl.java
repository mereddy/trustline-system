package com.ripple.trustline.service.service;

import com.ripple.trustline.dto.TransferRequest;
import com.ripple.trustline.dto.TransferResponse;
import com.ripple.trustline.exception.TrustlineException;
import com.ripple.trustline.spi.TrustlineTransferClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author vreddy
 */

@Component("transfer_client")
public class TrustlineTransferClientImpl implements TrustlineTransferClient {

    Logger logger = LoggerFactory.getLogger(TrustlineTransferClientImpl.class);

    @Value("${trustline.api.v1}")
    private String trustLineServiceId;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public TransferResponse transfer(TransferRequest request) throws TrustlineException {
        ResponseEntity<TransferResponse> response = restTemplate.postForEntity(trustLineServiceId+"/accept",request,TransferResponse.class);
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is4xxClientError()) {
            logger.error("exception calling {} with statusCode={}",trustLineServiceId, response.getStatusCode());
            throw new TrustlineException(String.format("exception calling %s , statusCode=%s", response, trustLineServiceId).toString());
        }
        return response.getBody();
    }

    @Override
    public TransferResponse accept(TransferRequest request) throws TrustlineException {
        throw new UnsupportedOperationException();
    }
}
