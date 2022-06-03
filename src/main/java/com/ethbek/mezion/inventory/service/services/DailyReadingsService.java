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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

import static com.ethbek.mezion.inventory.service.models.dto.Constants.DATE_FORMAT;

@Slf4j
@Service
public class DailyReadingsService {

    private static  final  String DAILY_READING_DELETED = "Daily Reading is Not Found";
    private static final String USER_NOT_FOUND = "User Not Found";
    private static final String LOCATION_NOT_FOUND = "Location is not found";
    private static final String DIPSHEET_NOT_FOUND = "No Such value on Dip Sheet";
    private static final String DAILY_READING_NOT_FOUND = "Daily Reading is not found";
    private static final String OR = " or ";
    private static final String CREATED_AT = "created_date";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TankRepository tankRepository;

    @Autowired
    private IDGenerator idGenerator;

    @Autowired
    private PumpReadingsRepository pumpReadingsRepository;

    @Autowired
    private DipSheetRepository dipSheetRepository;

    @Autowired
    private DipReadingsRepository dipReadingsRepository;

    @Autowired
    private PumpRepository pumpRepository;

    @Autowired
    private PumpSideRepository pumpSideRepository;

    @Autowired
    private LocationRepository locationRepository;

    private String formatDates(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        return format.format(date);
    }

