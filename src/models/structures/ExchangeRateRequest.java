package models.structures;

import models.tables.EdgeWeightedDigraph;

public class ExchangeRateRequest {
    ExchangeCurrencyVertex sourceExchangeCurrencyVertex;
    ExchangeCurrencyVertex destinationExchangeCurrencyVertex;

    public ExchangeRateRequest(EdgeWeightedDigraph digraph, ExchangeCurrencyVertex currSourceExchangeCurrencyVertex, ExchangeCurrencyVertex currDestinationExchangeCurrencyVertex){
        sourceExchangeCurrencyVertex = currSourceExchangeCurrencyVertex;
        destinationExchangeCurrencyVertex = currDestinationExchangeCurrencyVertex;

        updateIndexForBothVertex(digraph);
    }

    void updateIndexForBothVertex(EdgeWeightedDigraph digraph){
        sourceExchangeCurrencyVertex.index = digraph.GetVertexTable().get(sourceExchangeCurrencyVertex.exchange, sourceExchangeCurrencyVertex.currency).index;
        destinationExchangeCurrencyVertex.index = digraph.GetVertexTable().get(destinationExchangeCurrencyVertex.exchange, destinationExchangeCurrencyVertex.currency).index;
    }
}
