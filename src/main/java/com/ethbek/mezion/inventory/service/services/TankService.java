package com.ethbek.mezion.inventory.service.services;

import com.ethbek.mezion.inventory.service.models.dto.PumpDto;
import com.ethbek.mezion.inventory.service.models.dto.TankDto;
import com.ethbek.mezion.inventory.service.models.entites.*;
import com.ethbek.mezion.inventory.service.repositories.*;
import com.ethbek.mezion.inventory.service.utilites.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TankService {

    @Autowired
    private TankRepository tankRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PumpTypeRepository pumpTypeRepository;

    @Autowired
    private PumpRepository pumpRepository;

    @Autowired
    private  PumpSideRepository pumpSideRepository;

    public static final String LOCATION_NOT_FOUND = "The location does not exist";
    public static final String PRODUCT_NOT_FOUND = "The product does not exist";

    @Autowired
    private IDGenerator idGenerator;

    private void generatePumpSide(Integer sides, Pump pump){
        for (int i = 0; i < sides; i++) {
            pumpSideRepository.save( PumpSide.builder()
                    .pump(pump)
                    .pumpSideId( idGenerator.pumpSideGenerate())
                    .build());
        }
    }

    public ResponseEntity<Object> addNewPumpToTank(PumpDto pumpDto){
        Optional<Tank> optionalTank = tankRepository.findByTankId(pumpDto.getTank());
        Optional<PumpType> optionalPumpType = pumpTypeRepository.findByType(pumpDto.getType());
        if(!optionalTank.isPresent()){
            return new ResponseEntity<>("Tank not found", HttpStatus.EXPECTATION_FAILED);
        }
        else if(!optionalPumpType.isPresent()){
            return new ResponseEntity<>( "Pump Type not found", HttpStatus.EXPECTATION_FAILED);
        }
        else {
            Pump pump = pumpRepository.save(Pump.builder()
                    .pumpId(idGenerator.pumpIdGenerate())
                    .tank(optionalTank.get())
                    .type(optionalPumpType.get())
                    .build());

            generatePumpSide( pumpDto.getSides(), pump);
            return new ResponseEntity<>(pump , HttpStatus.OK);
        }

    }

    public ResponseEntity<Object> addNewTank(TankDto tankDto){
        Optional<Location> optionalLocation = locationRepository.findByLocationId(tankDto.getLocation());
        Optional<Product> optionalProduct = productRepository.findByName(tankDto.getProduct());
        if(optionalLocation.isPresent()) {
            if(optionalProduct.isPresent()) {
                tankRepository.save( Tank.builder()
                        .capacity( tankDto.getCapacity() )
                        .location(optionalLocation.get() )
                        .referencedHeight(tankDto.getReferenceHeight())
                        .product(optionalProduct.get())
                        .tankId(idGenerator.tankIdGenerate() )
                        .build() );
                return null;
            }
            return new ResponseEntity<>( PRODUCT_NOT_FOUND, HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>( LOCATION_NOT_FOUND, HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<Object> getTanksByLocation(String locationId){
        Optional<Location> optionalLocation = locationRepository.findByLocationId(locationId);
        if(optionalLocation.isPresent()){
            return new ResponseEntity<>(tankRepository.findByLocation(optionalLocation.get()) , HttpStatus.OK );
        }
        return new ResponseEntity<>( LOCATION_NOT_FOUND, HttpStatus.EXPECTATION_FAILED);
    }

}
