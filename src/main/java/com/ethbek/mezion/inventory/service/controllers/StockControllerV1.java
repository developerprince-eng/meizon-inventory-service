package com.ethbek.mezion.inventory.service.controllers;

import com.ethbek.mezion.inventory.service.services.AccessControlService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/stock")
@Tag(name = "Stock Management", description = "Version 1 for Managing Stock for Meizon")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Stock Management", description = "Version 1 for Managing Stock for Meizon")
public class StockControllerV1 {

    @Autowired
    private AccessControlService accessControlService;
}
