package com.ripple.trustline.model;

import com.ripple.trustline.dto.Amount;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author vreddy
 */
public class Transaction {

    private UUID from;
    private UUID to;
    private Amount amount;

    public void Transaction(){

    }

    public Transaction(UUID from, UUID to, Amount amount) {
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
