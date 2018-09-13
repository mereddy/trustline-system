package com.ripple.trustline.controller;

import com.ripple.trustline.dto.TransferResponse;
import com.ripple.trustline.dto.TransferStatus;
import com.ripple.trustline.exception.TrustlineException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author vreddy
 */
@RestControllerAdvice(basePackages = "com.ripple.trustline.controller")
public class TrustlineExceptionHandler {

    @ResponseBody
    @ExceptionHandler(TrustlineException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public TransferResponse trustlineException(TrustlineException ex) {
        TransferResponse response = new TransferResponse();
        response.setMesssge(ex.getMessage());
        response.setTransferStatus(TransferStatus.FAILED);
        return response;
    }
}
