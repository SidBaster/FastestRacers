package formula1Board.service;

import java.time.LocalDateTime;
import formula1Board.model.Racer;

public class Validator {
    public static final String RACER_INFO = "^[A-Z]{3}_\\D+_\\D+$";
    public static final String DATE_TIME = "^\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}\\.\\d{3}$";
    
    public Racer parseRacer(String line) {
        String[] parameters = line.split("_");
        
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException(ExceptionConstants.valueOf("EMPTY_ARGUMENT").toString());
        }
        if (!line.matches(RACER_INFO)) {
            throw new IllegalArgumentException(ExceptionConstants.valueOf("ARGUMENT_FORMAT").toString());
        }
        
        return new Racer(parameters[0], parameters[1], parameters[2]);
    }
    
    public LocalDateTime parseTimeDateFromString(String dateTime) {
        if (dateTime == null || dateTime.isEmpty()) {
            throw new IllegalArgumentException(ExceptionConstants.valueOf("EMPTY_ARGUMENT").toString());
        }
        if (!dateTime.matches(DATE_TIME)) {
            throw new IllegalArgumentException(ExceptionConstants.valueOf("ARGUMENT_FORMAT").toString());
        }
        
        return LocalDateTime.parse(dateTime, FormatConstants.DATE_FORMAT_PATTERN);
    }
}
