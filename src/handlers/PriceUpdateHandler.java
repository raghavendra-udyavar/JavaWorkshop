package handlers;

import controllers.data.PriceUpdate;
import controllers.serializers.PriceUpdateDeserializer;

import java.util.List;

public class PriceUpdateHandler implements IRequestHandler
{
    @Override
    public void HandleRequest(String input) {
        try {
            PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();
            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserialize(input);

        }catch (Exception ex){

        }
    }
}
