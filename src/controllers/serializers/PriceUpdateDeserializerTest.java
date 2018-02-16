package controllers.serializers;

import controllers.exceptions.EmptyPriceUpdateException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class PriceUpdateDeserializerTest {

    @Test
    void deserialize() {
    }

    @Test
    void isPriceUpdateInputNull() {

        try {
            PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();
            priceUpdateDeserializer.isPriceUpdateInputNullOrEmpty(null);
        } catch (Exception ex) {
            assertThrows(EmptyPriceUpdateException.class, () -> { throw new EmptyPriceUpdateException("PriceUpdateInput cannot be null"); }, "price update is null");
        }
    }

    @Test
    void isPriceUpdateInputEmpty(){
        try {
            PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();
            priceUpdateDeserializer.isPriceUpdateInputNullOrEmpty("");
        } catch (Exception ex) {
            assertThrows(EmptyPriceUpdateException.class, () -> { throw new EmptyPriceUpdateException("PriceUpdateInput cannot be null"); }, "price update is null");
        }
    }
}