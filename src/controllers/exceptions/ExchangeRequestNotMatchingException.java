package controllers.exceptions;

public class ExchangeRequestNotMatchingException extends  Exception {
    public ExchangeRequestNotMatchingException(String message){
        super(message);
    }
}
