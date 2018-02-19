package controllers.serializers;

import controllers.calculator.ExchangeRateCalculator;
import controllers.exceptions.EmptyInputException;
import controllers.exceptions.EmptyInputException;
import controllers.exceptions.IncompleteInputException;
import models.structures.ExchangeCurrencyVertex;
import models.structures.ExchangeRateRequest;
import models.tables.EdgeWeightedDigraph;

public class ExchangeRateRequestDeserializer<T> implements  IDeserializer {

    static final int EXCHANGERATEINPUTCOUNT = 5;

    EdgeWeightedDigraph edgeWeightedDigraph;
    ExchangeRateCalculator exchangeRateCalculator;

    public ExchangeRateRequestDeserializer(EdgeWeightedDigraph digraph, ExchangeRateCalculator calculator){
        edgeWeightedDigraph = digraph;
        exchangeRateCalculator = calculator;
    }

    @Override
    public T deserialize(String input) throws Exception {
        if(!isInputNullOrEmpty(input)){
            String[] exchangeRequestStrings = breakInput(input);
            if(exchangeRequestStrings.length == EXCHANGERATEINPUTCOUNT){
                String sourceExchange = exchangeRequestStrings[1];
                String sourceCurrency = exchangeRequestStrings[2];
                String destinationExchange = exchangeRequestStrings[3];
                String destinationCurrency = exchangeRequestStrings[4];

                ExchangeCurrencyVertex sourceExchangeCurrency = new ExchangeCurrencyVertex(sourceExchange, sourceCurrency);
                ExchangeCurrencyVertex destinationExchangeCurrency = new ExchangeCurrencyVertex(destinationExchange, destinationCurrency);

                ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest(sourceExchangeCurrency, destinationExchangeCurrency);
                exchangeRateRequest.updateIndexForBothVertex(edgeWeightedDigraph);

                return (T)exchangeRateRequest;
            } else {
                throw new IncompleteInputException("exchange rate is missing some information");
            }
        }

        return null;
    }

    boolean isInputNullOrEmpty(String input) throws EmptyInputException {
        if(input == null){
            throw new EmptyInputException("ExchangeRateRequest cannot be null");
        } else if(input.isEmpty()){
            throw new EmptyInputException("ExchangeRateRequest cannot be empty");
        } else {
            return false;
        }
    }

    String[] breakInput(String input) {
        return input.split(" ");
    }
}
