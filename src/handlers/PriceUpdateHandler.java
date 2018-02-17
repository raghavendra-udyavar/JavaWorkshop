package handlers;

import models.structures.PriceUpdate;
import controllers.serializers.PriceUpdateDeserializer;
import models.tables.EdgeWeightedDigraph;

public class PriceUpdateHandler implements IRequestHandler
{
    EdgeWeightedDigraph edgeWeightedDigraph;

    public PriceUpdateHandler(EdgeWeightedDigraph injectedEdgeWeightedDigraph){
        edgeWeightedDigraph = injectedEdgeWeightedDigraph;
    }

    @Override
    public void HandleRequest(String input) {
        try {
            PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();
            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserialize(input);
            double[][] digraph = edgeWeightedDigraph.updateDigraph(priceUpdates);

        }catch (Exception ex){

        }
    }
}