    public ResponseEntity<Object> getDailyReadingsByLocationAndDateRangeForTank(String locationId, Date startDate,String tankId ,Date endDate, int size, int page){
        Optional<Location> optionalLocation = locationRepository.findByLocationId(locationId);
        Optional<Tank> optionalTank = tankRepository.findById(tankId);
        if(optionalLocation.isPresent() && optionalTank.isPresent()){
            Tank tankReceived = optionalTank.get();
            List<DipSheetReadings> dailyReadings = dipReadingsRepository.findByTank(tankReceived);
            List<DipSheetReadings> filteredDailyReadings = new ArrayList<>( );


            dailyReadings.forEach( dailyReading -> {
                if(dailyReading.getCreatedDate().after(startDate) && dailyReading.getCreatedDate().before(endDate)) {
                    filteredDailyReadings.add( dailyReading );
                }
            });
            if(dailyReadings.isEmpty()){
                return new ResponseEntity<>( new ArrayList<>(  ), HttpStatus.OK );
            }

            Page<DipSheetReadings> dailyReadingsPage = new Page<>() {
                @Override
                public int getTotalPages() {
                    return page;
                }

                @Override
                public long getTotalElements() {
                    return filteredDailyReadings.size();
                }

                @Override
                public Page map(Function converter) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return 0;
                }

                @Override
                public int getSize() {
                    return size;
                }

                @Override
                public int getNumberOfElements() {
                    return 0;
                }

                @Override
                public List getContent() {
                    return filteredDailyReadings;
                }

                @Override
                public boolean hasContent() {
                    return false;
                }

                @Override
                public Sort getSort() {
                    return null;
                }

                @Override
                public boolean isFirst() {
                    return false;
                }

                @Override
                public boolean isLast() {
                    return false;
                }

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public boolean hasPrevious() {
                    return false;
                }

                @Override
                public Pageable nextPageable() {
                    return null;
                }

                @Override
                public Pageable previousPageable() {
                    return null;
                }

                @NotNull
                @Override
                public Iterator iterator() {
                    return filteredDailyReadings.iterator();
                }
            };

            return new ResponseEntity<>(dailyReadingsPage, HttpStatus.OK);
        }
        return new ResponseEntity<>( LOCATION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getDailyReadingsByLocationAndDateRangeForPumpSide(String locationId, Date startDate,String pumpSideId ,Date endDate, int size, int page){
        Optional<Location> optionalLocation = locationRepository.findByLocationId(locationId);
        if(optionalLocation.isPresent()){
            List<PumpReadings> dailyReadings = pumpReadingsRepository.findByPumpSide(pumpSideId);
            List<PumpReadings> filteredDailyReadings = new ArrayList<>( );


            dailyReadings.forEach( dailyReading -> {
                if(dailyReading.getCreatedDate().after(startDate) && dailyReading.getCreatedDate().before(endDate)) {
                    filteredDailyReadings.add( dailyReading );
                }
            });
            if(dailyReadings.isEmpty()){
                return new ResponseEntity<>( new ArrayList<>(  ), HttpStatus.OK );
            }

            Page<DipSheetReadings> dailyReadingsPage = new Page<>() {
                @Override
                public int getTotalPages() {
                    return page;
                }

                @Override
                public long getTotalElements() {
                    return filteredDailyReadings.size();
                }

                @Override
                public Page map(Function converter) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return 0;
                }

                @Override
                public int getSize() {
                    return size;
                }

                @Override
                public int getNumberOfElements() {
                    return 0;
                }

                @Override
                public List getContent() {
                    return filteredDailyReadings;
                }

                @Override
                public boolean hasContent() {
                    return false;
                }

                @Override
                public Sort getSort() {
                    return null;
                }

                @Override
                public boolean isFirst() {
                    return false;
                }

                @Override
                public boolean isLast() {
                    return false;
                }

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public boolean hasPrevious() {
                    return false;
                }

                @Override
                public Pageable nextPageable() {
                    return null;
                }

                @Override
                public Pageable previousPageable() {
                    return null;
                }

                @NotNull
                @Override
                public Iterator iterator() {
                    return filteredDailyReadings.iterator();
                }
            };

            return new ResponseEntity<>(dailyReadingsPage, HttpStatus.OK);
        }
        return new ResponseEntity<>( LOCATION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getDailyReadingsByLocationAndTankAndDate(String locationId,String tankId ,Date startDate, int size, int page){
        Optional<Location> optionalLocation = locationRepository.findByLocationId(locationId);
        Optional<Tank> optionalTank = tankRepository.findById(tankId);
        if(optionalLocation.isPresent() && optionalTank.isPresent()){
            List<DipSheetReadings> dailyReadings = dipReadingsRepository.findByTank(optionalTank.get());
            List<DipSheetReadings> filteredDailyReadings = new ArrayList<>( );

            dailyReadings.forEach( dailyReading -> {
                if(dailyReading.getCreatedDate().after(startDate)) {
                    filteredDailyReadings.add( dailyReading );
                }
            });
            if(dailyReadings.isEmpty()){
                return new ResponseEntity<>( new ArrayList<>(), HttpStatus.OK );
            }
            Page<DipSheetReadings> dailyReadingsPage = new Page<>() {
                @Override
                public int getTotalPages() {
                    return page;
                }

                @Override
                public long getTotalElements() {
                    return filteredDailyReadings.size();
                }

                @Override
                public Page map(Function converter) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return 0;
                }

                @Override
                public int getSize() {
                    return size;
                }

                @Override
                public int getNumberOfElements() {
                    return 0;
                }

                @Override
                public List getContent() {
                    return filteredDailyReadings;
                }

                @Override
                public boolean hasContent() {
                    return false;
                }

                @Override
                public Sort getSort() {
                    return null;
                }

                @Override
                public boolean isFirst() {
                    return false;
                }

                @Override
                public boolean isLast() {
                    return false;
                }

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public boolean hasPrevious() {
                    return false;
                }

                @Override
                public Pageable nextPageable() {
                    return null;
                }

                @Override
                public Pageable previousPageable() {
                    return null;
                }

                @NotNull
                @Override
                public Iterator iterator() {
                    return filteredDailyReadings.iterator();
                }
            };

            return new ResponseEntity<>(dailyReadingsPage, HttpStatus.OK);
        }
        return new ResponseEntity<>( LOCATION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getDailyReadingsByLocationAndPumpSideIdAndDate(String locationId,String pumpSideId ,Date startDate, int size, int page){
        Optional<Location> optionalLocation = locationRepository.findByLocationId(locationId);
        if(optionalLocation.isPresent()){
            List<PumpReadings> dailyReadings = pumpReadingsRepository.findByPumpSide(pumpSideId);
            List<PumpReadings> filteredDailyReadings = new ArrayList<>( );

            dailyReadings.forEach( dailyReading -> {
                if(dailyReading.getCreatedDate().after(startDate)) {
                    filteredDailyReadings.add( dailyReading );
                }
            });
            if(dailyReadings.isEmpty()){
                return new ResponseEntity<>( new ArrayList<>(), HttpStatus.OK );
            }
            Page<DipSheetReadings> dailyReadingsPage = new Page<>() {
                @Override
                public int getTotalPages() {
                    return page;
                }

                @Override
                public long getTotalElements() {
                    return filteredDailyReadings.size();
                }

                @Override
                public Page map(Function converter) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return 0;
                }

                @Override
                public int getSize() {
                    return size;
                }

                @Override
                public int getNumberOfElements() {
                    return 0;
                }

                @Override
                public List getContent() {
                    return filteredDailyReadings;
                }

                @Override
                public boolean hasContent() {
                    return false;
                }

                @Override
                public Sort getSort() {
                    return null;
                }

                @Override
                public boolean isFirst() {
                    return false;
                }

                @Override
                public boolean isLast() {
                    return false;
                }

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public boolean hasPrevious() {
                    return false;
                }

                @Override
                public Pageable nextPageable() {
                    return null;
                }

                @Override
                public Pageable previousPageable() {
                    return null;
                }

                @NotNull
                @Override
                public Iterator iterator() {
                    return filteredDailyReadings.iterator();
                }
            };

            return new ResponseEntity<>(dailyReadingsPage, HttpStatus.OK);
        }
        return new ResponseEntity<>( LOCATION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getDailyReadingByDailyReadingIdForTank(String dailyRedingId){
        Optional<DipSheetReadings> dailyReading = dipReadingsRepository.findByDailyReadingId(dailyRedingId);
        if (dailyReading.isPresent()){
            return new ResponseEntity<>(dailyReading.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(DAILY_READING_DELETED, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getDailyReadingsForTank(int page, int size){
        Pageable paging = PageRequest.of( page, size, Sort.by(CREATED_AT).descending());
        return new ResponseEntity<>(dipReadingsRepository.findAll( paging), HttpStatus.OK);
    }

    public ResponseEntity<Object> getDailyReadingsByUserForTank(String userEmail,String locationId , int page, int size){
        Optional<User> user = userRepository.findByEmailAndLocationId(userEmail, locationId);
        if (user.isPresent()){
            Pageable paging = PageRequest.of( page, size, Sort.by(CREATED_AT).descending());
            return new ResponseEntity<>(dipReadingsRepository.findByUser(user.get(),  paging), HttpStatus.OK);
        }
        return new ResponseEntity<>( USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> addNewDailyReadingForTankOpening(Integer openingMeasurements, String locationId, String tankId, String email) throws ParseException {
        Optional<User> user = userRepository.findByEmailAndLocationId(email, locationId);
        Optional<Location> location = locationRepository.findByLocationId(locationId);
        Optional<DipSheet> openDipSheet = dipSheetRepository.findByMetricMeasurement(openingMeasurements);
        Optional<Tank> tank = tankRepository.findById(tankId);
        if(openDipSheet.isPresent()) {
            if (location.isPresent()  && tank.isPresent()  && user.isPresent()) {
                String formatedDate = formatDates( new Date(  ) );
                Date date = new SimpleDateFormat( DATE_FORMAT).parse(formatedDate);
                DipSheetReadings dailReading = dipReadingsRepository.save( DipSheetReadings.builder()
                        .dailyReadingId( idGenerator.uuidGenerate( 16 ) )
                        .tank(tank.get())
                        .user(user.get().getEmail())
                        .createdDate(date)
                        .openVolume( openDipSheet.get().getVolume() )
                        .build() );
                return new ResponseEntity<>( dailReading, HttpStatus.CREATED );
            }
            return new ResponseEntity<>( USER_NOT_FOUND.concat( OR ).concat( LOCATION_NOT_FOUND ), HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<>( DIPSHEET_NOT_FOUND, HttpStatus.NOT_FOUND );
    }

    public ResponseEntity<Object> addNewDailyReadingForTankClosing(Integer closingMeasurements, String locationId, String tankId, String email) throws ParseException {
        Optional<User> user = userRepository.findByEmailAndLocationId(email, locationId);
        Optional<Location> location = locationRepository.findByLocationId(locationId);
        Optional<DipSheet> closingDipSheet = dipSheetRepository.findByMetricMeasurement(closingMeasurements);
        Optional<Tank> tank = tankRepository.findById(tankId);
        if(closingDipSheet.isPresent()) {
            if (location.isPresent()  && tank.isPresent() && user.isPresent()) {
                String formatedDate = formatDates( new Date(  ) );
                Date date = new SimpleDateFormat( DATE_FORMAT).parse(formatedDate);
                Optional<DipSheetReadings> dipSheetReadings = dipReadingsRepository.findByUserAndCreatedDate(user.get(), date);
                if(dipSheetReadings.isPresent()){
                    DipSheetReadings dailReading = dipReadingsRepository.save( DipSheetReadings.builder()
                            .dailyReadingId( idGenerator.uuidGenerate( 16 ) )
                            .tank(tank.get())
                            .user(user.get().getEmail())
                            .createdDate(date)
                            .openVolume(dipSheetReadings.get().getOpenVolume())
                            .closeVolume(closingDipSheet.get().getVolume())
                            .build() );
                    return new ResponseEntity<>( dailReading, HttpStatus.CREATED );
                }

            }
            return new ResponseEntity<>( USER_NOT_FOUND.concat( OR ).concat( LOCATION_NOT_FOUND ), HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<>( DIPSHEET_NOT_FOUND, HttpStatus.NOT_FOUND );
    }

    public ResponseEntity<Object> deleteDailyReadingByIdForTank(String dailyReadingId){
        Optional<DipSheetReadings> dailyReading = dipReadingsRepository.findByDailyReadingId(dailyReadingId);
        if(dailyReading.isPresent()){
            dipReadingsRepository.delete(dailyReading.get());
            return new ResponseEntity<>(DAILY_READING_DELETED, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(DAILY_READING_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> deleteDailyReadingByIdForPump(String dailyReadingId){
        Optional<PumpReadings> dailyReading = pumpReadingsRepository.findByPumpReadingId(dailyReadingId);
        if(dailyReading.isPresent()){
            pumpReadingsRepository.delete(dailyReading.get());
            return new ResponseEntity<>(DAILY_READING_DELETED, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(DAILY_READING_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    //TODO Ensure that the opening Reading for the pump is not less than the reading of the previous day as the meter runs forward
    public ResponseEntity<Object> addDailyPumpOpeningReading(String userEmail,String locationId,String pumpSideId ,String pumpIpd, Integer openingVolume ) throws ParseException{
        Optional<Pump> optionalPump = pumpRepository.findById(pumpIpd);
        Optional<User> optionalUser = userRepository.findByEmailAndLocationId(userEmail, locationId);
        Optional<PumpSide> optionalPumpSide = pumpSideRepository.findByPumpSideId(pumpSideId);
        String formattedDate = formatDates(new Date( ));
        Date date = new SimpleDateFormat(DATE_FORMAT).parse(formattedDate);
        if(!optionalPump.isPresent()){
            return new ResponseEntity<>( "No Such Pump by ID ".concat(pumpIpd), HttpStatus.EXPECTATION_FAILED);
        }
        else if(!optionalUser.isPresent()){
            return new ResponseEntity<>( "No Such user with Email ".concat(userEmail), HttpStatus.EXPECTATION_FAILED );
        }
        else if(!optionalPumpSide.isPresent()){
            return new ResponseEntity<>( "No Such PumpSide on Pump".concat( optionalPump.get().getPumpId()), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(pumpReadingsRepository.save(PumpReadings.builder()
                .openVolume(openingVolume)
                .createdDate(date)
                .user(userEmail)
                .pumpSide(pumpSideId)
                .build()), HttpStatus.CREATED);
    }

    //TODO Ensure that the closing Reading for the pump is not less than the opening reading of the current day as the meter runs forward
    public ResponseEntity<Object> addDailyPumpClosingReading(String userEmail,String locationId,String pumpSideId ,String pumpIpd, Integer closingVolume ) throws ParseException{
        String formattedDate = formatDates(new Date( ));
        Date date = new SimpleDateFormat(DATE_FORMAT).parse(formattedDate);
        Optional<Pump> optionalPump = pumpRepository.findById(pumpIpd);
        Optional<PumpReadings> optionalPumpReadings = pumpReadingsRepository.findByUserAndCreatedDate(userEmail, date);
        Optional<User> optionalUser = userRepository.findByEmailAndLocationId(userEmail, locationId);
        Optional<PumpSide> optionalPumpSide = pumpSideRepository.findByPumpSideId(pumpSideId);
        if(!optionalPump.isPresent()){
            return new ResponseEntity<>( "No Such Pump by ID ".concat(pumpIpd), HttpStatus.EXPECTATION_FAILED);
        }
        else if(!optionalUser.isPresent()){
            return new ResponseEntity<>( "No Such user with Email ".concat(userEmail), HttpStatus.EXPECTATION_FAILED );
        }
        else if(!optionalPumpReadings.isPresent()){
            return new ResponseEntity<>( "No Available Corresponding Opening Reading", HttpStatus.EXPECTATION_FAILED );
        }
        else if(!optionalPumpSide.isPresent()){
            return new ResponseEntity<>( "No Such PumpSide on Pump".concat( optionalPump.get().getPumpId()), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(pumpReadingsRepository.save(PumpReadings.builder()
                .closeVolume(closingVolume)
                .openVolume(optionalPumpReadings.get().getOpenVolume())
                .createdDate(date)
                .user(userEmail)
                .pumpSide(pumpSideId)
                .build()), HttpStatus.CREATED);
    }


}
