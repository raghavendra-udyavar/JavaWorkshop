package controllers.exceptions;

public class EmptyPriceUpdateException extends  Exception {
    public  EmptyPriceUpdateException(String message) {
        super(message);
    }
}
