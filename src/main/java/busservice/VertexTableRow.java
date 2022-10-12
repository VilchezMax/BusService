package busservice;

import java.text.DecimalFormat;

public class VertexTableRow {

    private String vertex;
    private double shortestFromStart;
    private String prevVertex;

    public VertexTableRow() {
        this.shortestFromStart = Double.POSITIVE_INFINITY;
    }

    public String getVertex() {
        return vertex;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "VertexTable{" + "vertex='" + vertex + '\'' + ", shortestFromStart=" + df.format(shortestFromStart) + ", prevVertex='" + prevVertex + '\'' + '}';
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }

    public double getShortestFromStart() {
        return shortestFromStart;
    }

    public void setShortestFromStart(double shortestFromStart) {
        this.shortestFromStart = shortestFromStart;
    }

    public String getPrevVertex() {
        return prevVertex;
    }

    public void setPrevVertex(String prevVertex) {
        this.prevVertex = prevVertex;
    }



}
