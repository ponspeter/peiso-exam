package com.peiso.exam.common.model;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class DateConverter {

    public Instant getInstant(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //convert String to LocalDate
        LocalDate localStartDate = LocalDate.parse(date, formatter);

        // Get default timezone
        ZoneId zoneId = ZoneId.systemDefault();
        // Convert LocalDate to Instant using toInstant() method
        Instant instant = localStartDate.atStartOfDay(zoneId).toInstant();
        return instant;
    }
}
