package formula1Board;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import formula1Board.model.Lap;
import formula1Board.model.Racer;
import formula1Board.service.FileReader;
import formula1Board.service.FormatConstants;
import formula1Board.service.Validator;

public class BoardGenerator {
    private static final int BORDERLINE = 15;
    private static final int TAB = 9;
    
    public String buildBoard(String startLogFileName, String endLogFileName, String abbrFileName) throws FileNotFoundException {
        
        try {
            FileReader reader = new FileReader();
            
            List<String> startList = reader.readFile(startLogFileName);
            List<String> endList = reader.readFile(endLogFileName);
            List<String> abbreviationsList = reader.readFile(abbrFileName);
            
            StringBuilder builder = new StringBuilder();
            
            int maxTeamNameLength = generateRacerFromLine(abbreviationsList).stream().mapToInt(racer -> racer.getTeamName().length()).max().getAsInt();
            int maxRacerNameLength = generateRacerFromLine(abbreviationsList).stream().mapToInt(racer -> racer.getName().length()).max().getAsInt();

            generateLapsInfo(startList, endList, abbreviationsList)
            .sorted()
            .forEachOrdered(new Consumer<Lap>() {
                int index = 1;
                
                public void accept(Lap lap) {
                    builder.append(generateBoard(lap, maxRacerNameLength, maxTeamNameLength, index));
                    index++;
                }
            });
            return builder.toString();
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
    
    private Stream<Lap> generateLapsInfo(List<String> startList, 
        List<String> endList, List<String> abbreviationsList) {
        
        return generateRacerFromLine(abbreviationsList).stream().map(racer -> {
            LocalDateTime startTime = parseLapInfo(startList, racer);
            LocalDateTime endTime = parseLapInfo(endList, racer);
            
            Duration lapDuration = Duration.between(startTime, endTime);
            
            return new Lap(startTime, endTime, lapDuration, racer);
        });
    }
    
    private List<Racer> generateRacerFromLine(List<String> lines) {
        Validator validator = new Validator();
        List<Racer> racers =  lines.stream()
                .map(validator::parseRacer)
                .collect(Collectors.toList());
        
        return racers;
    }
    
    private LocalDateTime parseLapInfo(List<String> list, Racer racer) {
         Validator parser = new Validator();
        
        return parser.parseTimeDateFromString(list.stream()
                .filter(line -> line.startsWith(racer.getAbbreviations()))
                .findAny().orElseThrow(() -> new NoSuchElementException("No element in start.log"))
                .substring(3));
    }    
    
    private String generateBoard(Lap lap, int maxRacerNameLength, int maxTeamNameLength, int index) {
        StringBuilder builder = new StringBuilder();
        
        LocalTime time = LocalTime.ofNanoOfDay(lap.getFastestLap().toNanos());
        String timeOutput = time.format(FormatConstants.BOARD_TIME_FORMAT_PATTERN);
        
        int spacesName = maxRacerNameLength - lap.getRacer().getName().length();
        int spacesTeam = maxTeamNameLength - lap.getRacer().getTeamName().length();
        
        if (index > TAB) {
            spacesName--;
        }
        
        String line = String.format("%d. %s%s | %s%s | %s", index, lap.getRacer().getName(), 
                repeatChar(spacesName, ' '), lap.getRacer().getTeamName(), repeatChar(spacesTeam, ' '), timeOutput);
        
        builder.append(line).append("\n");
        
        if (index == BORDERLINE) {
            builder.append(repeatChar(line.length(), '-')).append("\n");
        }
        return builder.toString();
    }
    
    private String repeatChar(int length, char ch) {
        StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            builder.append(ch);
        }
        return builder.toString();
    }
}
