package controllers.exceptions;

public class IncompleteInputException extends Exception {
    public IncompleteInputException(String message){
        super(message);
    }
}
