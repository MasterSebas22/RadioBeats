package co.edu.unbosque.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeGenerator {

    /**
     * Retrives the system local date
     *
     * @return the system local date
     */
    public static String retriveLocalDate() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d_MMM_uuuu");
        LocalDate date = LocalDate.now();

        return dateFormat.format(date);
    }

    /**
     * Retrives the system local time
     *
     * @return the system local time
     */
    public static String retriveLocalTime() {
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:m a");
        LocalTime time = LocalTime.now();

        return timeFormat.format(time);
    }
}
