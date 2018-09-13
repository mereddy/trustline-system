package com.ripple.trustline.exception;

/**
 * @author vreddy
 */
public class TrustlineException extends Exception {

    public TrustlineException(String message) {
        super(message);
    }

    public TrustlineException(String message, Throwable cause) {
        super(message, cause);
    }
}
