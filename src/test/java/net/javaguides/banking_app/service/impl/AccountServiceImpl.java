package net.javaguides.banking_app.service.impl;

import net.javaguides.banking_app.dto.Accountdto;
import net.javaguides.banking_app.entity.Account;
import net.javaguides.banking_app.mapper.Accountmapper;
import net.javaguides.banking_app.repository.AccountRepository;
import net.javaguides.banking_app.service.AccountService;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Accountdto createAccount(Accountdto accountdto) {
        Account account= Accountmapper.maptoAccount(accountdto);
        Account savedAccount = accountRepository.save(account);
        return Accountmapper.maptoAccountdto(savedAccount);
    }

    @Override
    public Accountdto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account not found"));
        return Accountmapper.maptoAccountdto(account);
    }

    @Override
    public Accountdto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account not found"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return Accountmapper.maptoAccountdto(savedAccount);
    }

    @Override
    public Accountdto withdrawal(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account not found"));

        if(account.getBalance()< amount){
            throw new RuntimeException("Insufficient balance");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return Accountmapper.maptoAccountdto(savedAccount);
    }

    @Override
    public List<Accountdto> getAllAccounts() {
     List<Account> accounts = accountRepository.findAll();
     return accounts.stream().map((account) -> Accountmapper.maptoAccountdto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account not found"));
        accountRepository.deleteById(id);
    }
}
