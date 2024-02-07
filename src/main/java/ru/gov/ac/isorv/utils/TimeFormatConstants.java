package ru.gov.ac.isorv.utils;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;

/**
 * Утилитный класс, содержащий константы и форматтеры для работы с датой и временем.
 */
@UtilityClass
public class TimeFormatConstants {
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT);
}
