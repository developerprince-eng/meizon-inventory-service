package com.ethbek.mezion.inventory.service.services;

import com.ethbek.mezion.inventory.service.models.dto.UserDto;
import com.ethbek.mezion.inventory.service.models.entites.*;
import com.ethbek.mezion.inventory.service.repositories.LocationRepository;
import com.ethbek.mezion.inventory.service.repositories.UserRepository;
import com.ethbek.mezion.inventory.service.utilites.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserService {

    private static final String FIRST_NAME = "firstName";
    private static final String USER_NOT_FOUND = "User Not Found";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;


    @Autowired
    private IDGenerator idGenerator;

    public ResponseEntity<Object> retrieveUserById(String userId, String locationId){
        Optional<User> user = userRepository.findByEmailAndLocationId(userId, locationId);
        if(user.isPresent()){
            return new ResponseEntity<>( user.get(), HttpStatus.OK );
        }
        return new ResponseEntity<>( USER_NOT_FOUND, HttpStatus.NOT_FOUND );
    }


    public ResponseEntity<Object> retrieveAllUsers(int size, int page){
        Pageable paging = PageRequest.of( page, size, Sort.by(FIRST_NAME).descending() );
        return new ResponseEntity<>(userRepository.findAll( paging ), HttpStatus.OK);
    }

    public void deleteByEmail(String email){
        Set<User> users = userRepository.findByEmail(email);
        if (!Objects.isNull(users)){
            userRepository.deleteAllByEmail(email);
        }
        log.warn("Users Do not exist");
    }

    public void creatUser (UserDto userDto){
        Optional<Location> location = locationRepository.findByLocationId(userDto.getLocationId());
        Optional<User> userOptional = userRepository.findByEmailAndLocationId( userDto.getEmail(), userDto.getLocationId() );
        if(location.isPresent()) {
            if (userOptional.isPresent()) {
                log.warn("User Already Exists for Location");
            }
            User user = User.builder()
                    .email( userDto.getEmail() )
                    .firstName( userDto.getFirstName() )
                    .lastName( userDto.getLastName() )
                    .employeeId( userDto.getUserId() )
                    .mobile( userDto.getMobile() )
                    .address( Address.builder()
                            .addressId( idGenerator.uuidGenerate( 32 ) )
                            .street( userDto.getStreet() )
                            .unitNumber( userDto.getUnitNumber() )
                            .build() )
                    .locationId( userDto.getLocationId() )
                    .organisationalId( userDto.getOrganisationId() )
                    .build();
            userRepository.save( user );

        }
        log.warn("Location associated to user couldn't not be found");
    }

}

