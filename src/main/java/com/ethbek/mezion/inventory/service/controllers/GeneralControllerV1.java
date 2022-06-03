package com.ethbek.mezion.inventory.service.controllers;

import com.ethbek.mezion.inventory.service.exceptions.AccessDeniedException;
import com.ethbek.mezion.inventory.service.models.entites.Category;
import com.ethbek.mezion.inventory.service.models.entites.Product;
import com.ethbek.mezion.inventory.service.models.entites.ProductType;
import com.ethbek.mezion.inventory.service.models.entites.PumpSideType;
import com.ethbek.mezion.inventory.service.permission.AuthenticatedUser;
import com.ethbek.mezion.inventory.service.repositories.*;
import com.ethbek.mezion.inventory.service.services.AccessControlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ethbek.mezion.inventory.service.models.dto.Constants.FORBIDDEN_MESSAGE;

@Slf4j
@RestController
@RequestMapping("/api/v1/general")
@Tag(name = "General Management", description = "Version 1 for Managing General Access of Utilites for Meizon")
@io.swagger.v3.oas.annotations.tags.Tag(name = "General Management", description = "Version 1 for Managing General Access of Utilites for Meizon")
public class GeneralControllerV1 {

    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    private PumpSideTypeRepository pumpSideTypeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private PumpTypeRepository pumpTypeRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/pump-side-types")
    @Operation(
            summary = "Get Pump Side Types",
            description = "This endpoint enables on to be able to get Pump Side Types",
            tags = { "General Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PumpSideType.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getPumpSides(){
        return new ResponseEntity<>(pumpSideTypeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products")
    @Operation(
            summary = "Get Products",
            description = "This endpoint enables on to be able to get all Products",
            tags = { "General Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getProducts(){
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/pump-types")
    @Operation(
            summary = "Get Pump Types",
            description = "This endpoint enables on to be able to get all Pump Types",
            tags = { "General Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductType.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getPumpTypes(){
        return new ResponseEntity<>(pumpTypeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/product-types")
    @Operation(
            summary = "Get Product Types",
            description = "This endpoint enables on to be able to get all Product Types",
            tags = { "General Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductType.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getProductTypes(){
        return new ResponseEntity<>(productTypeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/product-categories")
    @Operation(
            summary = "Get Product Categories",
            description = "This endpoint enables on to be able to get all Product Categories",
            tags = { "General Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getCategories(){
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

}

