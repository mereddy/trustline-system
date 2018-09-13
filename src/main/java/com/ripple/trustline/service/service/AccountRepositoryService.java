package com.ripple.trustline.service.service;

import com.ripple.trustline.dto.Amount;
import com.ripple.trustline.model.Account;
import com.ripple.trustline.model.PersonalInfo;
import com.ripple.trustline.spi.RepositoryService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author vreddy
 */
@Component
public class AccountRepositoryService implements RepositoryService<Account, UUID> {

    private ConcurrentHashMap<UUID, Account> accounts = new ConcurrentHashMap<>();

    {
        PersonalInfo personalInfo = new PersonalInfo("bob", "");
        accounts.put(UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), new Account(UUID.fromString("7861d56c-8b65-4ebc-be0b-0e25cf230079"), "", new Amount("USD", new BigDecimal(0)), personalInfo));
        personalInfo = new PersonalInfo("alice", "");
        accounts.put(UUID.fromString("ecc2094a-c0b9-4eb4-8f07-c019961aa3f0"), new Account(UUID.fromString("ecc2094a-c0b9-4eb4-8f07-c019961aa3f0"), "", new Amount("USD", new BigDecimal(0)), personalInfo));

    }

    @Override
    public Account findById(UUID id) {
        return accounts.get(id);
    }

    @Override
    public void persist(Account entity) {
        accounts.put(entity.getId(),entity);
    }
}
