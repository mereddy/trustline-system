package com.ripple.trustline.spi;

import com.ripple.trustline.exception.TrustlineException;
import com.ripple.trustline.model.Transaction;

/**
 * @author vreddy
 */
public interface TrustlineTransferService {

    void transfer(Transaction transaction) throws TrustlineException;

    void accept(Transaction transaction) throws TrustlineException;

}
