package controllers.serializers;

import models.structures.PriceUpdate;
import controllers.exceptions.EmptyPriceUpdateException;
import controllers.exceptions.PriceUpdateFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceUpdateDeserializerTest {

    PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();

    String newPriceUpdateString = "2017-11-01T09:42:23+00:00 DBS SGD USD 1.00 0.76\n"
            + "2017-11-01T09:42:23+00:00 OCBC SGD RM 1.00 2.97\n"
            + "2017-11-01T09:42:23+00:00 CITI USD INR 1 64.39\n"
            + "2017-11-01T09:42:23+00:00 SBI INR SGD 1.00 0.020\n"
            + "2017-11-01T09:42:23+00:00 SBI INR USD 1.00 0.016";

    @Test
    void deserialize(){

        try {
            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserialize(newPriceUpdateString);
            assertEquals(5, priceUpdates.length);
        } catch (Exception ex){

        }
    }

    @Test
    void isPriceUpdateInputNull() {
        assertThrows(EmptyPriceUpdateException.class, () -> priceUpdateDeserializer.isInputNullOrEmpty(null), "price update is null");
    }

    @Test
    void isPriceUpdateInputEmpty(){
        assertThrows(EmptyPriceUpdateException.class, () -> priceUpdateDeserializer.isInputNullOrEmpty(""), "price update is null");
    }

    @Test
    void checkPriceUpdateCount(){

        String[] totalPriceUpdates = priceUpdateDeserializer.getPriceUpdates(newPriceUpdateString);
        assertEquals(5, totalPriceUpdates.length);
    }

    @Test
    void checkIndividualPriceUpdate() {
        String[] totalPriceUpdates = priceUpdateDeserializer.getPriceUpdates(newPriceUpdateString);
        String[] firstPriceUpdateValues = priceUpdateDeserializer.getIndividualPriceUpdate(totalPriceUpdates[0]);
        assertEquals(6, firstPriceUpdateValues.length);
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
