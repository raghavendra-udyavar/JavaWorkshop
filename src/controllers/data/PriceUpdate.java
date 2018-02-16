package controllers.data;

import java.util.Date;

public class PriceUpdate {
    Date date;
    String exchangeName;
    String sourceCurrency;
    String destinationCurrency;
    double forwardRate;
    double backwardRate;

    public PriceUpdate(String currExchangeName, String currSourceCurrency, String currDestinationCurrency, double currForwardRate, double currBackwardRate){
        exchangeName = currExchangeName;
        sourceCurrency = currSourceCurrency;
        destinationCurrency = currDestinationCurrency;
        forwardRate = currForwardRate;
        backwardRate = currBackwardRate;
    }


}