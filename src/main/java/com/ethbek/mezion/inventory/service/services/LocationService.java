package com.ethbek.mezion.inventory.service.services;

import com.ethbek.mezion.inventory.service.models.dto.LocationDto;
import com.ethbek.mezion.inventory.service.models.entites.Address;
import com.ethbek.mezion.inventory.service.models.entites.Location;
import com.ethbek.mezion.inventory.service.models.entites.LocationType;
import com.ethbek.mezion.inventory.service.models.entites.Region;
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

import java.text.StringCharacterIterator;
import java.util.Optional;

@Slf4j
@Service
public class LocationService {

    private static final String LOCATION_NOT_FOUND = "Location is not found";
    private static final String LOCATION_TYPE_NOT_FOUND = "Location type is not found";
    private static final String SUCCESSFULLY_DELETED_LOCATION = "Location was successfully deleted";
    private static final String NAME = "name";

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationTypeRepository locationTypeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private IDGenerator idGenerator;

    public ResponseEntity<Object> retrieveAllRegions(){
        return new ResponseEntity<>( regionRepository.findAll(), HttpStatus.OK );
    }


    public ResponseEntity<Object> retrieveAllLocationTypes(){
        return new ResponseEntity<>(locationTypeRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> addNewLocation(LocationDto locationDto, String locationId){
        Address address = addressRepository.saveAndFlush( Address.builder()
                .addressId(idGenerator.uuidGenerate( 32 ))
                .unitNumber( locationDto.getAddress().getUnitNumber() )
                .street( locationDto.getAddress().getStreet())
                .build() );

        Optional<LocationType> locationType = locationTypeRepository.findByType(locationDto.getType());

        if(locationType.isPresent()){
            Optional<Region> region  = regionRepository.findByProvince(locationDto.getProvince());
            if(region.isPresent()){
                return new ResponseEntity<>(locationRepository.save( Location.builder()
                        .locationId(locationId)
                        .name(locationDto.getName())
                        .description(locationDto.getDescription())
                        .locationType(locationType.get())
                        .address(address)
                        .region(region.get())
                        .build()), HttpStatus.CREATED);
            }
            return new ResponseEntity<>( "No such Province", HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<>( "No such Location Type", HttpStatus.NOT_FOUND );
    }

    public ResponseEntity<Object> editLocation(LocationDto locationDto, String locationId){
        Optional<Location> location = locationRepository.findByLocationId( locationId );

        if(location.isPresent()){
            Address address = addressRepository.saveAndFlush( Address.builder()
                    .addressId( idGenerator.uuidGenerate( 32 ) )
                    .unitNumber( locationDto.getAddress().getUnitNumber() )
                    .street( locationDto.getAddress().getStreet())
                    .build() );

            Optional<LocationType> locationType = locationTypeRepository.findByType(locationDto.getType());

            if(locationType.isPresent()){
                Optional<Region> region  = regionRepository.findByProvince(locationDto.getProvince());
                if(region.isPresent()){
                    return new ResponseEntity<>(locationRepository.save( Location.builder()
                            .locationId(locationId)
                            .name(locationDto.getName())
                            .description(locationDto.getDescription())
                            .locationType(locationType.get())
                            .address(address)
                            .region(region.get())
                            .build()), HttpStatus.CREATED);
                }
                return new ResponseEntity<>( "No such Province", HttpStatus.NOT_FOUND );
            }
            return new ResponseEntity<>( "No such Location Type", HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<>( "No Such Location", HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Object> retrieveAllCities(){
        return new ResponseEntity<>( cityRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> retrieveAllLocations(int size, int page){
        Pageable paging = PageRequest.of( page, size, Sort.by(NAME).descending() );
        return new ResponseEntity<>(locationRepository.findAll(paging), HttpStatus.OK);
    }

    public ResponseEntity<Object> retrieveAllLocationsByLocationType(String type, int size, int page){
        Optional<LocationType> locationType = locationTypeRepository.findByType(type);
        if (locationType.isPresent()){
            Pageable paging = PageRequest.of( page, size, Sort.by(NAME).descending());
            return new ResponseEntity<>(locationRepository.findByLocationType(locationType.get(), paging), HttpStatus.OK);
        }
        return new ResponseEntity<>( LOCATION_TYPE_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> deleteLocationByLocationId(String locationId){
        Optional<Location> location = locationRepository.findByLocationId(locationId);
        if (location.isPresent()){
            locationRepository.delete(location.get());
            return new ResponseEntity<>( SUCCESSFULLY_DELETED_LOCATION, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>( LOCATION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> getLocationById(String locationId){
        Optional<Location> location = locationRepository.findByLocationId(locationId);
        if(location.isPresent()){
            return new ResponseEntity<>( location.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>( LOCATION_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

}
