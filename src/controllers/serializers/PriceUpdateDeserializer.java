package controllers.serializers;

import controllers.exceptions.EmptyPriceUpdateException;
import data.PriceUpdate;

// Deserialize PriceUpdate input
// PriceUpdate typically is in the form mentioned below with multiple lines separated by newline character
// <TimeStamp> <Exchange> <Source currency> <Destination Currency> <Forward factor> <Backward Factor>
public class PriceUpdateDeserializer implements IDeserializer {

    public PriceUpdate deserialize(String priceUpdateInput) throws Exception{

        System.out.println("Deserialization");

        // check if the priceupdate is empty
        if(!isPriceUpdateInputNullOrEmpty(priceUpdateInput)) {
            // check if the format is right

        }
        return  null;
    }

    boolean isPriceUpdateInputNullOrEmpty(String priceUpdateInput) throws EmptyPriceUpdateException{
        if(priceUpdateInput == null){
            throw new EmptyPriceUpdateException("PriceUpdateInput cannot be null");
        } else if(priceUpdateInput.isEmpty()){
            throw new EmptyPriceUpdateException("PriceUpdateInput cannot be empty");
        } else {
            return false;
        }
    }
}