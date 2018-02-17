import controllers.data.PriceUpdate;
import controllers.serializers.PriceUpdateDeserializer;
import handlers.IRequestHandler;
import handlers.PriceUpdateHandler;

import java.util.List;

class ExchangeRate {
    public static void main(String[] args) {

        String newPriceUpdateString = "2017-11-01T09:42:23+00:00 KRAKEN BTC USD 1000.0 0.0009\n"
                + "2017-11-01T09:42:23+00:00 GDAX ETH SGD 1001.0 0.0008\n"
                + "2017-11-01T09:42:23+00:00 GEMINI BTC USD 1002.0 0.0005\n"
                + "2017-11-01T09:42:23+00:00 BITCXINDIA ALT INR 1003.0 0.0005\n"
                + "2017-11-01T09:42:23+00:00 COINCHECK BTC USD 1004.0 0.0005";

        try {
            IRequestHandler handler = new PriceUpdateHandler();
            handler.HandleRequest(newPriceUpdateString);
        }catch (Exception ex) {

        }
    }
}