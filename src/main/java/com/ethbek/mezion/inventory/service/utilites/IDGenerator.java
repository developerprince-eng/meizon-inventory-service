package com.ethbek.mezion.inventory.service.utilites;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

@Component
public class IDGenerator {

     private static final String TANK_INTIAL = "MZTK-";
     private static final String PUMP_INTIAL = "MZPM-";
     private static final String PUMP_SIDE_INTIAL = "MZPMS-";
     private static final String PRODUCT_INTIAL = "MZPR-";
     private static final String PUMP_TYPE_INITIAL = "MZPTY-";

    public String uuidGenerate(int length){
        UUID uuid = UUID.randomUUID();
        return Long.toString(uuid.getMostSignificantBits(), length) + '-' + Long.toString(uuid.getLeastSignificantBits(), length);
    }

    public String tankIdGenerate(){
        Random random = new Random();
        Integer suffix = positiveRandomInt(2000, 8000);
        return TANK_INTIAL.concat(suffix.toString());
    }

    public String pumpIdGenerate(){
        Random random = new Random();
        Integer suffix = positiveRandomInt(2000, 8000);
        return PUMP_INTIAL.concat(suffix.toString());
    }

    public String productIdGenerate(){
        Random random = new Random();
        Integer suffix = positiveRandomInt(2000, 8000);
        return PRODUCT_INTIAL.concat(suffix.toString());
    }

    public String pumpTypeIdGenerate(){
        Random random = new Random();
        Integer suffix = positiveRandomInt(2000, 8000);
        return PUMP_TYPE_INITIAL.concat(suffix.toString());
    }

    public String pumpSideGenerate(){
        Random random = new Random();
        Integer suffix = positiveRandomInt(1000, 8000);
        return PUMP_SIDE_INTIAL.concat(suffix.toString());
    }

    private int positiveRandomInt(int min, int max) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(max-min) + min;
    }




}
