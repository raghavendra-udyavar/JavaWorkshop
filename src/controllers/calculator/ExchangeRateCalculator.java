package controllers.calculator;

import models.structures.ExchangeCurrencyVertex;
import models.tables.EdgeWeightedDigraph;

import java.util.LinkedList;
import java.util.List;

public class ExchangeRateCalculator {

    int[][] pathsTaken;
    EdgeWeightedDigraph edgeWeightedDigraph;
    public double[][] BestExchangeRates;

    public  ExchangeRateCalculator(EdgeWeightedDigraph edgeWeightedDigraphObj){
        edgeWeightedDigraph = edgeWeightedDigraphObj;
    }

    public void calculateBestExchangeRate(double[][] exchangeRateAdjacencyMatrix)
    {
        runFloydWarshallAlgorithm(exchangeRateAdjacencyMatrix);
    }

    private void runFloydWarshallAlgorithm(double[][] exchangeRateAdjacencyMatrix)
    {
        int vertexCount = edgeWeightedDigraph.GetVertexTable().GetVertices().size();
        pathsTaken = initializePathTaken(vertexCount);

        BestExchangeRates = exchangeRateAdjacencyMatrix;

        for (int k = 0; k < vertexCount; k++)
        {
            for (int i = 0; i < vertexCount; i++)
            {
                for (int j = 0; j < vertexCount; j++)
                {
                    if (BestExchangeRates[i][j] < BestExchangeRates[i][k] * BestExchangeRates[k][j])
                    {
                        BestExchangeRates[i][j] = BestExchangeRates[i][k] * BestExchangeRates[k][j];
                        pathsTaken[i][j] = pathsTaken[i][k];
                    }
                }
            }
        }
    }

    private int[][] initializePathTaken(int vertexCount)
    {
        int[][] pathTaken = new int[vertexCount][vertexCount];

        for (int i = 0; i < vertexCount; i++)
        {
            for(int j = 0; j < vertexCount; j++)
            {
                pathTaken[i][j] = j;
            }
        }
        return pathTaken;
    }

    public List<ExchangeCurrencyVertex> pathTaken(int sourceIndex, int destIndex)
    {
        Integer pathTaken = pathsTaken[sourceIndex][destIndex];
        if (pathTaken == null) {
            return null;
        } else
        {
            int vertexCount = edgeWeightedDigraph.GetVertexTable().GetVertices().size();
            List<ExchangeCurrencyVertex> path = new LinkedList<>();
            while (sourceIndex != destIndex)
            {
                sourceIndex = pathsTaken[sourceIndex][destIndex];

                ExchangeCurrencyVertex currentExchangeSourceVertex = edgeWeightedDigraph.GetVertexTable().GetVertices().get(sourceIndex);
                path.add(currentExchangeSourceVertex);
            }
            return path;
        }
    }
}
