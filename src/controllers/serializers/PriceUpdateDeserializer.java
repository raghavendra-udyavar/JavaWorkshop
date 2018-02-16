package controllers.serializers;

import controllers.exceptions.EmptyPriceUpdateException;
import controllers.data.PriceUpdate;
import controllers.exceptions.PriceUpdateNumberFormatException;

import java.util.List;

// Deserialize PriceUpdate input
// PriceUpdate typically is in the form mentioned below with multiple lines separated by newline character
// <TimeStamp> <Exchange> <Source currency> <Destination Currency> <Forward rate> <Backward rate>
public class PriceUpdateDeserializer implements IDeserializer {

    static final int PRICEUPDATECOUNT = 6;

    public List<PriceUpdate> deserialize(String input) throws Exception{

        System.out.println("Deserialization");

        // check if the priceupdate is empty
        if(isInputInRightFormat(input)) {
            // check if the format is right

        }
        return  null;
    }

    PriceUpdate constructPriceUpdate(String[] priceUpdateString) throws PriceUpdateNumberFormatException{
        String exchange = priceUpdateString[1];
        String sourceCurrency = priceUpdateString[2];
        String destinationCurrency = priceUpdateString[3];

        double forwardRate = 0.0f;
        try {
            forwardRate = Double.parseDouble(priceUpdateString[4]);
        } catch (Exception ex){
            throw new PriceUpdateNumberFormatException("Incorrect forwardRate format");
        }

        double backwardRate = 0.0f;
        try {
            backwardRate = Double.parseDouble(priceUpdateString[5]);
        } catch (Exception ex){
            throw new PriceUpdateNumberFormatException("Incorrect backwardRate format");
        }

        return new PriceUpdate(exchange, sourceCurrency, destinationCurrency, forwardRate, backwardRate);

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
            String[] priceUpdates = getPriceUpdates(input);
            for (String priceUpdate : priceUpdates) {
                String[] priceUpdateValues = getIndividualPriceUpdate(priceUpdate);

            }
        }
        return false;
    }

    String[] getPriceUpdates(String input){

        // multiple priceupdates are separated by a newline character
        return input.split("\n");
    }

    String[] getIndividualPriceUpdate(String input){

        // separate the priceupdate from the input string
        String[] priceUpdateStrings = input.split(" ");
        return priceUpdateStrings;
    }
}