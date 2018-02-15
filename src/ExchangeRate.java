import Runnable.PriceUpdateRunnable;
import controllers.deserializers.PriceUpdateDeserializer;

import java.util.List;
import java.util.*;

class ExchangeRate {
    public static void main(String[] args) {
        System.out.println("Hello! World");

        String input = "Hello! World";

        PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();
        priceUpdateDeserializer.Deserialize(input);

        System.out.println("Done!!");
    }
}