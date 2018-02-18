package handlers;

import controllers.calculator.ExchangeRateCalculator;
import controllers.serializers.ExchangeRateRequestDeserializer;
import models.structures.ExchangeCurrencyVertex;
import models.structures.ExchangeRateRequest;
import models.tables.EdgeWeightedDigraph;

import java.util.List;

public class ExchangeRequestHandler implements  IRequestHandler {

    EdgeWeightedDigraph edgeWeightedDigraph;
    ExchangeRateCalculator exchangeRateCalculator;

    public ExchangeRequestHandler(EdgeWeightedDigraph edgeWeightedDigraphObj, ExchangeRateCalculator exchangeRateCalculatorObj){
        edgeWeightedDigraph = edgeWeightedDigraphObj;
        exchangeRateCalculator = exchangeRateCalculatorObj;
    }

    @Override
    public void HandleRequest(String input) throws Exception {
        ExchangeRateRequestDeserializer exchangeRateRequestDeserializer = new ExchangeRateRequestDeserializer(edgeWeightedDigraph, exchangeRateCalculator);
        ExchangeRateRequest exchangeRateRequest = (ExchangeRateRequest) exchangeRateRequestDeserializer.deserialize(input);

        // get best exchangerate and the path taken for the best rate
        double bestRate = exchangeRateCalculator.GetBestExchangeRate(exchangeRateRequest.GetSourceExchangeCurrencyIndex(), exchangeRateRequest.GetDestinationExchangeCurrencyIndex());
        List<ExchangeCurrencyVertex> bestPathTaken = exchangeRateCalculator.pathTaken(exchangeRateRequest.GetSourceExchangeCurrencyIndex(), exchangeRateRequest.GetDestinationExchangeCurrencyIndex());

        System.out.printf("done");
    }
}
