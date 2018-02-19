package models.structures;

import controllers.exceptions.ExchangeRequestNotMatchingException;
import models.tables.EdgeWeightedDigraph;

public class ExchangeRateRequest {
    ExchangeCurrencyVertex sourceExchangeCurrencyVertex;
    ExchangeCurrencyVertex destinationExchangeCurrencyVertex;

    public ExchangeRateRequest(ExchangeCurrencyVertex currSourceExchangeCurrencyVertex, ExchangeCurrencyVertex currDestinationExchangeCurrencyVertex){
        sourceExchangeCurrencyVertex = currSourceExchangeCurrencyVertex;
        destinationExchangeCurrencyVertex = currDestinationExchangeCurrencyVertex;
}

    public void updateIndexForBothVertex(EdgeWeightedDigraph digraph) throws ExchangeRequestNotMatchingException {

        ExchangeCurrencyVertex matchingSourceCurrencyVertex = digraph.GetVertexTable().get(sourceExchangeCurrencyVertex.exchange, sourceExchangeCurrencyVertex.currency);
        ExchangeCurrencyVertex matchingDestinationCurrencyVertex = digraph.GetVertexTable().get(destinationExchangeCurrencyVertex.exchange, destinationExchangeCurrencyVertex.currency);

        if(matchingSourceCurrencyVertex == null){
            throw new ExchangeRequestNotMatchingException("Source Currency Vertex not found in record");
        }

        if(matchingDestinationCurrencyVertex == null){
            throw new ExchangeRequestNotMatchingException("Destination Currency Vertex not found in record");
        }

        sourceExchangeCurrencyVertex.index = matchingSourceCurrencyVertex.index;
        destinationExchangeCurrencyVertex.index = matchingDestinationCurrencyVertex.index;
    }

    public int GetSourceExchangeCurrencyIndex(){
        return sourceExchangeCurrencyVertex.index;
    }

    public int GetDestinationExchangeCurrencyIndex(){
        return destinationExchangeCurrencyVertex.index;
    }

    public ExchangeCurrencyVertex getSourceExchangeCurrencyVertex() {
        return sourceExchangeCurrencyVertex;
    }

    public ExchangeCurrencyVertex getDestinationExchangeCurrencyVertex() {
        return destinationExchangeCurrencyVertex;
    }
}
