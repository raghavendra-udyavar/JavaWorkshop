import controllers.calculator.ExchangeRateCalculator;
import handlers.ExchangeRequestHandler;
import handlers.IRequestHandler;
import handlers.PriceUpdateHandler;
import models.tables.EdgeWeightedDigraph;

class ExchangeRate {
    public static void main(String[] args) {

        String priceUpdateInput = "2017-11-01T09:42:23+00:00 DBS USD RMB 1000.0 0.0009\n"
                + "2017-11-01T09:42:23+00:00 CITI USD SGD 1001.0 0.0008\n"
                + "2017-11-01T09:42:23+00:00 SBI INR USD 1003.0 0.0005";

        try {
            EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph();
            ExchangeRateCalculator exchangeRateCalculator = new ExchangeRateCalculator(edgeWeightedDigraph);

            IRequestHandler handler = new PriceUpdateHandler(edgeWeightedDigraph, exchangeRateCalculator);
            handler.HandleRequest(priceUpdateInput);

            String exchangeRequestInput =  "EXCHANGE_RATE_REQUEST CITI USD SBI INR";
            ExchangeRequestHandler exchangeRequestHandler = new ExchangeRequestHandler(edgeWeightedDigraph, exchangeRateCalculator);
            exchangeRequestHandler.HandleRequest(exchangeRequestInput);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}