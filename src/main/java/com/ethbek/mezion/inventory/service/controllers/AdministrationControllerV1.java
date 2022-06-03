package com.ethbek.mezion.inventory.service.controllers;

import com.ethbek.mezion.inventory.service.exceptions.AccessDeniedException;
import com.ethbek.mezion.inventory.service.models.dto.CategoryDto;
import com.ethbek.mezion.inventory.service.models.dto.ProductDto;
import com.ethbek.mezion.inventory.service.models.entites.Category;
import com.ethbek.mezion.inventory.service.models.entites.Product;
import com.ethbek.mezion.inventory.service.models.entites.PumpType;
import com.ethbek.mezion.inventory.service.permission.AuthenticatedUser;
import com.ethbek.mezion.inventory.service.services.AccessControlService;
import com.ethbek.mezion.inventory.service.services.AdministrationService;
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
@RequestMapping("/api/v1/admin")
@Tag(name = "Administrator Management", description = "Version 1 for Administrator Management for Meizon")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Administrator Management", description = "Version 1 for Administrator Management for Meizon")
public class AdministrationControllerV1 {

    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    private AdministrationService administrationService;

    @PostMapping("/add/pump-type")
    @Operation(
            summary = "Add a new Pump Type in Meizon",
            description = "This endpoint enables on to be able to create a new Pump Type  for the Organization",
            tags = { "Administrator Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PumpType.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> addPumpType(@RequestParam String name, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isOrganisationAdministrator(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return administrationService.addPumpType(name) ;
    }

    @PostMapping("/add/product")
    @Operation(
            summary = "Add Product",
            description = "This endpoint enables on to be able to add a Product",
            tags = { "General Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> addProduct(@RequestBody ProductDto productDto, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isOrganisationAdministrator(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return administrationService.addNewProduct(productDto);
    }


    @PostMapping("/add/product-category")
    @Operation(
            summary = "Add Product Category",
            description = "This endpoint enables on to be able to add a Product Category",
            tags = { "Administrator Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> addCategory(@RequestBody CategoryDto categoryDto, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isOrganisationAdministrator(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return administrationService.addNewCategory(categoryDto);
    }

    @DeleteMapping("delete/product/{productSKU}")
    @Operation(
            summary = "Delete Product",
            description = "This endpoint enables on to be able to delete a Product using the product sku",
            tags = { "Administrator Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> deleteProductByProductSku(@PathVariable("productSKU") String productSKU, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isOrganisationAdministrator(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return administrationService.deleteProduct(productSKU);
    }
}
