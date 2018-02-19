package models.structures;

public class WeightedEdge {
    public double rate;
    public int sourceIndex;
    public int destinationIndex;

    public WeightedEdge(double currRate, int currSourceIndex, int currDestinationIndex){
        rate = currRate;
        sourceIndex = currSourceIndex;
        destinationIndex = currDestinationIndex;
    }

    public void updateNewRate(double newRate)
    {
        rate = newRate;
    }
}
