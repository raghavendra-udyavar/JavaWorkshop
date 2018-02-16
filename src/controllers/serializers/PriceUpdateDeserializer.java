package controllers.serializers;

import controllers.exceptions.EmptyPriceUpdateException;
import data.PriceUpdate;

// Deserialize PriceUpdate input
// PriceUpdate typically is in the form mentioned below with multiple lines separated by newline character
// <TimeStamp> <Exchange> <Source currency> <Destination Currency> <Forward factor> <Backward Factor>
public class PriceUpdateDeserializer implements IDeserializer {

    public PriceUpdate deserialize(String input) throws Exception{

        System.out.println("Deserialization");

        // check if the priceupdate is empty
        if(isInputInRightFormat(input)) {
            // check if the format is right

        }
        return  null;
    }

    boolean isInputNullOrEmpty(String input) throws EmptyPriceUpdateException {
        if(input == null){
            throw new EmptyPriceUpdateException("PriceUpdateInput cannot be null");
        } else if(input.isEmpty()){
            throw new EmptyPriceUpdateException("PriceUpdateInput cannot be empty");
        } else {
            return false;
        }
    }

    boolean isInputInRightFormat(String input) throws EmptyPriceUpdateException {
        if(!isInputNullOrEmpty(input)){

        }
        return false;
    }

    String[] getTotalPriceUpdates(String input){

        // multiple priceupdates are separated by a newline character
        return input.split("\n");
    }

    String[] getIndividualPriceUpdate(String input){

        // separate the priceupdate from the input string
        String[] priceUpdateStrings = input.split(" ");
        return priceUpdateStrings;
    }
}