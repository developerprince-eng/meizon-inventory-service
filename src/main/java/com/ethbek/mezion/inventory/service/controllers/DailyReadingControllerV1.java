package com.ethbek.mezion.inventory.service.controllers;

import com.ethbek.mezion.inventory.service.exceptions.AccessDeniedException;
import com.ethbek.mezion.inventory.service.models.dto.*;
import com.ethbek.mezion.inventory.service.models.entites.DipSheetReadings;
import com.ethbek.mezion.inventory.service.models.entites.Location;
import com.ethbek.mezion.inventory.service.permission.AuthenticatedUser;
import com.ethbek.mezion.inventory.service.services.AccessControlService;
import com.ethbek.mezion.inventory.service.services.DailyReadingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

import static com.ethbek.mezion.inventory.service.models.dto.Constants.FORBIDDEN_MESSAGE;

@Slf4j
@RestController
@RequestMapping("/api/v1/daily/readings")
@Tag(name = "Daily Reading Management", description = "Version 1 for Managing Daily Readings for Meizon")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Daily Reading Management", description = "ersion 1 for Managing Daily Readings for Meizon")
public class DailyReadingControllerV1 {

    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    private DailyReadingsService dailyReadingsService;

    @GetMapping("/tank/date-range/location/{locationId}/tank/{tankId}")
    @Operation(
            summary = "Get Daily Dip Reading for a Location in Meizon by Date Range",
            description = "This endpoint enables one to be able to Retrieve New Daily Readings by Date Range for the Organization",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> getDipDailyReadingByDateRange(@PathVariable("locationId")String locationId, @PathVariable("tankId")String tankId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size,@RequestParam Long orgainsationalId, @RequestParam Date startDate,@RequestParam Date endDate, AuthenticatedUser authenticatedUser) throws ParseException {
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.getDailyReadingsByLocationAndDateRangeForTank(locationId, startDate, tankId, endDate, size, page);
    }

    @GetMapping("/pump/date-range/location/{locationId}/pump-side/{pumpSide}")
    @Operation(
            summary = "Get Daily Pump Reading for a Location in Meizon by Date Range",
            description = "This endpoint enables one to be able to Retrieve New Daily Readings by Date Range for the Organization",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> getPumpDailyReadingByDateRange(@PathVariable("locationId")String locationId, @PathVariable("pumpSide")String pumpSide, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size,@RequestParam Long orgainsationalId, @RequestParam Date startDate,@RequestParam Date endDate, AuthenticatedUser authenticatedUser) throws ParseException {
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.getDailyReadingsByLocationAndDateRangeForPumpSide(locationId, startDate, pumpSide, endDate, size, page);
    }

    @DeleteMapping("/tank/location/{locationId}/daily-dip-reading/{dailyDipReadingId}")
    @Operation(
            summary = "Get Daily Dip Reading for a Location in Meizon by Date Range",
            description = "This endpoint enables one to be able to Retrieve New Daily Readings by Date Range for the Organization",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> deleteDailyDipReading(@PathVariable("locationId")String locationId, @PathVariable("dailyDipReadingId")String dailyDipReadingId,@RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser) throws ParseException {
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.deleteDailyReadingByIdForTank(dailyDipReadingId);
    }

    @DeleteMapping("/pump/location/{locationId}/daily-dip-reading/{dailyDipReadingId}")
    @Operation(
            summary = "Get Daily Pump Reading for a Location in Meizon by Date Range",
            description = "This endpoint enables one to be able to Retrieve New Daily Readings by Date Range for the Organization",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> deleteDailyPumpReading(@PathVariable("locationId")String locationId, @PathVariable("dailyDipReadingId")String dailyDipReadingId,@RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser) throws ParseException {
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.deleteDailyReadingByIdForPump(dailyDipReadingId);
    }

    @GetMapping("/tank/date/location/{locationId}/tank/{tankId}")
    @Operation(
            summary = "Get Daily Dip Reading for a Location in Meizon by Date",
            description = "This endpoint enables one to be able to Retrieve New Daily Readings by Date for the Organization",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> getDipDailyReadingByDate(@PathVariable("locationId")String locationId, @PathVariable("tankId")String tankId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size,@RequestParam Long orgainsationalId, @RequestParam Date date, AuthenticatedUser authenticatedUser) throws ParseException {
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.getDailyReadingsByLocationAndTankAndDate(locationId, tankId, date, size, page);
    }

    @GetMapping("/pump/date/location/{locationId}/pump-side/{pumpSideId}")
    @Operation(
            summary = "Get Daily Pump Reading for a Location in Meizon by Date",
            description = "This endpoint enables one to be able to Retrieve New Daily Readings by Date for the Organization",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> getPumpDailyReadingByDate(@PathVariable("locationId")String locationId, @PathVariable("pumpSideId")String pumpSideId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size,@RequestParam Long orgainsationalId, @RequestParam Date date, AuthenticatedUser authenticatedUser) throws ParseException {
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.getDailyReadingsByLocationAndPumpSideIdAndDate(locationId, pumpSideId, date, size, page);
    }

    @PostMapping("/dip/opening/location/{locationId}/tank/{tankId}")
    @Operation(
            summary = "Create Opening Daily Dip Reading for a Location in Meizon",
            description = "This endpoint enables on to be able to create an New Daily Reading for the Organization",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> addNewOpeningDipDailyReading(@PathVariable("locationId")String locationId, @PathVariable("tankId")String tankId, @RequestParam Long orgainsationalId,@RequestParam Integer opening, AuthenticatedUser authenticatedUser) throws ParseException {
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.addNewDailyReadingForTankOpening(opening, locationId, tankId, authenticatedUser.getEmailAddress());
    }

    @PostMapping("/pump/opening/location/{locationId}/pump/{pumpId}/pump-side/{pumpSideId}")
    @Operation(
            summary = "Create Opening Daily Pump Reading for a Location in Meizon",
            description = "This endpoint enables on to be able to create an New Daily Reading for the Organization",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> addNewOpeningPumpReading(@PathVariable("locationId")String locationId, @PathVariable("pumpId")String pumpId, @PathVariable("pumpSideId") String pumpSideId , @RequestParam Long orgainsationalId,@RequestParam Integer openingVolume, AuthenticatedUser authenticatedUser) throws ParseException {
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.addDailyPumpOpeningReading(authenticatedUser.getEmailAddress(), locationId, pumpSideId, pumpId, openingVolume);
    }


    @PostMapping("/pump/closing/location/{locationId}/pump/{pumpId}/pump-side/{pumpSideId}")
    @Operation(
            summary = "Create Closing Daily Pump Reading for a Location in Meizon",
            description = "This endpoint enables on to be able to create an New Daily Reading for the Organization",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> addNewClosinggPumpReading(@PathVariable("locationId")String locationId, @PathVariable("pumpId")String pumpId, @PathVariable("pumpSideId") String pumpSideId , @RequestParam Long orgainsationalId,@RequestParam Integer closingVolume, AuthenticatedUser authenticatedUser) throws ParseException {
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.addDailyPumpClosingReading(authenticatedUser.getEmailAddress(), locationId, pumpSideId, pumpId, closingVolume);
    }

    @PostMapping("/dip/closing/location/{locationId}/tank/{tankId}")
    @Operation(
            summary = "Create Closing Daily Dip Reading for a Location in Meizon",
            description = "This endpoint enables on to be able to create an New Daily Reading for the Organization",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> addNewClosingDipDailyReading(@PathVariable("locationId")String locationId, @PathVariable("tankId")String tankId, @RequestParam Long orgainsationalId,@RequestParam Integer closing, AuthenticatedUser authenticatedUser) throws ParseException {
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.addNewDailyReadingForTankClosing(closing, locationId, tankId, authenticatedUser.getEmailAddress());
    }

    @GetMapping("/dip/{dailyReadingId}")
    @Operation(
            summary = "Get Daily Dip Reading in Meizon by daily reading Id",
            description = "This endpoint enables on to be able to get daily Reading for the Organization by daily reading Id",
            tags = { "Daily Reading Management" },
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
    public ResponseEntity<Object> getDailyReadingById( @PathVariable("dailyReadingId")String dailyReadingId, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.getDailyReadingByDailyReadingIdForTank(dailyReadingId);
    }

    @GetMapping("/dip/user/{userEmail}")
    @Operation(
            summary = "Get Daily Dip Reading in Meizon by user email",
            description = "This endpoint enables on to be able to get daily Reading for the Organization by user Id",
            tags = { "Daily Reading Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",  array = @ArraySchema(schema = @Schema(implementation = DipSheetReadings.class)))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getDailyReadingById( @PathVariable("userEmail")String userId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.getDailyReadingsByUserForTank(userId,locationId, page, size);
    }


    @GetMapping("/")
    @Operation(
            summary = "Get Daily Dip Reading in Meizon",
            description = "This endpoint enables on to be able to get daily Reading for the Organization",
            tags = { "Daily Reading Management" },
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DipSheetReadings.class))
                    ),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
            }
    )
    @CrossOrigin(origins = "*", allowedHeaders = "*") //NOSONAR Meizon sits behind a Firewall
    public ResponseEntity<Object> getDailyReadingById( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10", required = false) int size, @RequestParam String locationId, @RequestParam Long orgainsationalId, AuthenticatedUser authenticatedUser){
        if (!accessControlService.isGeneral(authenticatedUser,orgainsationalId, locationId))
            throw new AccessDeniedException(FORBIDDEN_MESSAGE.concat(authenticatedUser.getEmailAddress()));
        return dailyReadingsService.getDailyReadingsForTank(page, size);
    }

}
