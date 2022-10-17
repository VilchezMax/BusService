package algorithm2;

public class Edge {
    private double distance;
    private Vertex startVertex;
    private Vertex targetVertex;

    public Edge(double distance, Vertex startVertex, Vertex targetVertex) {
        this.distance = distance;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        if (!startVertex.equals(edge.startVertex)) return false;
        return targetVertex.equals(edge.targetVertex);
    }

    @Override
    public int hashCode() {
        int result = startVertex.getBusStop().hashCode();
        result = 31 * result + targetVertex.getBusStop().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "\nEdge{" +
                "\n distance=" + distance +
                "\n,    from=" + startVertex.getBusStop().getName() +
                "\n,      to=" + targetVertex.getBusStop().getName() +
                "\n}";
    }
}
