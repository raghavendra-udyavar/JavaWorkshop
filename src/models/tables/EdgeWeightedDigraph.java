package models.tables;

import models.structures.ExchangeCurrencyVertex;
import models.structures.PriceUpdate;
import models.structures.WeightedEdge;

public class EdgeWeightedDigraph {
    EdgeTable edgeTable;
    VertexTable vertexTable;

    public EdgeWeightedDigraph(){
        edgeTable = new EdgeTable();
        vertexTable = new VertexTable();
    }

    public double[][] updateDigraph(PriceUpdate[] priceUpdates)
    {
        for (PriceUpdate priceUpdate : priceUpdates)
        {
            if (priceUpdate != null)
            {
                // update the vertex table with the new price update
                ExchangeCurrencyVertex sourceExchangeCurrency = vertexTable.addOrUpdateVertices(priceUpdate.exchange, priceUpdate.sourceCurrency);
                ExchangeCurrencyVertex destinationExchangeCurrency = vertexTable.addOrUpdateVertices(priceUpdate.exchange, priceUpdate.destinationCurrency);

                // update the weightedexchangetable
                WeightedEdge edge1 = new WeightedEdge(priceUpdate.forwardRate, sourceExchangeCurrency.index, destinationExchangeCurrency.index);
                WeightedEdge edge2 = new WeightedEdge(priceUpdate.backwardRate, destinationExchangeCurrency.index, sourceExchangeCurrency.index);

                edgeTable.addOrUpdateEdges(edge1);
                edgeTable.addOrUpdateEdges(edge2);
            }
        }

        // update the exchange rate accordingly
        return constructAdjacencyMatrix();
    }

    double[][] constructAdjacencyMatrix()
    {
        int verticesCount = vertexTable.exchangeCurrencyVertexTreeMap.size();
        double[][] adjacencyMatrix = new double[verticesCount][];

        for(int i = 0; i < verticesCount; i++)
        {
            for(int j = 0; j < verticesCount; j++)
            {
                if (i == j)
                {
                    adjacencyMatrix[i][j] = 0;
                }
                else
                {
                    adjacencyMatrix[i][j] = getEdgeWeightFromiToj(i, j);
                }
            }
        }

        return adjacencyMatrix;
    }

    double getEdgeWeightFromiToj(int i, int j)
    {
        WeightedEdge edge = edgeTable.get(i, j);
        if (edge != null)
        {
            return edge.rate;
        } else
        {
            // check the source currency against the destination currency here,
            // the connection between them is 1 if they have a matching currency else -1
            ExchangeCurrencyVertex sourceVertex = vertexTable.exchangeCurrencyVertexTreeMap.get(i);
            ExchangeCurrencyVertex destinationVertex = vertexTable.exchangeCurrencyVertexTreeMap.get(j);
            if (sourceVertex.currency == destinationVertex.currency)
            {
                return 1.0;
            }
            else
            {
                return -1.0;
            }
        }
    }
}
