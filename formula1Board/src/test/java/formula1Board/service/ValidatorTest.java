package formula1Board.service;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.Test;

import formula1Board.model.Racer;

class ValidatorTest {
    private Validator validator = new Validator();
    
    @Test
    void shouldReturnRacerFromString() {
        
        Racer actual = validator.parseRacer("SVF_Sebastian Vettel_FERRARI");
        String expectedAbbr = "SVF";
        String expectedFullName = "Sebastian Vettel";
        String expectedTeamName = "FERRARI";
        assertThat(actual, IsInstanceOf.instanceOf(Racer.class));
        
        assertEquals(actual.getAbbreviations(), expectedAbbr);
        assertEquals(actual.getName(), expectedFullName);
        assertEquals(actual.getTeamName(), expectedTeamName);
    }
    
    @Test
    void shouldReturnIllArgExceptionWhenArgumentIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.parseRacer("");
        });
    }
    
    @Test
    void shouldReturnIllArgExceptionWhenArgumentHaveWrongFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.parseRacer("aa");
        });
    }
    
    @Test
    void shouldReturnLocalDateTime() {
        LocalDateTime actual = validator.parseTimeDateFromString("2018-05-24_12:04:03.332");
        assertThat(actual, IsInstanceOf.instanceOf(LocalDateTime.class));
    }
    
    @Test
    void shouldReturnIllArgExceptionWhenArgIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.parseRacer("");
        });
    }
    
    @Test
    void shouldReturnIllArgExceptionWhenTimeFormatWrong() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.parseRacer("dd");
        });
    }  
}
