package com.redenergy.simplenem12.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SimpleNem12Formatter {

    public static LocalDate formatDate(String dateString) {
        return LocalDate.parse(dateString,
                DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
}
