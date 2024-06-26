package com.ironman.restaurantmanagement.shared.util;

import java.time.LocalDate;

public class DateHelper {

    public static String localDateToString(LocalDate date) {
        return date != null ? date.toString() : null;
    }
}
