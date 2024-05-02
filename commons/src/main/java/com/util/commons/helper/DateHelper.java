package com.util.commons.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateHelper {

    public static LocalDate parseDateFrom(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateString, formatter);
    }

    public static LocalDate parseDateFrom(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    public static Date parseAtDateFrom(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
    public static LocalDate parseAtLocalDateFrom(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        return LocalDate.parse(formattedDate, formatter);
    }

}
