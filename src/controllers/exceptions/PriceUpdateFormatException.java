package controllers.exceptions;

public class PriceUpdateFormatException extends NumberFormatException {
    public  PriceUpdateFormatException(String message) { super(message); }
}
