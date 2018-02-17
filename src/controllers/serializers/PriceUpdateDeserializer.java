package controllers.serializers;

import controllers.exceptions.EmptyPriceUpdateException;
import controllers.data.PriceUpdate;
import controllers.exceptions.PriceUpdateFormatException;

import java.util.ArrayList;
import java.util.List;

// Deserialize PriceUpdate input
// PriceUpdate typically is in the form mentioned below with multiple lines separated by newline character
// <TimeStamp> <Exchange> <Source currency> <Destination Currency> <Forward rate> <Backward rate>
public class PriceUpdateDeserializer implements IDeserializer {

    static final int PRICEUPDATECOUNT = 6;

    public List<PriceUpdate> deserialize(String input) throws Exception{

        System.out.println("Deserialization");
        List<PriceUpdate> deserializedPriceUpdates = new ArrayList<PriceUpdate>();

        // check if the priceupdate is empty
        if(!isInputNullOrEmpty(input)){
            String[] priceUpdates = getPriceUpdates(input);
            for (String priceUpdate : priceUpdates) {
                String[] priceUpdateValues = getIndividualPriceUpdate(priceUpdate);
                PriceUpdate deserializedPriceUpdate = constructPriceUpdate(priceUpdateValues);
                deserializedPriceUpdates.add(deserializedPriceUpdate);
            }
        }

        return  null;
    }

    PriceUpdate constructPriceUpdate(String[] priceUpdateString) throws PriceUpdateFormatException {
        String exchange = priceUpdateString[1];
        String sourceCurrency = priceUpdateString[2];
        String destinationCurrency = priceUpdateString[3];

        double forwardRate = 0.0f;

        try {
            forwardRate = Double.parseDouble(priceUpdateString[4]);
        } catch (Exception ex){
            throw new PriceUpdateFormatException("Incorrect forwardRate format");
        }

        double backwardRate = 0.0f;
        try {
            backwardRate = Double.parseDouble(priceUpdateString[5]);
        } catch (Exception ex){
            throw new PriceUpdateFormatException("Incorrect backwardRate format");
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