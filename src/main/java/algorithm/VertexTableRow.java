package algorithm;

import busservice.models.BusStop;

import java.text.DecimalFormat;

public class VertexTableRow {
    private BusStop vertex;
    private double shortestFromStart;
    private BusStop prevVertex;

    public VertexTableRow() {
        this.shortestFromStart = Double.POSITIVE_INFINITY;
    }

    public BusStop getVertex() {
        return vertex;
    }

    public void setVertex(BusStop vertex) {
        this.vertex = vertex;
    }

    public double getShortestFromStart() {
        return shortestFromStart;
    }

    public void setShortestFromStart(double shortestFromStart) {
        this.shortestFromStart = shortestFromStart;
    }

    public BusStop getPrevVertex() {
        return prevVertex;
    }

    public void setPrevVertex(BusStop prevVertex) {
        this.prevVertex = prevVertex;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "VertexTableRow{" + "vertex='" + vertex.getName() + '\'' + ", shortestFromStart=" + df.format(shortestFromStart) + ", prevVertex='" + prevVertex.getName() + '\'' + '}';
    }
}
