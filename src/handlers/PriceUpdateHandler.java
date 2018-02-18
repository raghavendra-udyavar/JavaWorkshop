package handlers;

import controllers.calculator.ExchangeRateCalculator;
import models.structures.PriceUpdate;
import controllers.serializers.PriceUpdateDeserializer;
import models.tables.EdgeWeightedDigraph;

public class PriceUpdateHandler implements IRequestHandler
{
    EdgeWeightedDigraph edgeWeightedDigraph;
    ExchangeRateCalculator exchangeRateCalculator;

    public PriceUpdateHandler(EdgeWeightedDigraph injectedEdgeWeightedDigraph, ExchangeRateCalculator injectedExchangeRateCalculator){
        edgeWeightedDigraph = injectedEdgeWeightedDigraph;
        exchangeRateCalculator = injectedExchangeRateCalculator;
    }

    @Override
    public void HandleRequest(String input) {
        try {
            PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();
            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserialize(input);
            double[][] digraph = edgeWeightedDigraph.updateDigraph(priceUpdates);
            exchangeRateCalculator.calculateBestExchangeRate(digraph);

        }catch (Exception ex){

        }
    }
}
