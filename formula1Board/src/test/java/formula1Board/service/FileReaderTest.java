package formula1Board.service;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

class FileReaderTest {
    private FileReader reader = new FileReader();
 
    @Test
    void shouldReturnLengthOfReadedLines() throws IOException {
        int expected = 19;
        List<String> actual = reader.readFile("src\\test\\resources\\startTest.log");
        assertEquals(expected, actual.size());
    }
    
    @Test
    void shouldReturnNonEmptyLines() throws IOException {
        Random random = new Random();
        List<String> list = reader.readFile("src\\test\\resources\\startTest.log");
        
        String actual = list.get(random.nextInt(18));
        assertTrue(StringUtils.isNotBlank(actual));
    }
    
    @Test
    void shouldReturnNonEmptyLine() throws IOException {
        List<String> list = reader.readFile("src\\test\\resources\\startTest.log");
        
        String actual = list.get(0);
        String expected = "SVF2018-05-24_12:02:58.917";
        assertEquals(actual.length(), expected.length());
    }
    
    @Test
    void shouldReturnIllArgExcWhenFileNameIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            reader.readFile("");
        });
    }
}
