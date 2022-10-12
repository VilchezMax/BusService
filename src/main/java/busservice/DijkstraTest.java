package busservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DijkstraTest {
    public static String getShortestPath(String start, String destination) {
        {
            ArrayList<String> visitedVertexes = new ArrayList<>();
            ArrayList<String> unvisitedVertexes = new ArrayList<>();
            ArrayList<VertexTableRow> vertexTableRows = new ArrayList<>();
            ArrayList<String> result = new ArrayList<>();
            HashMap<String, String[]> vertices = loadVertices(new HashMap<>());
            HashMap<String, int[]> distances = loadDistances(new HashMap<>());

            for (Map.Entry<String, String[]> entry : vertices.entrySet()) {
                VertexTableRow row = new VertexTableRow();
                row.setVertex(entry.getKey());
                unvisitedVertexes.add(entry.getKey());
                if (entry.getKey() == start) {
                    row.setShortestFromStart(0);
                }
                vertexTableRows.add(row);
            }

            //double currShortest = Double.POSITIVE_INFINITY;
            //String closestVertex = null;
            // || unvisitedVertexes.size()>0
            while (allVisited(vertexTableRows)) {
                double currentShortestVertex = Double.POSITIVE_INFINITY;
                String closestVertex = null;
                for (VertexTableRow row : vertexTableRows) {
                    if (row.getShortestFromStart() < currentShortestVertex && !visitedVertexes.contains(row.getVertex())) {
                        currentShortestVertex = row.getShortestFromStart();
                        closestVertex = row.getVertex();
                    }
                }
                for (Map.Entry<String, String[]> entry : vertices.entrySet()) {
                    if (entry.getKey() == closestVertex) {
                        for (String toVisit : entry.getValue()) {
                            if (visitedVertexes.contains(toVisit)) {
                                continue;
                            }
                            int[] coordinatesClosest = distances.get(closestVertex);
                            int[] coordinatesToVisit = distances.get(toVisit);
                            int closestX = coordinatesClosest[0];
                            int closestY = coordinatesClosest[1];
                            int toVisitX = coordinatesToVisit[0];
                            int toVisitY = coordinatesToVisit[1];
                            double distance = Math.sqrt(Math.pow(toVisitY - closestY, 2) + Math.pow(toVisitX - closestX, 2));
                            for (VertexTableRow row : vertexTableRows) {
                                if (row.getVertex() == toVisit && row.getShortestFromStart() > distance) {
                                    for (VertexTableRow dist : vertexTableRows) {
                                        if (dist.getVertex() == closestVertex) {
                                            distance += dist.getShortestFromStart();
                                            break;
                                        }
                                    }
                                    if (row.getShortestFromStart() >= distance) {
                                        double newShortestVertex = distance;
                                        for (VertexTableRow previousRow : vertexTableRows) {
                                            double distanceTotal = previousRow.getShortestFromStart() + distance;
                                            if (distanceTotal > row.getShortestFromStart()) {
                                                newShortestVertex += previousRow.getShortestFromStart();
                                                row.setShortestFromStart(newShortestVertex);
                                                row.setPrevVertex(closestVertex);
                                                break;
                                            }
                                            if (previousRow.getVertex() == row.getPrevVertex()) {
                                                newShortestVertex += previousRow.getShortestFromStart();
                                                break;
                                            }
                                        }
                                        row.setShortestFromStart(newShortestVertex);
                                        row.setPrevVertex(closestVertex);
                                    }
                                }
                            }
                        }
                    }
                }
                visitedVertexes.add(closestVertex);
                unvisitedVertexes.remove(closestVertex);
            }

            boolean prevIsNull = false;
            while (!prevIsNull) {
                for (VertexTableRow row : vertexTableRows) {
                    if (row.getVertex() == destination) {
                        result.add(row.getVertex());
                        if (row.getPrevVertex() == null) {
                            prevIsNull = true;
                            break;
                        }
                        destination = row.getPrevVertex();
                    }
                }
            }
            Collections.reverse(result);
            return result.toString();
        }
    }

    public static boolean allVisited(ArrayList<VertexTableRow> testTable) {
        boolean anyInfinity = false;
        for (VertexTableRow row : testTable) {
            if (row.getShortestFromStart() == Double.POSITIVE_INFINITY) {
                anyInfinity = true;
                break;
            }
        }
        return anyInfinity;
    }

    public static HashMap<String, String[]> loadVertices(HashMap<String, String[]> vertices) {
        vertices.put("Knightsbridge", new String[]{"Covent Garden", "Gloucester Road"});
        vertices.put("Covent Garden", new String[]{"Leicester Square", "Knightsbridge"});
        vertices.put("Gloucester Road", new String[]{"Hyde Park Corner", "Knightsbridge"});
        vertices.put("Leicester Square", new String[]{"Covent Garden", "Tower Hill", "Hyde Park Corner"});
        vertices.put("Hyde Park Corner", new String[]{"Gloucester Road", "Piccadilly Circus", "Leicester Square"});
        vertices.put("Piccadilly Circus", new String[]{"Hyde Park Corner", "Liverpool Street", "Waterloo"});
        vertices.put("Tower Hill", new String[]{"Leicester Square", "Blackfriars"});
        vertices.put("Blackfriars", new String[]{"Tower Hill", "St. Paul's"});
        vertices.put("St. Paul's", new String[]{"Blackfriars", "Waterloo"});
        vertices.put("Waterloo", new String[]{"Piccadilly Circus", "St. Paul's"});
        vertices.put("Liverpool Street", new String[]{"Piccadilly Circus"});
        return vertices;
    }

    public static HashMap<String, int[]> loadDistances(HashMap<String, int[]> distances) {
        distances.put("Knightsbridge", new int[]{39, 13});
        distances.put("Covent Garden", new int[]{36, 14});
        distances.put("Gloucester Road", new int[]{39, 16});
        distances.put("Leicester Square", new int[]{35, 17});
        distances.put("Hyde Park Corner", new int[]{38, 20});
        distances.put("Piccadilly Circus", new int[]{35, 23});
        distances.put("Tower Hill", new int[]{31, 16});
        distances.put("Blackfriars", new int[]{29, 19});
        distances.put("St. Paul's", new int[]{29, 22});
        distances.put("Waterloo", new int[]{32, 20});
        distances.put("Liverpool Street", new int[]{34, 25});
        return distances;
    }
}
