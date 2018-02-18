package controllers.serializers;

import controllers.exceptions.IncompleteInputException;
import models.structures.PriceUpdate;
import controllers.exceptions.EmptyInputException;
import controllers.exceptions.PriceUpdateFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceUpdateDeserializerTest {

    PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();

    String newPriceUpdateString = "2017-11-01T09:42:23+00:00 DBS SGD USD 1.00 0.76\n"
            + "2017-11-01T09:42:23+00:00 OCBC SGD RM 1.00 2.97\n"
            + "2017-11-01T09:42:23+00:00 CITI USD INR 1 64.39\n"
            + "2017-11-01T09:42:23+00:00 SBI INR SGD 1.00 0.020";

    @Test
    void deserialize(){

        try {
            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserializeMultiline(newPriceUpdateString);
            assertEquals(4, priceUpdates.length);
        } catch (Exception ex){
            assertThrows(Exception.class, () -> { throw new Exception(); }, "message");
        }
    }

    @Test
    void isPriceUpdateInputNull() {
        assertThrows(EmptyInputException.class, () -> priceUpdateDeserializer.isInputNullOrEmpty(null), "price update is null");
    }

    @Test
    void isPriceUpdateInputEmpty(){
        assertThrows(EmptyInputException.class, () -> priceUpdateDeserializer.isInputNullOrEmpty(""), "price update is null");
    }

    @Test
    void checkPriceUpdateCount(){
        String[] totalPriceUpdates = priceUpdateDeserializer.getPriceUpdates(newPriceUpdateString);
        assertEquals(4, totalPriceUpdates.length);
    }

    @Test
    void checkIndividualPriceUpdate() {
        String[] totalPriceUpdates = priceUpdateDeserializer.getPriceUpdates(newPriceUpdateString);
        String[] firstPriceUpdateValues = priceUpdateDeserializer.getIndividualPriceUpdate(totalPriceUpdates[0]);
        assertEquals(6, firstPriceUpdateValues.length);
    }

    @Test
    void checkIncompleteInput(){
        assertThrows(IncompleteInputException.class, ()-> {
            String input = "2017-11-01T09:42:23+00:00 DBS SGD USD 1.0";

            String[] priceUpdates = priceUpdateDeserializer.getPriceUpdates(input);
            for (String priceUpdate : priceUpdates) {
                 priceUpdateDeserializer.deserialize(priceUpdate);
                } }, "format not right");
    }

    @Test
    void checkPriceUpdateFormat(){
        assertThrows(PriceUpdateFormatException.class, ()-> {
            String input = "2017-11-01T09:42:23+00:00 DBS SGD USD some some\n";

            String[] priceUpdates = priceUpdateDeserializer.getPriceUpdates(input);
            for (String priceUpdate : priceUpdates) {
                String[] priceUpdateValues = priceUpdateDeserializer.getIndividualPriceUpdate(priceUpdate);
                priceUpdateDeserializer.constructPriceUpdate(priceUpdateValues);} }, "format not right");
    }
}
