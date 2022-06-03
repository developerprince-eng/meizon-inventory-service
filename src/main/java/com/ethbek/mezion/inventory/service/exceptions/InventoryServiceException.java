package com.ethbek.mezion.inventory.service.exceptions;

public class InventoryServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public InventoryServiceException(String message){
        super(message);
    }
}
