import controllers.calculator.ExchangeRateCalculator;
import handlers.ExchangeRequestHandler;
import handlers.IRequestHandler;
import handlers.PriceUpdateHandler;
import models.tables.EdgeWeightedDigraph;

class ExchangeRate {
    public static void main(String[] args) {

        String priceUpdateInput = "2017-11-01T09:42:23+00:00 DBS SGD USD 1.00 0.76\n"
                + "2017-11-01T09:42:23+00:00 OCBC SGD RM 1.00 2.97\n"
                + "2017-11-01T09:42:23+00:00 CITI USD INR 1 64.39\n"
                + "2017-11-01T09:42:23+00:00 SBI INR SGD 1.00 0.020\n"
                + "2017-11-01T09:42:23+00:00 SBI INR USD 1.00 0.016";

        try {
            EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph();
            ExchangeRateCalculator exchangeRateCalculator = new ExchangeRateCalculator(edgeWeightedDigraph);

            IRequestHandler handler = new PriceUpdateHandler(edgeWeightedDigraph, exchangeRateCalculator);
            handler.HandleRequest(priceUpdateInput);

            String exchangeRequestInput =  "EXCHANGE_RATE_REQUEST DBS SGD SBI USD";
            ExchangeRequestHandler exchangeRequestHandler = new ExchangeRequestHandler(edgeWeightedDigraph, exchangeRateCalculator);
            exchangeRequestHandler.HandleRequest(exchangeRequestInput);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}