package net.javaguides.banking_app.controller;


import net.javaguides.banking_app.dto.Accountdto;
import net.javaguides.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //add account REST API
    @PostMapping
    public ResponseEntity<Accountdto> addAccount(@RequestBody Accountdto accountdto) {
        return new ResponseEntity<>(accountService.createAccount(accountdto), HttpStatus.CREATED);
    }


    //Get account REST API
    @GetMapping("/{id}")
    public ResponseEntity<Accountdto> getAccountById(@PathVariable Long id) {
        Accountdto accountdto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountdto);
    }

    //Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<Accountdto> deposit(@PathVariable Long id  , @RequestBody Map<String , Double > request){

        Double amount = request.get("amount");
        Accountdto accountdto = accountService.deposit(id,request.get("amount"));
        return ResponseEntity.ok(accountdto);
    }

    //withdraw REST API
    @PutMapping("{id}/withdraw")
    public ResponseEntity<Accountdto> withdraw(@PathVariable Long id  ,
                                               @RequestBody Map<String , Double > request){
        double amount = request.get("amount");
        Accountdto accountdto = accountService.withdrawal(id , amount);
        return ResponseEntity.ok(accountdto);
    }


    //get all accounts REST API
    @GetMapping
    public ResponseEntity<List<Accountdto>> getAllAccounts(){
    List<Accountdto> accounts = accountService.getAllAccounts();
    return ResponseEntity.ok(accounts);

    }

    //delete account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount (@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully");
    }

}
