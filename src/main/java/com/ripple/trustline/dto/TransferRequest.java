package com.ripple.trustline.dto;

import java.util.UUID;

/**
 * @author vreddy
 */
public class TransferRequest {

    UUID from;
    UUID to;
    Amount amount;

    public TransferRequest() {

    }

    public TransferRequest(UUID from, UUID to, Amount amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public UUID getFrom() {
        return from;
    }

    public void setFrom(UUID from) {
        this.from = from;
    }

    public UUID getTo() {
        return to;
    }

    public void setTo(UUID to) {
        this.to = to;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}

