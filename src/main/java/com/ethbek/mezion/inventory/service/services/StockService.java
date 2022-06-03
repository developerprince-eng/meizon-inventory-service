package com.ethbek.mezion.inventory.service.services;

import com.ethbek.mezion.inventory.service.models.entites.Location;
import com.ethbek.mezion.inventory.service.repositories.LocationRepository;
import com.ethbek.mezion.inventory.service.repositories.StockRepository;
import com.ethbek.mezion.inventory.service.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {

    private static final String deliveryDate = "delivery_date";

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SupplierRepository supplierRepository;


    public ResponseEntity<Object> retrieveStockByLocation(String locationId, int size, int page){
        Pageable paging = PageRequest.of( page, size, Sort.by(deliveryDate).descending() );
        Optional<Location> location = locationRepository.findByLocationId(locationId);
        if(location.isPresent()){
            return new ResponseEntity<>( stockRepository.findAllByLocation( location.get(), paging ), HttpStatus.OK );
        }
        return new ResponseEntity<>( "No Such Location", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> addStockByLocation(String locationId, int size, int page){
        Pageable paging = PageRequest.of( page, size, Sort.by(deliveryDate).descending() );
        Optional<Location> location = locationRepository.findByLocationId(locationId);
        if(location.isPresent()){
            return new ResponseEntity<>( stockRepository.findAllByLocation( location.get(), paging ), HttpStatus.OK );
        }
        return new ResponseEntity<>( "No Such Location", HttpStatus.NOT_FOUND);
    }
}
