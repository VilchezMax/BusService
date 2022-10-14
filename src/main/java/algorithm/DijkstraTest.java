package algorithm;

import busservice.dao.DBInfoHandler;

import java.sql.SQLException;
import java.util.*;

public class DijkstraTest {

    public static String getShortestPath(String from, String to) throws SQLException {
        {

            String start = from;

            ArrayList<String> visited = new ArrayList<>();
            ArrayList<String> unvisited = new ArrayList<>();

            DBInfoHandler infoHandler = new DBInfoHandler();

            HashMap<String, String[]> vertices = infoHandler.getAdjacentStops();



                    /*new HashMap<>() {
                {
                    put("Knightsbridge", new String[]{"Covent Garden", "Gloucester Road"});
                    put("Covent Garden", new String[]{"Leicester Square", "Knightsbridge"});
                    put("Gloucester Road", new String[]{"Hyde Park Corner", "Knightsbridge"});
                    put("Leicester Square", new String[]{"Covent Garden", "Tower Hill", "Hyde Park Corner"});
                    put("Hyde Park Corner", new String[]{"Gloucester Road", "Piccadilly Circus", "Leicester Square"});
                    put("Piccadilly Circus", new String[]{"Hyde Park Corner", "Liverpool Street", "Waterloo"});
                    put("Tower Hill", new String[]{"Leicester Square", "Blackfriars"});
                    put("Blackfriars", new String[]{"Tower Hill", "St. Paul's"});
                    put("St. Paul's", new String[]{"Blackfriars", "Waterloo"});
                    put("Waterloo", new String[]{"Piccadilly Circus", "St. Paul's"});
                    put("Liverpool Street", new String[]{"Piccadilly Circus"});
                }
            };*/

            HashMap<String, int[]> distances = infoHandler.getStopsCoordinates();

                    /*new HashMap<>() {
                {
                    put("Knightsbridge", new Integer[]{39,13});
                    put("Covent Garden", new Integer[]{36,14});
                    put("Gloucester Road",  new Integer[]{39,16});
                    put("Leicester Square", new Integer[]{35,17});
                    put("Hyde Park Corner",  new Integer[]{38,20});
                    put("Piccadilly Circus",  new Integer[]{35,23});
                    put("Tower Hill", new Integer[]{31,16} );
                    put("Blackfriars", new Integer[]{29,19} );
                    put("St. Paul's",  new Integer[]{29,22});
                    put("Waterloo",  new Integer[]{32,20} );
                    put("Liverpool Street", new Integer[]{34,25} );
                }
            };*/

            ArrayList<VertexTable> shortestTable = new ArrayList<>();

            for (Map.Entry<String, String[]> set : vertices.entrySet()) {
                VertexTable row = new VertexTable();
                row.setVertex(set.getKey());
                unvisited.add(set.getKey());
                if (set.getKey().equals(start)) {
                    row.setShortestFromStart(0);
                }

                shortestTable.add(row);

                //System.out.println(set.getKey() + " " + Arrays.toString(set.getValue()));
            }

            //double currShortest = Double.POSITIVE_INFINITY;
            //String closestVertex = null;

            int count = 0;
            while (lookForInfinity(shortestTable) || unvisited.size() > 0) {

                count++;
                if (count > 20) break;
                double currShortest = Double.POSITIVE_INFINITY;
                String closestVertex = null;
                for (VertexTable row : shortestTable) {
                    //System.out.println(row);

                    if (row.getShortestFromStart() < currShortest && !visited.contains(row.getVertex())) {

                        //System.out.println((row.getShortestFromStart() < currShortest)  + "&&" + !visited.contains(row.getVertex()));
                        currShortest = row.getShortestFromStart();
                        closestVertex = row.getVertex();
                    }
                }


                //System.out.println("Continue with: " + closestVertex);
                for (Map.Entry<String, String[]> set : vertices.entrySet()) {
                    if (set.getKey().equals(closestVertex)) {
                        //System.out.println("Try:" + Arrays.toString(set.getValue()));
                        //System.out.println("visited: " + Arrays.toString(visited.toArray())+  " unvisited: " + Arrays.toString(unvisited.toArray())) ;

                        for (String toVisit : set.getValue()) {
                            if (visited.contains(toVisit)) {
                                continue;
                            }
                            int[] coordinatesClosest = distances.get(closestVertex);
                            int[] coordinatesToVisit = distances.get(toVisit);

                            //System.out.println("closes coord : " + Arrays.toString(coordinatesClosest));
                            //System.out.println("visit coord: " + Arrays.toString(coordinatesToVisit));
                            int closestX = coordinatesClosest[0];
                            int closestY = coordinatesClosest[1];
                            int toVisitX = coordinatesToVisit[0];
                            int toVisitY = coordinatesToVisit[1];

                            double distance = Math.sqrt(Math.pow(toVisitY - closestY, 2) + Math.pow(toVisitX - closestX, 2));

                            for (VertexTable update : shortestTable) {


                                if (update.getVertex().equals(toVisit) && update.getShortestFromStart() > distance) {


                                    boolean flagD = false;
                                    for (VertexTable dist : shortestTable) {
                                        if (dist.getVertex().equals(closestVertex)) {
                                            //System.out.println("distance between " + toVisit + " and " + closestVertex + " is " + distance);
                                            //System.out.println("will sum : " +dist.getShortestFromStart());
                                            distance += dist.getShortestFromStart();
                                            //System.out.println("distance is : "+  distance + " sum of " + toVisit + " and " + closestVertex);
                                            flagD = true;
                                        }
                                        if (flagD) break;
                                    }


                                    if (update.getShortestFromStart() < distance) {
                                        continue;
                                    }

                                    //System.out.println("update is " + update.getVertex());
                                    //System.out.println("update distance is smaller than: " +distance  +" < " + update.getShortestFromStart()  );
                                    //System.out.println("distance is sum of distances from : " + toVisit + " and " + closestVertex);
                                    double newShortest = distance;


                                    for (VertexTable sumThis : shortestTable) {


                                        if (update.getShortestFromStart() < sumThis.getShortestFromStart() + distance) {
                                            newShortest += sumThis.getShortestFromStart();
                                            //System.out.println("setting : " + newShortest + " to " + update.getVertex());
                                            //System.out.println("sum of: " +sumThis.getShortestFromStart() +" and " + distance);
                                            update.setShortestFromStart(newShortest);
                                            update.setPrevVertex(closestVertex);
                                            break;
                                        }
                                        if (sumThis.getVertex().equals(update.getPrevVertex())) {
                                            newShortest += sumThis.getShortestFromStart();
                                            // System.out.println("setting : " + newShortest + " to " + update.getVertex());
                                            // System.out.println("sum of: " +sumThis.getShortestFromStart() +" and " + distance);
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
            //String result ="";

            boolean prevIsNull = false;

            while (!prevIsNull) {
                for (VertexTable show : shortestTable) {
                    //result += show.toString() + "\n";
                    //System.out.println(show);

                    if (show.getVertex().equals(to)) {

                        result.add(show.getVertex());
                        if (show.getPrevVertex() == null) {

                            prevIsNull = true;

                            break;
                        }


                        //result += show.getPrevVertex() + "-> ";

                        //result.add(show.getPrevVertex() + " ->");

                        to = show.getPrevVertex();
                    }

                }

            }

            Collections.reverse(result);
            showPathWithBuses(result);
            return result.toString();
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
                    System.out.println("get down at " + route.get(i));
                    break;
                }

                if (lineHasBothStops(set.getValue(), route.get(i), route.get(i + 1))) {
                    if (currentLine != null && currentLine.equals(set.getKey())) continue;

                    resultArray.add(set.getKey().toString());
                    resultArray.add(route.get(i));

                    System.out.println("take line " + set.getKey() + " at " + route.get(i));

                    currentLine = set.getKey();
                }
                //System.out.println(set.getKey() + " = " + Arrays.toString(set.getValue()));
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