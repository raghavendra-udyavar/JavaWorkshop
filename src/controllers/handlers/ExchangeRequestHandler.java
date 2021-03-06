package controllers.handlers;

import controllers.calculator.ExchangeRateCalculator;
import controllers.serializers.ExchangeRateRequestDeserializer;
import models.database.DataConnection;
import models.structures.ExchangeCurrencyVertex;
import models.structures.ExchangeRateRequest;
import models.tables.EdgeWeightedDigraph;

import java.util.List;

public class ExchangeRequestHandler implements  IRequestHandler {

    EdgeWeightedDigraph edgeWeightedDigraph;
    ExchangeRateCalculator exchangeRateCalculator;
    DataConnection dataConnection;

    public ExchangeRequestHandler(EdgeWeightedDigraph edgeWeightedDigraphObj, ExchangeRateCalculator exchangeRateCalculatorObj, DataConnection connection){
        edgeWeightedDigraph = edgeWeightedDigraphObj;
        exchangeRateCalculator = exchangeRateCalculatorObj;
        dataConnection = connection;
    }

    @Override
    public void HandleRequest(String input) throws Exception {
        ExchangeRateRequestDeserializer exchangeRateRequestDeserializer = new ExchangeRateRequestDeserializer(edgeWeightedDigraph, exchangeRateCalculator);
        ExchangeRateRequest exchangeRateRequest = (ExchangeRateRequest) exchangeRateRequestDeserializer.deserialize(input);

        // get best exchangerate and the path taken for the best rate
        double bestRate = exchangeRateCalculator.GetBestExchangeRate(exchangeRateRequest.GetSourceExchangeCurrencyIndex(), exchangeRateRequest.GetDestinationExchangeCurrencyIndex());

        // check the path taken for the best rate
        if(bestRate > 0) {
            List<ExchangeCurrencyVertex> bestPathTaken = exchangeRateCalculator.pathTaken(exchangeRateRequest.GetSourceExchangeCurrencyIndex(), exchangeRateRequest.GetDestinationExchangeCurrencyIndex());
            System.out.printf("Best %s : %f \n",input, bestRate);

            System.out.printf("Path Taken for best rate \n");

            System.out.printf("%s %s \n", exchangeRateRequest.getSourceExchangeCurrencyVertex().exchange, exchangeRateRequest.getSourceExchangeCurrencyVertex().currency);
            for(ExchangeCurrencyVertex vertex : bestPathTaken){
                System.out.printf("%s %s \n", vertex.exchange, vertex.currency);
            }
        }
    }
}
