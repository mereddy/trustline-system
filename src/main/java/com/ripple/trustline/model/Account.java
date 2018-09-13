package com.ripple.trustline.model;


import com.ripple.trustline.dto.Amount;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author vreddy
 */
public final class Account {

    private UUID id;

    private String accountType;

    private Amount balance;

    private PersonalInfo personalInfo;

    public Account(UUID id, String type, Amount balance, PersonalInfo personalInfo) {
        this.id = id;
        this.accountType = type;
        this.balance = balance;
        this.personalInfo = personalInfo;
    }

    public void updateBalance(BigDecimal balance) {
        this.balance.value = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getAccountType() {
        return accountType;
    }

    public Amount getBalance() {
        return balance;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }
}
