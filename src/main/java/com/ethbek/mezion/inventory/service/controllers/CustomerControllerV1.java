package com.ethbek.mezion.inventory.service.controllers;

import com.ethbek.mezion.inventory.service.exceptions.AccessDeniedException;
import com.ethbek.mezion.inventory.service.models.dto.CustomerDto;
import com.ethbek.mezion.inventory.service.models.entites.Customer;
import com.ethbek.mezion.inventory.service.permission.AuthenticatedUser;
import com.ethbek.mezion.inventory.service.services.AccessControlService;
import com.ethbek.mezion.inventory.service.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ethbek.mezion.inventory.service.models.dto.Constants.FORBIDDEN_MESSAGE;

@Slf4j
@RestController
@RequestMapping("/api/v1/customer")
@Tag(name = "Customer Management", description = "Version 1 for Managing Customers for Meizon")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Customer Management", description = "Version 1 for Managing Customers for Meizon")
public class CustomerControllerV1 {

    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    CustomerService customerService;

    @PostMapping("/")
    @Operation(
            summary = "Create a new Customer in Meizon",
            description = "This endpoint enables on to be able to create a new Customer for the Organization",
            tags = { "Customer Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> addNewCustomer(@RequestBody CustomerDto customerDto, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isOrganisationAdministrator(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return customerService.saveCustomer(customerDto);
    }

    @GetMapping("/{customerId}")
    @Operation(
            summary = "Get Customers Meizon by ID",
            description = "This endpoint enables on to be able to get a Customer for the Organization by Customer Id",
            tags = { "Customer Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getCustomerByCustomerId(@PathVariable("customerId")String customerId, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return customerService.getCustomerById(customerId);
    }


    @DeleteMapping("/delete/{customerId}")
    @Operation(
            summary = "Delete Customers Meizon by ID",
            description = "This endpoint enables on to be able to delete a Customer for the Organization by Customer Id",
            tags = { "Customer Management" },
            responses = {
                    @ApiResponse(
                            description = "No Content",
                            responseCode = "204",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> deleteByCustomerId(@PathVariable("customerId")String customerId, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isOrganisationAdministrator(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return customerService.deleteCustomerById(customerId);
    }

    @GetMapping("/")
    @Operation(
            summary = "Get All Customers in Meizon",
            description = "This endpoint enables on to be able to get all Customers for the Organization",
            tags = { "Customer Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getAllCustomer(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return customerService.retrieveAllCustomers(page, size);
    }

    @GetMapping("/city/{city}")
    @Operation(
            summary = "Get All Customers in Meizon by City",
            description = "This endpoint enables on to be able to get all Customers for the Organization",
            tags = { "Customer Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getAllCustomerByCity(@PathVariable("city")String city, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return customerService.retrieveALlCustomersByCity(city , page ,size);
    }

    @GetMapping("/company/{company}")
    @Operation(
            summary = "Get All Customers in Meizon by Company",
            description = "This endpoint enables on to be able to get all Customers for the Organization",
            tags = { "Customer Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getAllCustomersByCompany(@PathVariable("company")String company, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return customerService.retrieveAllCustomerByCompanyName(company , page ,size);
    }

    @GetMapping("/last-name/{lastName}")
    @Operation(
            summary = "Get All Customers in Meizon by Last Name",
            description = "This endpoint enables on to be able to get all Customers for the Organization",
            tags = { "Customer Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getAllCustomersByLastName(@PathVariable("lastName")String lastName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return customerService.retrieveAllCustomersByLastName(lastName , page ,size);
    }

    @GetMapping("/first-name/{firstName}")
    @Operation(
            summary = "Get All Customers in Meizon by First Name",
            description = "This endpoint enables on to be able to get all Customers for the Organization",
            tags = { "Customer Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getAllCustomersByFirstName(@PathVariable("firstName")String firstName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return customerService.retrieveAllCustomersByFirstName(firstName , page ,size);
    }


    @PutMapping("/edit/{customerId}")
    @Operation(
            summary = "Edit an existing Customer in Meizon by First Name",
            description = "This endpoint enables on to be able to get all Customers for the Organization",
            tags = { "Customer Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Customer.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> editCustomer(@RequestBody CustomerDto customerDto, @PathVariable("customerId")String customerId, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isOrganisationAdministrator(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return customerService.editCustomer(customerDto, customerId);
    }
}
