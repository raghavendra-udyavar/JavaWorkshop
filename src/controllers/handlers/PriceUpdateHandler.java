package controllers.handlers;

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
    public void HandleRequest(String input) throws Exception {

            PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();
            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserializeMultiline(input);

            // update the digraph and the bestexchangerate for the new priceupdates
            double[][] digraph = edgeWeightedDigraph.updateDigraph(priceUpdates);
            exchangeRateCalculator.calculateBestExchangeRate(digraph);

    }
}
