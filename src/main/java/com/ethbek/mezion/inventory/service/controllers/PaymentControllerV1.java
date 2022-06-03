package com.ethbek.mezion.inventory.service.controllers;

import com.ethbek.mezion.inventory.service.exceptions.AccessDeniedException;
import com.ethbek.mezion.inventory.service.models.dto.PaymentDto;
import com.ethbek.mezion.inventory.service.models.entites.Location;
import com.ethbek.mezion.inventory.service.models.entites.Payment;
import com.ethbek.mezion.inventory.service.permission.AuthenticatedUser;
import com.ethbek.mezion.inventory.service.services.AccessControlService;
import com.ethbek.mezion.inventory.service.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ethbek.mezion.inventory.service.models.dto.Constants.FORBIDDEN_MESSAGE;

@Slf4j
@RestController
@RequestMapping("/api/v1/payment")
@Tag(name = "Payment Management", description = "Version 1 for Managing Payments for Meizon")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Payment Management", description = "Version 1 for Managing Payments for Meizon")
public class PaymentControllerV1 {

    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{paymentId}")
    @Operation(
            summary = "Get Payment in Meizon by payment Id",
            description = "This endpoint enables on to be able to get a Payment for the Organization by payment Id",
            tags = { "Payment Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getPaymentById(@PathVariable("paymentId")String paymentId, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isSales(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return paymentService.getPaymentById(paymentId);
    }

    @GetMapping("/")
    @Operation(
            summary = "Get All Payments in Meizon",
            description = "This endpoint enables on to be able to get a Payments for the Organization",
            tags = { "Payment Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getAllPayments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isSales(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return paymentService.getAllPayments(size, page);
    }

    @GetMapping("/customer-info")
    @Operation(
            summary = "Get Payment in Meizon by Customer Information",
            description = "This endpoint enables on to be able to get a Payment for the Organization by Customer Information",
            tags = { "Payment Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getPaymentByCustomeInfo(@RequestParam String firstName,@RequestParam String lastName,@RequestParam String companyName , @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isSales(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return paymentService.getPaymentsByCustomer(firstName, lastName, companyName, size, page);
    }

    @DeleteMapping("/{paymentId}")
    @Operation(
            summary = "Delete Payment by location id in Meizon",
            description = "This endpoint enables the organization to be able to delete Location in Meizon by searching by location Id",
            tags = { "Payment Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Location.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> deleteByLocationId(@PathVariable("paymentId")String paymentId, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isSales(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return paymentService.deletePayment(paymentId);
    }

    @PostMapping("/")
    @Operation(
            summary = "Create Payment in Meizon",
            description = "This endpoint enables on to be able to create a Payment for the Organization",
            tags = { "Payment Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Payment.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> makePayment(@RequestBody List<PaymentDto> payments, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isSales(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return paymentService.makePayment(payments);
    }
}
