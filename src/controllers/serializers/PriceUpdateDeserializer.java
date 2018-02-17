package controllers.serializers;

import controllers.exceptions.EmptyPriceUpdateException;
import models.structures.PriceUpdate;
import controllers.exceptions.PriceUpdateFormatException;

// Deserialize PriceUpdate input
// PriceUpdate typically is in the form mentioned below with multiple lines separated by newline character
// <TimeStamp> <Exchange> <Source currency> <Destination Currency> <Forward rate> <Backward rate>
public class PriceUpdateDeserializer<T> implements IDeserializer {

    private static final int PRICEUPDATECOUNT = 6;

    @Override
    public T[] deserialize(String input) throws Exception{

        System.out.println("Deserialization");

        // check if the priceupdate is empty
        if(!isInputNullOrEmpty(input)){
            String[] priceUpdates = getPriceUpdates(input);
            PriceUpdate[] deserializedPriceUpdates = new PriceUpdate[priceUpdates.length];
            int i = 0;
            for (String priceUpdate : priceUpdates) {
                String[] priceUpdateValues = getIndividualPriceUpdate(priceUpdate);

                if(priceUpdateValues.length != PRICEUPDATECOUNT){
                    throw new PriceUpdateFormatException("Price update value is incomplete");
                }

                PriceUpdate deserializedPriceUpdate = constructPriceUpdate(priceUpdateValues);
                deserializedPriceUpdates[i] = deserializedPriceUpdate;
                i++;
            }
        }

        return  null;
    }

    PriceUpdate constructPriceUpdate(String[] priceUpdateString) throws PriceUpdateFormatException {
        String exchange = priceUpdateString[1];
        String sourceCurrency = priceUpdateString[2];
        String destinationCurrency = priceUpdateString[3];

        double forwardRate;

        try {
            forwardRate = Double.parseDouble(priceUpdateString[4]);
        } catch (Exception ex){
            throw new PriceUpdateFormatException("Incorrect forwardRate format");
        }

        double backwardRate;
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