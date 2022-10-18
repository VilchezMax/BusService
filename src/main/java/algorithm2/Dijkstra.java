package algorithm2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public void computePath(Vertex source) {
        source.setMinDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            Vertex vertex = priorityQueue.poll();

            for (Edge edge : vertex.getEdges()) {
                Vertex vtx = edge.getTargetVertex();
//                Vertex u = edge.getStartVertex();
                double distance = edge.getDistance();
                double minDistance = vertex.getMinDistance() + distance;

                if (minDistance < vtx.getMinDistance()) {
                    priorityQueue.remove(vertex);
                    vtx.setPreviousVertex(vertex);
                    vtx.setMinDistance(minDistance);
                    priorityQueue.add(vtx);
                }
            }
        }
        priorityQueue.forEach(System.out::println);
    }

    public List<Vertex> getShortestPathTo(Vertex target) {

        List<Vertex> result = new ArrayList<>();
        Vertex previousVertex = target;
        do {
            result.add(previousVertex);
            previousVertex = previousVertex.getPreviousVertex();
        } while (previousVertex != null);
//
//        for (Vertex vertex = target; vertex != null; vertex = vertex.getPreviousVertex()) {
//            result.add(vertex);
//        }

        Collections.reverse(result);
        return result;
    }
}
