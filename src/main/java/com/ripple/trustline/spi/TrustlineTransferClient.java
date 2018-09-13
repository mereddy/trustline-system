package com.ripple.trustline.spi;

import com.ripple.trustline.dto.TransferRequest;
import com.ripple.trustline.dto.TransferResponse;
import com.ripple.trustline.exception.TrustlineException;

/**
 * @author vreddy
 */
public interface TrustlineTransferClient {

    TransferResponse transfer(TransferRequest request) throws TrustlineException;

    TransferResponse accept(TransferRequest request) throws TrustlineException;
}
