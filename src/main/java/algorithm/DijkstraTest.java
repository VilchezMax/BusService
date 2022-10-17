package algorithm;

import busservice.dao.DBInfoHandler;
import busservice.dao.MainMyBatis;
import busservice.models.Bus;
import busservice.models.BusStop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

public class DijkstraTest {

    private static final Logger logger = LogManager.getLogger(DijkstraTest.class);
    public static ArrayList<BusStop> getShortestPath (BusStop from, BusStop to) throws SQLException {
        try {
            ArrayList<BusStop> visited = new ArrayList<>();
            ArrayList<BusStop> unvisited = new ArrayList<>();
            DBInfoHandler infoHandler = new DBInfoHandler();
            HashMap<BusStop, ArrayList<BusStop>> vertices = infoHandler.getAdjacentStops();
            HashMap<BusStop, int[]> distances = infoHandler.getStopsCoordinates();
            ArrayList<VertexTableRow> shortestTable = new ArrayList<>();

            for (Map.Entry<BusStop, ArrayList<BusStop>> set : vertices.entrySet()) {
                VertexTableRow row = new VertexTableRow();
                row.setVertex(set.getKey());
                unvisited.add(set.getKey());
                if (set.getKey().equals(from)) {
                    row.setShortestFromStart(0);
                }

                shortestTable.add(row);
            }

            while (lookForInfinity(shortestTable) && unvisited.size() > 0) {

                double currShortest = Double.POSITIVE_INFINITY;
                BusStop closestVertex = null;
                for (VertexTableRow row : shortestTable) {

                    if (row.getShortestFromStart() < currShortest && !visited.contains(row.getVertex())) {

                        currShortest = row.getShortestFromStart();
                        closestVertex = row.getVertex();
                    }
                }

                for (Map.Entry<BusStop, ArrayList<BusStop>> set : vertices.entrySet()) {
                    if (set.getKey().equals(closestVertex)) {
                        logger.info("Entra 2");
                        for (BusStop toVisit : set.getValue()) {
                            logger.info("Entra 3");
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
                                    if (update.getShortestFromStart() < distance) {
                                        continue;
                                    }
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
                logger.info("Sale 1");

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
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean lookForInfinity (ArrayList<VertexTableRow> testTable) {
        boolean flag = false;
        for (VertexTableRow infinite : testTable) {
            if (infinite.getShortestFromStart() == Double.POSITIVE_INFINITY) {
                flag = true;
            }
        }
        return flag;
    }

    public static void showPathWithBuses (ArrayList<BusStop> route) {
        DBInfoHandler info = new DBInfoHandler();
        HashMap<Integer, BusStop[]> lines = info.getLinesStops();

        ArrayList<BusStop> resultArray = new ArrayList<>();

        Integer currentLine = null;
        for (int i = 0; i < route.size(); i++) {

            for (Map.Entry<Integer, BusStop[]> set : lines.entrySet()) {
                if (i + 1 == route.size()) {
                    resultArray.add(route.get(i));
                    System.out.println("get off at " + route.get(i));
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
                    if (currentLine != null && currentLine.equals(set.getKey())) {
                        continue;
                    } else {
                        System.out.println("take line " + set.getKey() + " at " + route.get(i));
                    }
                    //resultArray.add(set.getKey().toString());
                    resultArray.add(route.get(i));

                    currentLine = set.getKey();
                }
            }
        }
    }

    public static boolean lineHasBothStops (BusStop[] stops, BusStop currStop, BusStop nextStop) {

        if (Arrays.stream(stops).anyMatch(currStop::equals) && Arrays.stream(stops).anyMatch(nextStop::equals)) {
            return true;
        }
        return false;
    }

    public static boolean isEdgeStop (HashMap<BusStop, ArrayList<BusStop>> stopsList, BusStop stop, BusStop destination, BusStop start) {

        if (stopsList.get(stop).size() == 1 && !stopsList.get(stop).get(0).equals(destination) && !stop.equals(destination) && !stop.equals(start)) {
            return true;
        }

        return false;
    }
}