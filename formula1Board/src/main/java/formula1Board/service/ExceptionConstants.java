package formula1Board.service;

public enum ExceptionConstants {
    EMPTY_FILE_NAME("File name can't be empty"),
    EMPTY_ARGUMENT("Argument can't be empty or null"),
    ARGUMENT_FORMAT("Wrong argument format"),
    NO_FILE("No file"),
    EMPTY_FILE("File can't be empty");
    
    private String title;

    ExceptionConstants(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    @Override
    public String toString() {
        return title;
    }
}
