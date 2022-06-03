package com.ethbek.mezion.inventory.service.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/uptime")
@Tag(name = "ELK Management", description = "uptime endpoint")
public class UpTimeController {

    @GetMapping
    public String health() {
        return "ACK";
    }

}