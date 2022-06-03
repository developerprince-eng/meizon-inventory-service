package com.ethbek.mezion.inventory.service.models.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DailyReadingDto implements Serializable {
    private static final long serialVersionUID = 73739832653L;
    private Integer openMeasurement;
    private Integer closeMeasurement;
}
