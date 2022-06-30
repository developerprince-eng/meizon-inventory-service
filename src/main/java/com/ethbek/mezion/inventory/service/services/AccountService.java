package com.ethbek.mezion.inventory.service.services;

import com.ethbek.mezion.inventory.service.models.dto.AccountsDto;
import com.ethbek.mezion.inventory.service.models.entites.*;
import com.ethbek.mezion.inventory.service.repositories.*;
import com.ethbek.mezion.inventory.service.utilites.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class AccountService {

    private static final String CREATED_AT = "created_at";
    private static final String LOCATION_NOT_FOUND = "Location not found";
    private static final String CUSTOMER_NOT_FOUND = "Customer not found";
    private static final String ACCOUNT_NOT_FOUND = "Account not found";
    private static final String SUCCESSFULLY_CREATED_ACCOUNT = "Successfully created an account";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountHistoryRepository accountHistoryRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private IDGenerator idGenerator;


    public ResponseEntity<Object> getAccountsByLocation(String locationId, int size, int page){
        Pageable paging = PageRequest.of( page, size, Sort.by(CREATED_AT).descending());
        Optional<Location> location = locationRepository.findByLocationId(locationId);
        accountHistoryRepository.findAll();
        if(location.isPresent()){
            return new ResponseEntity<>(accountRepository.findByLocation(location.get(), paging), HttpStatus.OK);
        }
        return new ResponseEntity<>( LOCATION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getAccountsByCustomer(String firstName, String lastName, String companyName,int size, int page){
        Pageable paging = PageRequest.of( page, size, Sort.by(CREATED_AT).descending());
        Optional<Customer> customer = customerRepository.findByFirstNameAndLastNameAndCompanyName( firstName, lastName, companyName);
        if(customer.isPresent()){
            return new ResponseEntity<>(accountRepository.findByCustomer(customer.get(), paging), HttpStatus.OK);
        }
        return new ResponseEntity<>( CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> saveAccount(AccountsDto accountsDto){
        Calendar calender = Calendar.getInstance();
        Date date = new Date();
        calender.setTime(date);
        calender.add(Calendar.DATE, 30);
        Optional<Customer> customer = customerRepository.findByFirstNameAndLastNameAndCompanyName( accountsDto.getCustomer().getFirstName(), accountsDto.getCustomer().getLastName(), accountsDto.getCustomer().getCompanyName());
        Optional<AccountType> accountType = accountTypeRepository.findByType(accountsDto.getAccountType().getType());
        Optional<Location> location = locationRepository.findByName(accountsDto.getLocation().getName());
        if(customer.isPresent() && accountType.isPresent() && location.isPresent()){
            return new ResponseEntity<>( accountRepository.saveAndFlush( Account.builder()
                    .accountId(idGenerator.uuidGenerate(32))
                    .customer(customer.get())
                    .createdAt(new Date())
                    .expirationDate(calender.getTime())
                    .location(location.get())
                    .status(AccountStatus.OPEN)
                    .accountType(accountType.get())
                    .build()), HttpStatus.OK);
        }
        return new ResponseEntity<>( CUSTOMER_NOT_FOUND, HttpStatus.OK);

    }

    public ResponseEntity<Object> deleteAccount(String accountId){
        Optional<Account> account = accountRepository.findByAccountId(accountId);
        if(account.isPresent()){
            accountRepository.delete(account.get());
            return new ResponseEntity<>(SUCCESSFULLY_CREATED_ACCOUNT, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<Object> editAccount(AccountsDto accountsDto, String accountId){
        Optional<Account> account = accountRepository.findByAccountId(accountId);

        if(account.isPresent()){
            return new ResponseEntity<>( accountRepository.save(Account.builder()
                    .accountType(accountsDto.getAccountType().getType()==null?account.get().getAccountType():accountTypeRepository.findByType(accountsDto.getAccountType().getType()).get())
                    .createdAt(account.get().getCreatedAt())
                    .expirationDate(account.get().getExpirationDate())
                    .location(accountsDto.getLocation().getName()==null?account.get().getLocation():locationRepository.findByName(accountsDto.getLocation().getName()).get())
                    .status(accountsDto.getStatus()==null?account.get().getStatus():accountsDto.getStatus())
                    .build()), HttpStatus.NO_CONTENT );
        }
        return new ResponseEntity<>(ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }
}
