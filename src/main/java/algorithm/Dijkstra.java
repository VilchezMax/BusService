package algorithm;

import busservice.models.Bus;
import busservice.models.BusStop;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Dijkstra {

    private static final Logger logger = LogManager.getLogger(Dijkstra.class);

    public static ArrayList<BusStop> getShortestPath(BusStop from, BusStop to) {
        List<BusStop> visited = new ArrayList<>();
        List<BusStop> unvisited = new BusStopService().getAll();
        List<Bus> buses = new BusService().getAll();

        HashMap<BusStop, ArrayList<BusStop>> vertices = Dijkstra.getAdjacentStops(buses);
        ArrayList<VertexTableRow> shortestTable = new ArrayList<>();

        for (Map.Entry<BusStop, ArrayList<BusStop>> set : vertices.entrySet()) {
            VertexTableRow row = new VertexTableRow();
            row.setVertex(set.getKey());
            if (set.getKey().equals(from)) {
                row.setShortestFromStart(0);
            }
            shortestTable.add(row);
        }

        while (isMaxValue(shortestTable) && unvisited.size() > 0) {

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
                    for (BusStop toVisit : set.getValue()) {
                        if (!visited.contains(toVisit)) {

                            double distance = closestVertex.calculateDistanceTo(toVisit);

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
        System.out.println(showPathWithBuses(result, buses));
        return result;
    }

    public static boolean isMaxValue(ArrayList<VertexTableRow> testTable) {
        for (VertexTableRow row : testTable) {
            if (row.getShortestFromStart() == Double.POSITIVE_INFINITY) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> showPathWithBuses(ArrayList<BusStop> route, List<Bus> buses) {

        HashMap<Integer, BusStop[]> lines = Dijkstra.getLinesStops(buses);

        ArrayList<String> resultArray = new ArrayList<>();


        Integer currentLine = null;
        for (int i = 0; i < route.size(); i++) {

            for (Map.Entry<Integer, BusStop[]> line : lines.entrySet()) {
                if (i + 1 == route.size()) {
                    resultArray.add("Get off at " + route.get(i).getName() + "in " + route.get(i).getCity().getName());
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
                    } else if (auxArray[j].equals(route.get(i + 1))) {
                        next = j;
                    }
                }
                if (lineHasBothStops(line.getValue(), route.get(i), route.get(i + 1)) && (first == (next + 1) || first == next - 1)) {
                    continue;
                }
                if (lineHasBothStops(line.getValue(), route.get(i), route.get(i + 1))) {
                    if (!(currentLine != null && currentLine.equals(line.getKey()))) {
                        BusStop stop = route.get(i);
                        if (resultArray.isEmpty()) {
                            resultArray.add("Go to Stop " + stop.getName() + " in " + stop.getCity().getName() + " and get on Bus N° " + line.getKey());
                        } else {
                            resultArray.add("Get Bus N° " + line.getKey() + " at " + stop.getName() + " stop  in " + stop.getCity().getName());
                        }

                    }
                    currentLine = line.getKey();

                }
            }
        }
        return resultArray;
    }

    public static boolean lineHasBothStops(BusStop[] stops, BusStop currStop, BusStop nextStop) {
        return Arrays.asList(stops).contains(currStop) &&
                Arrays.asList(stops).contains(nextStop);
    }

    public static boolean isEdgeStop(HashMap<BusStop, ArrayList<BusStop>> stopsList, BusStop stop, BusStop destination, BusStop start) {
        return stopsList.get(stop).size() == 1 &&
                !stopsList.get(stop).get(0).equals(destination) &&
                !stop.equals(destination) &&
                !stop.equals(start);
    }

    public static HashMap<BusStop, ArrayList<BusStop>> getAdjacentStops(List<Bus> buses) {
        HashMap<BusStop, ArrayList<BusStop>> stops = new HashMap<>();
        for (Bus bus : buses) {
            BusStop prevStop = null;
            for (BusStop stop : bus.getRoute()) {
                if (!stops.containsKey(stop)) {
                    stops.put(stop, new ArrayList<>());
                } else {
                    if (prevStop != null) {
                        stops.get(stop).add(prevStop);
                    }
                }
                if (stops.containsKey(prevStop) && !stops.get(prevStop).contains(stop) && stop != null) {
                    stops.get(prevStop).add(stop);
                }
                if (!stops.get(stop).contains(prevStop) && prevStop != null) {
                    stops.get(stop).add(prevStop);
                }
                prevStop = stop;
            }
        }
        return stops;
    }

    public static HashMap<Integer, BusStop[]> getLinesStops(List<Bus> buses) {
        HashMap<Integer, BusStop[]> lines = new HashMap<>();

        for (Bus bus : buses) {
            ArrayList<BusStop> stopsAux = new ArrayList<>(bus.getRoute());

            BusStop[] stops = stopsAux.toArray(new BusStop[0]);
            lines.put(bus.getId(), stops);
        }
        return lines;
    }
}