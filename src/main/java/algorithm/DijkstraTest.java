package algorithm;

import busservice.dao.DBInfoHandler;

import java.sql.SQLException;
import java.util.*;

public class DijkstraTest {

    public static String getShortestPath(String from, String to) throws SQLException {
        try {
            ArrayList<String> visited = new ArrayList<>();
            ArrayList<String> unvisited = new ArrayList<>();
            DBInfoHandler infoHandler = new DBInfoHandler();
            HashMap<String, String[]> vertices = infoHandler.getAdjacentStops();
            HashMap<String, int[]> distances = infoHandler.getStopsCoordinates();
            ArrayList<VertexTable> shortestTable = new ArrayList<>();

            for (Map.Entry<String, String[]> set : vertices.entrySet()) {
                VertexTable row = new VertexTable();
                row.setVertex(set.getKey());
                unvisited.add(set.getKey());
                if (set.getKey().equals(from)) {
                    row.setShortestFromStart(0);
                }

                shortestTable.add(row);
            }

            while (lookForInfinity(shortestTable) || unvisited.size() > 0) {

                double currShortest = Double.POSITIVE_INFINITY;
                String closestVertex = null;
                for (VertexTable row : shortestTable) {

                    if (  row.getShortestFromStart() < currShortest && !visited.contains(row.getVertex()) ) {

                        currShortest = row.getShortestFromStart();
                        closestVertex = row.getVertex();
                    }
                }


                for (Map.Entry<String, String[]> set : vertices.entrySet()) {
                    if (set.getKey().equals(closestVertex)) {
                        for (String toVisit : set.getValue()) {
                            if (visited.contains(toVisit)) {
                                continue;
                            }
                            int[] coordinatesClosest = distances.get(closestVertex);
                            int[] coordinatesToVisit = distances.get(toVisit);

                            int closestX = coordinatesClosest[0];
                            int closestY = coordinatesClosest[1];
                            int toVisitX = coordinatesToVisit[0];
                            int toVisitY = coordinatesToVisit[1];

                            double distance = Math.sqrt(Math.pow(toVisitY - closestY, 2) + Math.pow(toVisitX - closestX, 2));

                            for (VertexTable update : shortestTable) {


                                if (update.getVertex().equals(toVisit) && update.getShortestFromStart() > distance) {

                                    for (VertexTable dist : shortestTable) {
                                        if (dist.getVertex().equals(closestVertex)) {
                                            distance += dist.getShortestFromStart();
                                            break;
                                        }
                                    }
                                    if (update.getShortestFromStart() < distance) {
                                        continue;
                                    }
                                    double newShortest = distance;

                                    for (VertexTable sumThis : shortestTable) {
                                        if (update.getShortestFromStart() < sumThis.getShortestFromStart() + distance) {
                                            newShortest += sumThis.getShortestFromStart();update.setShortestFromStart(newShortest);
                                            update.setPrevVertex(closestVertex);
                                            break;
                                        }
                                        if (sumThis.getVertex().equals(update.getPrevVertex())) {
                                            newShortest += sumThis.getShortestFromStart();
                                            break;
                                        }
                                    }
                                    update.setShortestFromStart(newShortest);
                                    update.setPrevVertex(closestVertex);
                                }
                            }
                        }

                    }
                }

                visited.add(closestVertex);
                unvisited.remove(closestVertex);

            }


            ArrayList<String> result = new ArrayList<>();

            boolean prevIsNull = false;

            while (!prevIsNull) {
                for (VertexTable show : shortestTable) {

                    if (show.getVertex().equals(to)) {

                        result.add(show.getVertex());
                        if (show.getPrevVertex() == null) {

                            prevIsNull = true;
                            break;
                        }
                        to = show.getPrevVertex();
                    }
                }
            }
            Collections.reverse(result);
            showPathWithBuses(result);
            return result.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean lookForInfinity(ArrayList<VertexTable> testTable) {
        boolean flag = false;
        for (VertexTable infinite : testTable) {
            if (infinite.getShortestFromStart() == Double.POSITIVE_INFINITY) {
                flag = true;
            }
        }
        return flag;
    }


    public static void showPathWithBuses(ArrayList<String> route) {
        DBInfoHandler info = new DBInfoHandler();
        HashMap<Integer, String[]> lines = info.getLinesStops();

        ArrayList<String> resultArray = new ArrayList<>();

        Integer currentLine = null;
        for (int i = 0; i < route.size(); i++) {

            for (Map.Entry<Integer, String[]> set : lines.entrySet()) {
                if (i + 1 == route.size()) {
                    resultArray.add(route.get(i));
                    System.out.println("get off at " + route.get(i));
                    break;
                }

                if (lineHasBothStops(set.getValue(), route.get(i), route.get(i + 1))) {
                    if (currentLine != null && currentLine.equals(set.getKey())) continue;

                    resultArray.add(set.getKey().toString());
                    resultArray.add(route.get(i));

                    System.out.println("take line " + set.getKey() + " at " + route.get(i));

                    currentLine = set.getKey();
                }
            }
        }
    }


    public static boolean lineHasBothStops(String[] stops, String currStop, String nextStop) {

        if (Arrays.stream(stops).anyMatch(currStop::equals) && Arrays.stream(stops).anyMatch(nextStop::equals)) {
            return true;
        }
        return false;
    }
}