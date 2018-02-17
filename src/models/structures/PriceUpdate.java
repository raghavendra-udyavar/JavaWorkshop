package models.structures;

import java.util.Date;

public class PriceUpdate {
    Date date;
    public String exchange;
    public String sourceCurrency;
    public String destinationCurrency;
    public double forwardRate;
    public double backwardRate;

    public PriceUpdate(String currExchange, String currSourceCurrency, String currDestinationCurrency, double currForwardRate, double currBackwardRate){
        exchange = currExchange;
        sourceCurrency = currSourceCurrency;
        destinationCurrency = currDestinationCurrency;
        forwardRate = currForwardRate;
        backwardRate = currBackwardRate;
    }


}