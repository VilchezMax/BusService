package algorithm3;

import algorithm2.Vertex;

import java.util.List;

public class Dijkstra {
    private List<List<Vertex>> graph;

    public Dijkstra(List<List<Vertex>> graph) {
        this.graph = graph;
    }

    public List<List<Vertex>> getGraph() {
        return graph;
    }

    public void setGraph(List<List<Vertex>> graph) {
        this.graph = graph;
    }

    private int findMinDistance(List<Vertex> visited) {
        double minValue = Integer.MAX_VALUE;
        int minIndex = -1;

/*        for (int i = 0; i < distance.size(); i++) {
            if (!visited.contains(i) && distance.get(i) < minValue) {
                minValue = distance.get(i);
                minIndex = i;
            }
        }*/
        return minIndex;
    }
}
