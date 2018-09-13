package com.ripple.trustline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ripple.trustline.dto.TransferRequest;
import com.ripple.trustline.dto.TransferResponse;
import com.ripple.trustline.dto.TransferStatus;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


public class TrustlineApplicationTests {

    RestTemplate restTemplate = new RestTemplate();

    @Test // Integration test to run different transactions
    public void contextLoads() throws IOException {
        //Bob's server :
        //./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=bob

        //Alice server :
        //./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=alice


        ObjectMapper objectMapper = new ObjectMapper();

        //Transaction 1 : bob -> alice (5000)
        TransferRequest tx1 = objectMapper.readValue(this.getClass().getResourceAsStream("/input/bob2aliceTx1.json"), TransferRequest.class);
        TransferResponse transferResponse = restTemplate.postForEntity("http://localhost:8081/api/ripple/trust-line/services/transfer", tx1, TransferResponse.class).getBody();
        System.out.println(String.format("Transaction-1 From=%s,to=%s",tx1.getFrom(),tx1.getTo()));
        Assert.assertEquals(TransferStatus.COMPLETED, transferResponse.getTransferStatus());

        //Transaction 2 : alice -> bob (10000)
        TransferRequest tx2 = objectMapper.readValue(this.getClass().getResourceAsStream("/input/alice2bobTx2.json"), TransferRequest.class);
        transferResponse = restTemplate.postForEntity("http://localhost:8080/api/ripple/trust-line/services/transfer", tx2, TransferResponse.class).getBody();
        System.out.println(String.format("Transaction-1 From=%s,to=%s",tx2.getFrom(),tx2.getTo()));
        Assert.assertEquals(TransferStatus.COMPLETED, transferResponse.getTransferStatus());

        //Transaction 3 : bob -> alice (2500)
        TransferRequest tx3 = objectMapper.readValue(this.getClass().getResourceAsStream("/input/bob2aliceTx3.json"), TransferRequest.class);
        transferResponse = restTemplate.postForEntity("http://localhost:8081/api/ripple/trust-line/services/transfer", tx3, TransferResponse.class).getBody();
        System.out.println(String.format("Transaction-1 From=%s,to=%s",tx3.getFrom(),tx3.getTo()));
        Assert.assertEquals(TransferStatus.COMPLETED, transferResponse.getTransferStatus());

    }

}
