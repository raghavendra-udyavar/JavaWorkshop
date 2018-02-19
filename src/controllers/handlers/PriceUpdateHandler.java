package controllers.handlers;

import controllers.calculator.ExchangeRateCalculator;
import models.database.DataConnection;
import models.structures.PriceUpdate;
import controllers.serializers.PriceUpdateDeserializer;
import models.tables.EdgeWeightedDigraph;

import java.time.LocalDateTime;
import java.util.Date;

public class PriceUpdateHandler implements IRequestHandler
{
    EdgeWeightedDigraph edgeWeightedDigraph;
    ExchangeRateCalculator exchangeRateCalculator;
    DataConnection dataConnection;

    public PriceUpdateHandler(EdgeWeightedDigraph injectedEdgeWeightedDigraph, ExchangeRateCalculator injectedExchangeRateCalculator, DataConnection connection){
        edgeWeightedDigraph = injectedEdgeWeightedDigraph;
        exchangeRateCalculator = injectedExchangeRateCalculator;
        dataConnection = connection;
    }

    @Override
    public void HandleRequest(String input) throws Exception {

            PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();
            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserializeMultiline(input);

            // TODO: for now it adds the values to table everytime the application is run, which needs to be avoided once it finds the combination of exchange, sourcecurrency and destinationcurrency present
            for (PriceUpdate priceUpdate : priceUpdates){
                dataConnection.insertIntoTable("INSERT INTO priceupdate(id, updatetime, exchange, sourcecurrency, destinationcurrency, forwardrate, backwardrate)" +
                        " VALUES (DEFAULT, "+ null + ","
                        + "\'" + priceUpdate.exchange + "\'" + ","
                        + "\'" + priceUpdate.sourceCurrency + "\'" + ","
                        + "\'" + priceUpdate.destinationCurrency + "\'" + ","
                        + priceUpdate.forwardRate + ","
                        + priceUpdate.backwardRate + ")" );
            }

            // update the digraph and the bestexchangerate for the new priceupdates
            double[][] digraph = edgeWeightedDigraph.updateDigraph(priceUpdates);
            exchangeRateCalculator.calculateBestExchangeRate(digraph);

    }
}
