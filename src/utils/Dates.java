package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dates {

    public static LocalDateTime getDateNow() {
        LocalDateTime date = LocalDateTime.now();
        String format = date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        return LocalDateTime.parse(format);
    }
}
