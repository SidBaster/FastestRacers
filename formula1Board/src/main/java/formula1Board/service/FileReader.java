package formula1Board.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    
    public List<String> readFile(String FileName) throws IOException {
        
        if (FileName == null || FileName.isEmpty()) {
            throw new IllegalArgumentException(ExceptionConstants.valueOf("EMPTY_FILE_NAME").toString());
        }
        try {
            List<String> lines = Files.lines(Paths.get(FileName)).collect(Collectors.toList());
        
            if (lines.isEmpty()) {
                throw new IllegalArgumentException(ExceptionConstants.valueOf("EMPTY_FILE").toString());
            }
            
            return lines;
        } catch (NullPointerException e) {
            throw new FileNotFoundException(ExceptionConstants.valueOf("NO_FILE").toString());
        }
    }
}
