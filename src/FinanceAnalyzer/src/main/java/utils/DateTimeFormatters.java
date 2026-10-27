package utils;

import java.time.format.DateTimeFormatter;

public final class DateTimeFormatters {

    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter MONTH_YEAR = DateTimeFormatter.ofPattern("MM-yyyy");
    public static final DateTimeFormatter DATE_DISPLAY = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    public static final DateTimeFormatter MONTH_YEAR_DISPLAY = DateTimeFormatter.ofPattern("MMMM yyyy");
}
