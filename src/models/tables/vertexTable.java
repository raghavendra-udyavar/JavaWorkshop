package models.tables;

import models.structures.ExchangeCurrencyVertex;

import java.util.TreeMap;

public class VertexTable {

    public TreeMap<Integer, ExchangeCurrencyVertex> exchangeCurrencyVertexTreeMap;

    public VertexTable()
    {
        exchangeCurrencyVertexTreeMap = new TreeMap<>();
    }

    public ExchangeCurrencyVertex addOrUpdateVertices(String exchange, String currency)
    {
        ExchangeCurrencyVertex foundExchangeCurrencyVertex = get(exchange, currency);
        if (foundExchangeCurrencyVertex == null)
        {
            ExchangeCurrencyVertex exchangeCurrencyVertex = new ExchangeCurrencyVertex(exchange, currency);
            exchangeCurrencyVertex.index = exchangeCurrencyVertexTreeMap.size();
            exchangeCurrencyVertexTreeMap.put(exchangeCurrencyVertex.index, exchangeCurrencyVertex);

            return exchangeCurrencyVertex;
        } else
        {
            return foundExchangeCurrencyVertex;
        }
    }

    ExchangeCurrencyVertex get(String exchange, String currency)
    {
        for(ExchangeCurrencyVertex vertex : exchangeCurrencyVertexTreeMap.values()) {
            if((vertex.exchange == exchange) && (vertex.currency == currency))
                return vertex;
        }

        return null;
    }
}
