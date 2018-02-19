package models.tables;

import controllers.serializers.PriceUpdateDeserializer;
import models.structures.ExchangeCurrencyVertex;
import models.structures.PriceUpdate;
import models.structures.WeightedEdge;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TablesTest {

    @Test
    void checkAddVertex() {

        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph();
        try {
            PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();

            String newPriceUpdateString = "2017-11-01T09:42:23+00:00 DBS SGD USD 1.00 0.76\n"
                    + "2017-11-01T09:42:23+00:00 OCBC SGD RM 1.00 2.97";

            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserializeMultiline(newPriceUpdateString);
            for (PriceUpdate priceUpdate : priceUpdates) {

                // update the vertex table with the new price update
                edgeWeightedDigraph.GetVertexTable().addOrUpdateVertices(priceUpdate.exchange, priceUpdate.sourceCurrency);
                edgeWeightedDigraph.GetVertexTable().addOrUpdateVertices(priceUpdate.exchange, priceUpdate.destinationCurrency);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            assertEquals(4, edgeWeightedDigraph.GetVertexTable().GetVertices().size());
        }
    }

    @Test
    void checkUpdateVertex() throws Exception {

        PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();
        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph();

        try {
            String newPriceUpdateString = "2017-11-01T09:42:23+00:00 DBS SGD USD 1.00 0.76\n"
                    + "2017-11-01T09:42:23+00:00 DBS USD SGD 1 1.315";

            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserializeMultiline(newPriceUpdateString);

            for (PriceUpdate priceUpdate : priceUpdates) {

                // update the vertex table with the new price update
                edgeWeightedDigraph.GetVertexTable().addOrUpdateVertices(priceUpdate.exchange, priceUpdate.sourceCurrency);
                edgeWeightedDigraph.GetVertexTable().addOrUpdateVertices(priceUpdate.exchange, priceUpdate.destinationCurrency);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        } finally {
            assertEquals(2, edgeWeightedDigraph.GetVertexTable().GetVertices().size());
        }
    }

    @Test
    void checkAddEdge(){

        int count = 0;
        try {
            PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();

            String newPriceUpdateString = "2017-11-01T09:42:23+00:00 DBS SGD USD 1.00 0.76\n"
                    + "2017-11-01T09:42:23+00:00 OCBC SGD RM 1.00 2.97";

            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserializeMultiline(newPriceUpdateString);
            count = addOrUpdateEdge(priceUpdates);
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            assertEquals(4, count);
        }
    }

    @Test
    void checkUpdateEdge() throws Exception {

        int count = 0;

        try {
            PriceUpdateDeserializer priceUpdateDeserializer = new PriceUpdateDeserializer();

            String newPriceUpdateString = "2017-11-01T09:42:23+00:00 DBS SGD USD 1.00 0.76\n"
                    + "2017-11-01T09:42:23+00:00 DBS USD SGD 1 1.315";

            PriceUpdate[] priceUpdates = (PriceUpdate[]) priceUpdateDeserializer.deserializeMultiline(newPriceUpdateString);
            count = addOrUpdateEdge(priceUpdates);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            assertEquals(2, count);
        }
    }

    int addOrUpdateEdge(PriceUpdate[] priceUpdates){

        EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph();

        for(PriceUpdate priceUpdate : priceUpdates) {
            // update the vertex table with the new price update
            ExchangeCurrencyVertex sourceExchangeCurrency = edgeWeightedDigraph.GetVertexTable().addOrUpdateVertices(priceUpdate.exchange, priceUpdate.sourceCurrency);
            ExchangeCurrencyVertex destinationExchangeCurrency = edgeWeightedDigraph.GetVertexTable().addOrUpdateVertices(priceUpdate.exchange, priceUpdate.destinationCurrency);

            // update the weightedexchangetable
            WeightedEdge edge1 = new WeightedEdge(priceUpdate.forwardRate, sourceExchangeCurrency.index, destinationExchangeCurrency.index);
            WeightedEdge edge2 = new WeightedEdge(priceUpdate.backwardRate, destinationExchangeCurrency.index, sourceExchangeCurrency.index);
            edgeWeightedDigraph.GetEdgeTable().addOrUpdateEdges(edge1);
            edgeWeightedDigraph.GetEdgeTable().addOrUpdateEdges(edge2);
        }

        return edgeWeightedDigraph.GetEdgeTable().getWeightedEdges().size();
    }
}