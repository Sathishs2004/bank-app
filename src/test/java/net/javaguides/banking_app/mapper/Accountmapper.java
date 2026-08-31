package net.javaguides.banking_app.mapper;

import net.javaguides.banking_app.dto.Accountdto;
import net.javaguides.banking_app.entity.Account;

public class Accountmapper {
    public static Account maptoAccount(Accountdto accountdto) {
        Account account = new Account(
                accountdto.getId(),
                accountdto.getAccountHolderName(),
                accountdto.getBalance()
        );
        return account;
    }


    public static Accountdto maptoAccountdto(Account account) {
        Accountdto accountdto = new Accountdto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
        return accountdto;
    }
}
