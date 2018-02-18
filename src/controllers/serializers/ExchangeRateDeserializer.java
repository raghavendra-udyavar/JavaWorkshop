package controllers.serializers;

import controllers.exceptions.EmptyInputException;
import controllers.exceptions.EmptyInputException;

public class ExchangeRateDeserializer<T> implements  IDeserializer {

    @Override
    public T deserialize(String input) throws Exception {


        return null;
    }

    boolean isInputNullOrEmpty(String input) throws EmptyInputException {
        if(input == null){
            throw new EmptyInputException("ExchangeRateRequest cannot be null");
        } else if(input.isEmpty()){
            throw new EmptyInputException("ExchangeRateRequest cannot be empty");
        } else {
            return false;
        }
    }
}
