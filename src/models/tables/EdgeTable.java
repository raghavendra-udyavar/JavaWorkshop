package models.tables;

import models.structures.WeightedEdge;

import java.util.LinkedList;
import java.util.List;

public class EdgeTable {
    List<WeightedEdge> weightedEdges;

    public EdgeTable(){
        weightedEdges = new LinkedList<>();
    }

    public void addOrUpdateEdges(WeightedEdge edge)
    {
        WeightedEdge foundEdge = get(edge);
        if (foundEdge == null)
        {
            weightedEdges.add(edge);
        } else
        {
            foundEdge.updateNewRate(edge.rate);
        }
    }

    private WeightedEdge get(WeightedEdge edge){
        return get(edge.sourceIndex, edge.destinationIndex);
    }

    WeightedEdge get(int sourceIndex, int destinationIndex){
        for(WeightedEdge weightedEdge : weightedEdges){
            if(weightedEdge.sourceIndex == sourceIndex && weightedEdge.destinationIndex == destinationIndex) {
                return  weightedEdge;
            }
        }

        return null;
    }
}
