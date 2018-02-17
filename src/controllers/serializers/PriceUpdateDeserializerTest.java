package controllers.serializers;

import controllers.data.PriceUpdate;
import controllers.exceptions.EmptyPriceUpdateException;
import controllers.exceptions.PriceUpdateFormatException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceUpdateDeserializerTest {

    PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();

    String newPriceUpdateString = "2017-11-01T09:42:23+00:00 KRAKEN BTC USD 1000.0 0.0009\n"
            + "2017-11-01T09:42:23+00:00 ETH SGD 1001.0 0.0008\n"
            + "2017-11-01T09:42:23+00:00 GEMINI BTC USD 1002.0 0.0005\n"
            + "2017-11-01T09:42:23+00:00 BITCXINDIA ALT INR 1003.0 0.0005\n"
            + "2017-11-01T09:42:23+00:00 COINCHECK BTC USD 1004.0 0.0005";

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
        assertThrows(EmptyPriceUpdateException.class, () -> { priceUpdateDeserializer.isInputNullOrEmpty(null); }, "price update is null");
    }

    @Test
    void isPriceUpdateInputEmpty(){
        assertThrows(EmptyPriceUpdateException.class, () -> { priceUpdateDeserializer.isInputNullOrEmpty(""); }, "price update is null");
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
            String input = "2017-11-01T09:42:23+00:00 KRAKEN BTC USD some some\n";

            String[] priceUpdates = priceUpdateDeserializer.getPriceUpdates(input);
            for (String priceUpdate : priceUpdates) {
                String[] priceUpdateValues = priceUpdateDeserializer.getIndividualPriceUpdate(priceUpdate);
                priceUpdateDeserializer.constructPriceUpdate(priceUpdateValues);} }, "format not right");
    }
}
