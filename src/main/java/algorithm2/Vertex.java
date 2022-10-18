package algorithm2;

import busservice.models.BusStop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
    private BusStop busStop;
    private List<Edge> edges;
    private boolean visited;
    private Vertex previousVertex;
    private double minDistance = Double.MAX_VALUE;

    public Vertex() {
    }

    public Vertex(BusStop busStop) {
        this.busStop = busStop;
        this.edges = new ArrayList<>();
    }

    public BusStop getBusStop() {
        return busStop;
    }

    public void setBusStop(BusStop busStop) {
        this.busStop = busStop;
    }

    public void addNeighbour(Edge edge) {
        this.edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getPreviousVertex() {
        return previousVertex;
    }

    public void setPreviousVertex(Vertex previousVertex) {
        this.previousVertex = previousVertex;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    /* Used for filling Edges distances between vertexes */
    public double calculateDistanceTo(BusStop otherStop) {
        //TODO - Calculate distance between two bus stops
        int aX = this.busStop.getLatitude();
        int aY = this.busStop.getLongitude();
        int bX = otherStop.getLatitude();
        int bY = otherStop.getLongitude();

        return Math.sqrt(Math.pow(aX - bX, 2) + Math.pow(aY - bY, 2));
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "\nbusStop=" + busStop.toString() +
                ",\n edges=" + edges +
                ",\n visited=" + visited +
                ",\n previousVertex=" + previousVertex +
                ",\n minDistance=" + minDistance +
                '}';
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(this.minDistance, otherVertex.minDistance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;

        Vertex vertex = (Vertex) o;

        if (!busStop.equals(vertex.busStop)) return false;
        if (!Objects.equals(edges, vertex.edges)) return false;
        return Objects.equals(previousVertex, vertex.previousVertex);
    }

    @Override
    public int hashCode() {
        int result = busStop.hashCode();
        result = 31 * result + (edges != null ? edges.hashCode() : 0);
        result = 31 * result + (previousVertex != null ? previousVertex.hashCode() : 0);
        return result;
    }
}
