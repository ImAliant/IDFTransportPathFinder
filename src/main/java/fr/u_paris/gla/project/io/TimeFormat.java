package fr.u_paris.gla.project.io;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TimeFormat {
    private static final int MINUTES_INDEX = 1;
    private static final int SECONDS_INDEX = 2;

    private TimeFormat() {
        // utility class
    }

    // Convert a time in the format "mm(can be higher than 60):ss" to seconds
    public static Integer convertToSeconds(String time) {
        Pattern pattern = Pattern.compile("^(\\d+):([0-5]\\d)$");

        Matcher matcher = pattern.matcher(time);
        
        if (!matcher.matches()) {
            throw new IllegalArgumentException("The time format is not valid");
        }

        String minutes = matcher.group(MINUTES_INDEX);
        String secondes = matcher.group(SECONDS_INDEX);

        return Integer.parseInt(minutes) * 60 + Integer.parseInt(secondes);
    }
}
