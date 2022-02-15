package formula1Board;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String startLogFileName = "src/main/resources/start.log";
        String endLogFileName = "src/main/resources/end.log";
        String abbrFileName = "src/main/resources/abbreviations.txt";
            
        BoardGenerator fg = new BoardGenerator();
        System.out.println(fg.buildBoard(startLogFileName, endLogFileName, abbrFileName));   
    }
}
