package net.javaguides.banking_app.service;

import net.javaguides.banking_app.dto.Accountdto;

import java.util.List;


public interface AccountService {
    Accountdto createAccount(Accountdto accountdto);
    Accountdto getAccountById(Long id);
    Accountdto deposit(Long id, double amount);
    Accountdto withdrawal(Long id, double amount);

    List<Accountdto> getAllAccounts();

    void deleteAccount(Long id);



}
