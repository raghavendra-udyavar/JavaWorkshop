package models.structures;

public class ExchangeCurrencyVertex {
    public String exchange;
    public String currency;
    public int index;

    public ExchangeCurrencyVertex(String newExchange, String newCurrency){
        exchange = newExchange;
        currency = newCurrency;
    }
}
