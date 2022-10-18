package algorithm;

import busservice.dao.DBInfoHandler;
import busservice.models.BusStop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

public class DijkstraTest {

    private static final Logger logger = LogManager.getLogger(DijkstraTest.class);

    public static ArrayList<BusStop> getShortestPath(BusStop from, BusStop to) {
        ArrayList<BusStop> visited = new ArrayList<>();
        ArrayList<BusStop> unvisited = new ArrayList<>();
        DBInfoHandler infoHandler = new DBInfoHandler();
        HashMap<BusStop, ArrayList<BusStop>> vertices = infoHandler.getAdjacentStops();
        ArrayList<VertexTableRow> shortestTable = new ArrayList<>();
        HashMap<BusStop, int[]> distances = new HashMap<>();
        try {
            distances = infoHandler.getStopsCoordinates();
        } catch (SQLException e) {
            logger.error("Error getting Stops Coordinates");
            logger.error(e.getMessage());
        }
        for (Map.Entry<BusStop, ArrayList<BusStop>> set : vertices.entrySet()) {
            VertexTableRow row = new VertexTableRow();
            row.setVertex(set.getKey());
            unvisited.add(set.getKey());
            if (set.getKey().equals(from)) {
                row.setShortestFromStart(0);
            }
            shortestTable.add(row);
        }

        while (isMaxValue(shortestTable) && unvisited.size() > 0) {

            double currShortest = Double.MAX_VALUE;
            BusStop closestVertex = null;
            for (VertexTableRow row : shortestTable) {

                if (row.getShortestFromStart() < currShortest && !visited.contains(row.getVertex())) {

                    currShortest = row.getShortestFromStart();
                    closestVertex = row.getVertex();
                }
            }

            for (Map.Entry<BusStop, ArrayList<BusStop>> set : vertices.entrySet()) {
                if (set.getKey().equals(closestVertex)) {
                    for (BusStop toVisit : set.getValue()) {
                        if (!visited.contains(toVisit)) {

                            double distance = calculateDistance(closestVertex, toVisit, distances);

                            for (VertexTableRow update : shortestTable) {

                                if (isEdgeStop(vertices, update.getVertex(), to, from)) {
                                    update.setShortestFromStart(1000000);
                                    update.setPrevVertex(toVisit);
                                    visited.add(update.getVertex());
                                    unvisited.remove(update.getVertex());
                                    continue;
                                }

                                if (update.getVertex().equals(toVisit) && update.getShortestFromStart() > distance) {

                                    for (VertexTableRow dist : shortestTable) {
                                        if (dist.getVertex().equals(closestVertex)) {
                                            distance += dist.getShortestFromStart();
                                            break;
                                        }
                                    }
                                    if (update.getShortestFromStart() >= distance) {
                                        double newShortest = distance;

                                        for (VertexTableRow sumThis : shortestTable) {
                                            if (update.getShortestFromStart() < sumThis.getShortestFromStart() + distance) {
                                                newShortest += sumThis.getShortestFromStart();
                                                update.setShortestFromStart(newShortest);
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
                }
            }

            visited.add(closestVertex);
            unvisited.remove(closestVertex);
        }

        ArrayList<BusStop> result = new ArrayList<>();

        boolean prevIsNull = false;

        while (!prevIsNull) {
            for (VertexTableRow show : shortestTable) {

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
        return result;
    }

    private static double calculateDistance(BusStop closestVertex, BusStop toVisit, HashMap<BusStop, int[]> distances) {
        int[] coordinatesClosest = distances.get(closestVertex);
        int[] coordinatesToVisit = distances.get(toVisit);
        int closestX = coordinatesClosest[0];
        int closestY = coordinatesClosest[1];
        int toVisitX = coordinatesToVisit[0];
        int toVisitY = coordinatesToVisit[1];
        return Math.sqrt(Math.pow(toVisitY - closestY, 2) + Math.pow(toVisitX - closestX, 2));
    }

    public static boolean isMaxValue(ArrayList<VertexTableRow> testTable) {
        for (VertexTableRow row : testTable) {
            if (row.getShortestFromStart() == Double.MAX_VALUE) {
                return true;
            }
        }
        return false;
    }

    public static void showPathWithBuses(ArrayList<BusStop> route) {
        DBInfoHandler info = new DBInfoHandler();
        HashMap<Integer, BusStop[]> lines = info.getLinesStops();

        ArrayList<String> resultArray = new ArrayList<>();

        Integer currentLine = null;
        for (int i = 0; i < route.size(); i++) {

            for (Map.Entry<Integer, BusStop[]> set : lines.entrySet()) {
                if (i + 1 == route.size()) {
                    resultArray.add(route.get(i).getName());
                    logger.info("get off at " + route.get(i).getName());
                    break;
                }

                int first = -10;
                int next = -10;

                BusStop[] auxArray = new BusStop[0];
                if (currentLine != null) {
                    auxArray = lines.get(currentLine);
                }

                for (int j = 0; j < auxArray.length; j++) {

                    if (auxArray[j].equals(route.get(i))) {
                        first = j;
                    }

                    if (auxArray[j].equals(route.get(i + 1))) {
                        next = j;
                    }
                }

                if (lineHasBothStops(set.getValue(), route.get(i), route.get(i + 1)) && (first == (next + 1) || first == next - 1)) {
                    continue;
                }

                if (lineHasBothStops(set.getValue(), route.get(i), route.get(i + 1))) {
                    if (!(currentLine != null && currentLine.equals(set.getKey()))) {
                        logger.info("take line " + set.getKey() + " at " + route.get(i).getName());
                    }
                    resultArray.add(set.getKey().toString());
                    resultArray.add(route.get(i).getName());
                    currentLine = set.getKey();
                }
            }
        }
    }

    public static boolean lineHasBothStops(BusStop[] stops, BusStop currStop, BusStop nextStop) {

        if (Arrays.stream(stops).anyMatch(currStop::equals) && Arrays.stream(stops).anyMatch(nextStop::equals)) {
            return true;
        }
        return false;
    }

    public static boolean isEdgeStop(HashMap<BusStop, ArrayList<BusStop>> stopsList, BusStop stop, BusStop destination, BusStop start) {

        if (stopsList.get(stop).size() == 1 && !stopsList.get(stop).get(0).equals(destination) && !stop.equals(destination) && !stop.equals(start)) {
            return true;
        }

        return false;
    }
}