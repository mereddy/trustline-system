package com.ripple.trustline.dto;

import java.math.BigDecimal;

/**
 * @author vreddy
 */
public class Amount {

    public String currency;

    public BigDecimal value;

    public Amount() {

    }

    public Amount(String currency, BigDecimal value) {
        this.currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return currency + ":" + value;
    }
}
