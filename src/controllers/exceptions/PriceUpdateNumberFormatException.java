package controllers.exceptions;

public class PriceUpdateNumberFormatException extends NumberFormatException{
    public  PriceUpdateNumberFormatException(String message) { super(message); }
}
