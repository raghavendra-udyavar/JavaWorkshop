import handlers.IRequestHandler;
import handlers.PriceUpdateHandler;
import models.tables.EdgeWeightedDigraph;

class ExchangeRate {
    public static void main(String[] args) {

        String newPriceUpdateString = "2017-11-01T09:42:23+00:00 DBS SGD USD 1.00 0.76\n"
                + "2017-11-01T09:42:23+00:00 OCBC SGD RM 1.00 2.97\n"
                + "2017-11-01T09:42:23+00:00 CITI USD INR 1 64.39\n"
                + "2017-11-01T09:42:23+00:00 SBI INR SGD 1.00 0.020\n"
                + "2017-11-01T09:42:23+00:00 SBI INR USD 1.00 0.016";

        try {
            EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph();
            IRequestHandler handler = new PriceUpdateHandler(edgeWeightedDigraph);
            handler.HandleRequest(newPriceUpdateString);
        }catch (Exception ex) {

        }
    }
}