package models.tables;

import models.structures.ExchangeCurrencyVertex;

import java.util.TreeMap;

public class VertexTable {

    TreeMap<Integer, ExchangeCurrencyVertex> exchangeCurrencyVertexTreeMap;

    public VertexTable()
    {
        exchangeCurrencyVertexTreeMap = new TreeMap<>();
    }

    public TreeMap<Integer, ExchangeCurrencyVertex> GetVertices() { return exchangeCurrencyVertexTreeMap; }

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
            if(vertex.exchange.equals(exchange) && vertex.currency.equals(currency)) {
                return vertex;
            }
        }

        return null;
    }
}
