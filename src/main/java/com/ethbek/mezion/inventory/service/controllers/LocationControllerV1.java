package com.ethbek.mezion.inventory.service.controllers;

import com.ethbek.mezion.inventory.service.exceptions.AccessDeniedException;
import com.ethbek.mezion.inventory.service.models.dto.LocationDto;
import com.ethbek.mezion.inventory.service.models.entites.City;
import com.ethbek.mezion.inventory.service.models.entites.Location;
import com.ethbek.mezion.inventory.service.models.entites.LocationType;
import com.ethbek.mezion.inventory.service.models.entites.Region;
import com.ethbek.mezion.inventory.service.permission.AuthenticatedUser;
import com.ethbek.mezion.inventory.service.services.AccessControlService;
import com.ethbek.mezion.inventory.service.services.LocationService;
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
@RequestMapping("/api/v1/location")
@Tag(name = "Location Management", description = "Version 1 for Managing Location for Meizon")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Location Management", description = "Version 1 for Managing Location for Meizon")
public class LocationControllerV1 {

    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    private LocationService locationService;

    @PostMapping("/")
    @Operation(
            summary = "Create New Location in Meizon",
            description = "This endpoint enables on to be able to create an New added Location for the Organization",
            tags = { "Location Management" },
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
    public ResponseEntity<Object> addNewLocation(@RequestBody LocationDto locationDto, @RequestParam String locationId){
        return locationService.addNewLocation(locationDto, locationId);
    }


    @PutMapping("/{locationId}")
    @Operation(
            summary = "Create New Location in Meizon",
            description = "This endpoint enables on to be able to create an New added Location for the Organization",
            tags = { "Location Management" },
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
    public ResponseEntity<Object> editLocation(@RequestBody LocationDto locationDto, @PathVariable("locationId") String locationId,  @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return locationService.editLocation(locationDto, locationId);
    }

    @GetMapping("/regions")
    @Operation(
            summary = "Get All Regions for Meizon",
            description = "This endpoint enables the organization to be able to get all Regions for Meizon",
            tags = { "Location Management" },
                    responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Region.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getAllRegions(){
        return locationService.retrieveAllRegions();
    }

    @GetMapping("/location-types")
    @Operation(
            summary = "Get All Location Types for Meizon",
            description = "This endpoint enables the organization to be able to get all Location Types for Meizon",
            tags = { "Location Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LocationType.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getAllLocationTypes(){
        return locationService.retrieveAllLocationTypes();
    }

    @GetMapping("/cities")
    @Operation(
            summary = "Get All Cities Details in Zimbabwe",
            description = "This endpoint enables the organization to be able to get all Cities in Zimbabwe",
            tags = { "Location Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = City.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getAllCities(){
        return locationService.retrieveAllCities();
    }


    @GetMapping("/")
    @Operation(
            summary = "Get All Locations Details in Meizon",
            description = "This endpoint enables the organization to be able to get all Locations in Meizon",
            tags = { "Location Management" },
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
    public ResponseEntity<Object> getAllLocations(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size){
        return locationService.retrieveAllLocations(size, page);
    }

    @GetMapping("/type/{type}")
    @Operation(
            summary = "Get Location by location type in Meizon",
            description = "This endpoint enables the organization to be able to get Location in Meizon by searching by type",
            tags = { "Location Management" },
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
    public ResponseEntity<Object> getAllLocationByType(@PathVariable("type")String type, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size,  @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return locationService.retrieveAllLocationsByLocationType( type, size, page );
    }

    @GetMapping("/{locationId}")
    @Operation(
            summary = "Get Location by location id in Meizon",
            description = "This endpoint enables the organization to be able to get Location in Meizon by searching by location Id",
            tags = { "Location Management" },
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
    public ResponseEntity<Object> getLocationByLocationId(@PathVariable("locationId")String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return locationService.getLocationById(locationId);
    }

    @DeleteMapping("/{locationId}")
    @Operation(
            summary = "Delete Location by location id in Meizon",
            description = "This endpoint enables the organization to be able to delete Location in Meizon by searching by location Id",
            tags = { "Location Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> deleteByLocationId(@PathVariable("locationId")String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return locationService.deleteLocationByLocationId(locationId);
    }
}
