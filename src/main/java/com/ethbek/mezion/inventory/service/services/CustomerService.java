package com.ethbek.mezion.inventory.service.services;

import com.ethbek.mezion.inventory.service.models.dto.*;
import com.ethbek.mezion.inventory.service.models.entites.*;
import com.ethbek.mezion.inventory.service.repositories.*;
import com.ethbek.mezion.inventory.service.utilites.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;

import static com.ethbek.mezion.inventory.service.Constants.*;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ContactTypeRepository contactTypeRepository;

    @Autowired
    private IDGenerator idGenerator;

    public ResponseEntity<Object> saveCustomer(CustomerDto customerDto){
        CustomerResponse customer = saveCustomerEntity( customerDto,null);
        if(Objects.isNull(customer)){
            return new ResponseEntity<>( CITY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    public ResponseEntity<Object> retrieveAllCustomers(int page, int size){
        Pageable paging = PageRequest.of( page, size, Sort.by(FIRST_NAME).descending());
        Page<Customer> customers = customerRepository.findAll(paging);
        return new ResponseEntity<>(convertCustomerEntitiesToCustomerResponses( customers ) , HttpStatus.OK);
    }

    public ResponseEntity<Object> retrieveALlCustomersByCity(String cityName, int page, int size){
        Pageable paging = PageRequest.of( page, size, Sort.by(FIRST_NAME).descending());
        Optional<City> city = cityRepository.findByName(cityName);
        if (city.isPresent()){
            Page<Customer> customers = customerRepository.findByCity(city.get(), paging);
            return new ResponseEntity<>(convertCustomerEntitiesToCustomerResponses( customers ) , HttpStatus.OK);
        }
        return new ResponseEntity<>(CITY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> retrieveAllCustomerByCompanyName(String companyName, int page, int size){
        Pageable paging = PageRequest.of( page, size, Sort.by(FIRST_NAME).descending());
        Page<Customer> customers = customerRepository.findByCompanyName(companyName, paging);
        return new ResponseEntity<>(convertCustomerEntitiesToCustomerResponses( customers ) , HttpStatus.OK);
    }

    public ResponseEntity<Object> retrieveAllCustomersByLastName(String lastName, int page, int size){
        Pageable paging = PageRequest.of( page, size, Sort.by(FIRST_NAME).descending());
        Page<Customer> customers = customerRepository.findByLastName(lastName, paging);
        return new ResponseEntity<>(convertCustomerEntitiesToCustomerResponses( customers ) , HttpStatus.OK);
    }

    public ResponseEntity<Object> retrieveAllCustomersByFirstName(String firstName, int page, int size){
        Pageable paging = PageRequest.of( page, size, Sort.by(FIRST_NAME).descending());
        Page<Customer> customers = customerRepository.findByFirstName(firstName, paging);

        return new ResponseEntity<>(convertCustomerEntitiesToCustomerResponses(customers) , HttpStatus.OK);
    }

    public ResponseEntity<Object> editCustomer(CustomerDto customerDto, String customerId){
        Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
        if (customer.isPresent()) {
            return new ResponseEntity<>(saveCustomerEntity(customerDto, customerId), HttpStatus.OK);
        }
        return new ResponseEntity<>(CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getCustomerById(String customerId){
        Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
        if (customer.isPresent()){
            List<ContactDto> contactDtoList = new ArrayList<>();
            List<Contact> contacts = contactRepository.findByCustomer(customer.get());
            contacts.forEach( contact ->
                    contactDtoList.add(ContactDto.builder()
                            .contactId( idGenerator.uuidGenerate( 32 ) )
                            .type( contact.getType().getDescriptorType() )
                            .detail( contact.getDetail() )
                            .build()));
            return new ResponseEntity<>( CustomerResponse.builder()
                    .customerId( customer.get().getCustomerId() )
                    .address( AddressDto.builder()
                            .street( customer.get().getAddress().getStreet() )
                            .unitNumber( customer.get().getAddress().getUnitNumber())
                            .build() )
                    .contacts( contactDtoList )
                    .companyName( customer.get().getCompanyName() )
                    .city( customer.get().getCity().getName() )
                    .region( customer.get().getRegion().getProvince() )
                    .firstName( customer.get().getFirstName() )
                    .lastName( customer.get().getLastName() )
                    .contacts(contactDtoList)
                    .build(), HttpStatus.OK );
        }
        return new ResponseEntity<>(CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> deleteCustomerById(String customerId){
        Optional<Customer> customer = customerRepository.findByCustomerId(customerId);
        if (customer.isPresent()){
            customerRepository.deleteByCustomerId(customer.get().getCustomerId());
            return new ResponseEntity<>( DELETED_CUSTOMER_SUCCESSFULLY, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    private Page<CustomerResponse> convertCustomerEntitiesToCustomerResponses(Page<Customer> customers) {
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        customers.getContent().forEach( customer -> {
                    List<Contact> contacts = contactRepository.findByCustomer(customer);
                    List<ContactDto> contactDtoList = new ArrayList<>();
                    contacts.forEach( contact ->
                            contactDtoList.add( ContactDto.builder()
                                    .contactId( contact.getContactId() )
                                    .detail( contact.getDetail() )
                                    .type( contact.getType().getDescriptorType() )
                                    .build() )
                    );
                    customerResponseList.add( CustomerResponse.builder()
                            .lastName( customer.getLastName() )
                            .firstName( customer.getFirstName() )
                            .region( customer.getRegion().getProvince() )
                            .city( customer.getCity().getName() )
                            .companyName( customer.getCompanyName() )
                            .address( AddressDto.builder()
                                    .unitNumber( customer.getAddress().getUnitNumber() )
                                    .street( customer.getAddress().getStreet() )
                                    .build() )
                            .contacts(contactDtoList)
                            .customerId( customer.getCustomerId() )
                            .build());
                }
        );

        return  new Page<>() {
            @Override
            public int getTotalPages() {
                return customers.getTotalPages();
            }

            @Override
            public long getTotalElements() {
                return customers.getTotalElements();
            }

            @Override
            public <U> Page<U> map(Function<? super CustomerResponse, ? extends U> converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return customers.getNumber();
            }

            @Override
            public int getSize() {
                return customers.getSize();
            }

            @Override
            public int getNumberOfElements() {
                return customers.getNumberOfElements();
            }

            @Override
            public List<CustomerResponse> getContent() {
                return customerResponseList;
            }

            @Override
            public boolean hasContent() {
                return customers.hasNext();
            }

            @Override
            public Sort getSort() {
                return customers.getSort();
            }

            @Override
            public boolean isFirst() {
                return customers.isFirst();
            }

            @Override
            public boolean isLast() {
                return customers.isLast();
            }

            @Override
            public boolean hasNext() {
                return customers.hasNext();
            }

            @Override
            public boolean hasPrevious() {
                return customers.hasPrevious();
            }

            @Override
            public Pageable nextPageable() {
                return customers.nextPageable();
            }

            @Override
            public Pageable previousPageable() {
                return customers.previousPageable();
            }

            @NotNull
            @Override
            public Iterator<CustomerResponse> iterator() {
                return customerResponseList.iterator();
            }
        };
    }

    private CustomerResponse saveCustomerEntity(CustomerDto customerDto, String customerId){
        Optional<City> city = cityRepository.findByName(customerDto.getCity());
        Optional<Region> region = regionRepository.findByProvince(customerDto.getRegion());
        List<Contact> contacts = new ArrayList<>(  );
        List<ContactDto> contactResposnes = new ArrayList<>(  );
        if (city.isPresent() && region.isPresent()) {
            Customer customer = customerRepository.saveAndFlush( Customer.builder()
                    .customerId(customerId == null?idGenerator.uuidGenerate(16):customerId)
                    .firstName(customerDto.getFirstName())
                    .lastName(customerDto.getLastName())
                    .companyName( customerDto.getCompanyName())
                    .region(region.get())
                    .city(city.get())
                    .address(addressRepository.saveAndFlush(Address.builder()
                            .addressId( idGenerator.uuidGenerate( 32 ) )
                            .street(customerDto.getAddress().getStreet())
                            .unitNumber( customerDto.getAddress().getUnitNumber())
                            .build()))
                    .build());
            customerDto.getContacts().forEach( contactDto -> {
                Optional<ContactType> contactType = contactTypeRepository.findByDescriptorType( contactDto.getType() );
                if(contactType.isPresent()) {
                    contacts.add(contactRepository.saveAndFlush( Contact.builder()
                            .type( contactType.get())
                            .detail( contactDto.getDetail() )
                            .contactId(contactDto.getContactId())
                            .customer(customer)
                            .build()));
                    contactResposnes.add(ContactDto.builder()
                            .contactId( contactDto.getContactId() )
                            .type( contactType.get().getDescriptorType() )
                            .detail( contactDto.getDetail() )
                            .build());

                }
            });

            return CustomerResponse.builder()
                    .customerId( customer.getCustomerId() )
                    .address( AddressDto.builder()
                            .street( customer.getAddress().getStreet() )
                            .unitNumber( customer.getAddress().getUnitNumber() )
                            .build())
                    .city( customer.getCity().getName())
                    .region( customer.getRegion().getProvince() )
                    .firstName( customer.getFirstName() )
                    .lastName( customer.getLastName() )
                    .contacts( contactResposnes )
                    .companyName( customer.getCompanyName() )
                    .build();
        }
        return null;
    }
}
