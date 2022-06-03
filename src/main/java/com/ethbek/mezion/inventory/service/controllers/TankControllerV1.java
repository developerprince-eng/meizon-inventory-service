package com.ethbek.mezion.inventory.service.controllers;

import com.ethbek.mezion.inventory.service.exceptions.AccessDeniedException;
import com.ethbek.mezion.inventory.service.models.dto.PumpDto;
import com.ethbek.mezion.inventory.service.models.dto.TankDto;
import com.ethbek.mezion.inventory.service.models.entites.Tank;
import com.ethbek.mezion.inventory.service.permission.AuthenticatedUser;
import com.ethbek.mezion.inventory.service.services.AccessControlService;
import com.ethbek.mezion.inventory.service.services.TankService;
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
@RequestMapping("/api/v1/tank")
@Tag(name = "Tank Management", description = "Version 1 for Managing Tanks for Meizon in different locations")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tank Management", description = "Version 1 for Managing Tanks for Meizon in different locations")
public class TankControllerV1 {

    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    private TankService tankService;

    @PostMapping("/")
    @Operation(
            summary = "Add New Tank for Location",
            description = "This endpoint enables one to be able to add a Tank for different locations",
            tags = { "Tank Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tank.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> addNewTankForALocation(@RequestBody TankDto tankDto, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return tankService.addNewTank(tankDto);
    }


    @PostMapping("/pump")
    @Operation(
            summary = "Add new pump to a tank for Location",
            description = "This endpoint enables one to be able to add a pump to a tank for location",
            tags = { "Tank Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tank.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> addNewPumpToATankForALocation(@RequestBody PumpDto pumpDto, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return tankService.addNewPumpToTank(pumpDto);
    }

    @GetMapping("/location/{locationId}")
    @Operation(
            summary = "Get Tanks by Location",
            description = "This endpoint enables one to be able to retrieve all Tanks for different locations",
            tags = { "Tank Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tank.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getTanksByLocation(@PathVariable("locationId") String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return tankService.getTanksByLocation(locationId);
    }

}
