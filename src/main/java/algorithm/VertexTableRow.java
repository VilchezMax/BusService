package algorithm;

import busservice.models.Bus;
import busservice.models.BusStop;
import busservice.services.mybatis.BusService;

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

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "VertexTableRow{" + "vertex='" + vertex + '\'' + ", shortestFromStart=" + df.format(shortestFromStart) + ", prevVertex='" + prevVertex + '\'' + '}';
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
}
