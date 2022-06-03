package com.ethbek.mezion.inventory.service.services;

import com.ethbek.mezion.inventory.service.models.dto.PaymentDto;
import com.ethbek.mezion.inventory.service.models.entites.*;
import com.ethbek.mezion.inventory.service.models.entites.Currency;
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

import java.util.*;

@Slf4j
@Service
public class PaymentService {

    private static final String PAYMENT_DATE = "payment_date";
    private static final String PAYMENT_NOT_FOUND = "Payment not found";
    private static final String CUSTOMER_NOT_FOUND = "Customer not found";
    private static final String EMPTY = "";

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private IDGenerator idGenerator;

    public ResponseEntity<Object> makePayment(List<PaymentDto> paymentDtoList){
        List<PaymentHistory> paymentHistories = new ArrayList<>();
        paymentDtoList.forEach( paymentDto -> {
            Optional<Customer> customer = customerRepository.findByFirstNameAndLastNameAndCompanyName(paymentDto.getCustomer().getFirstName(), paymentDto.getCustomer().getLastName(), paymentDto.getCustomer().getCompanyName());
            Optional<Currency> currency = currencyRepository.findByName(paymentDto.getCurrency().getName());
            Optional<PaymentType> paymentType = paymentTypeRepository.findByType(paymentDto.getPaymentType().getType());
            if(customer.isPresent() && currency.isPresent() && paymentType.isPresent()){
                paymentHistories.add(paymentHistoryRepository.save(PaymentHistory.builder()
                        .paymentHistoryId(idGenerator.uuidGenerate( 32 ))
                        .amount(paymentDto.getAmount())
                        .customer(customer.get())
                        .currency(currency.get())
                        .paymentTime(new Date())
                        .paymentType(paymentType.get())
                        .build()));
            }
        });
        List<Double> doubles  = new ArrayList<>(  );
        paymentHistories.forEach(paymentHistory -> doubles.add( paymentHistory.getAmount().doubleValue()));
        return new ResponseEntity<>( paymentRepository.save( Payment.builder()
                .paymentId( idGenerator.uuidGenerate( 32 ) )
                .amount(doubles.stream().mapToDouble(Double::doubleValue).sum())
                .paymentDate(new Date())
                .paymentHistories(paymentHistories)
                .build()), HttpStatus.OK);
    }

    public ResponseEntity<Object> getPaymentById(String paymentId){
        Optional<Payment> payment = paymentRepository.findByPaymentId(paymentId);
        if(payment.isPresent()){
            return new ResponseEntity<>(payment.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(PAYMENT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> deletePayment(String paymentId){
        Optional<Payment> payment = paymentRepository.findByPaymentId(paymentId);
        if(payment.isPresent()){
            paymentRepository.delete(payment.get());
            return new ResponseEntity<>(EMPTY, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PAYMENT_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getPaymentsByCustomer(String firstName, String lastName, String companyName, int size, int page){
        Optional<Customer> customer = customerRepository.findByFirstNameAndLastNameAndCompanyName(firstName, lastName, companyName);
        Pageable paging = PageRequest.of( page, size, Sort.by(PAYMENT_DATE).descending());
        if(customer.isPresent()){
            return new ResponseEntity<>(paymentHistoryRepository.findByCustomer(customer.get(), paging), HttpStatus.OK);
        }
        return  new ResponseEntity<>(CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getAllPayments(int size, int page){
        Pageable paging = PageRequest.of( page, size, Sort.by(PAYMENT_DATE).descending());
        return new ResponseEntity<>(paymentRepository.findAll(paging), HttpStatus.OK);
    }

}
