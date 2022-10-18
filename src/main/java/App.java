import algorithm.Dijkstra;
import busservice.models.Bus;
import busservice.models.BusStop;
import busservice.parsers.Parser;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import busservice.services.mybatis.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import views.gui.GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main (String[] args) throws SQLException {
        GUI gui = GUI.getInstance();

        /* Fetch data */
        BusService busService = new BusService();
        BusStopService busStopService = new BusStopService();
        CityService cityService = new CityService();
        List<BusStop> allBusStops = busStopService.getAll();

        BusStop initialBusStop = null;
        BusStop finalBusStop = null;

        /* Displays Map for User */

        gui.map();
        logger.info("Map displayed. User passes to choosing screen.");

        /*
         * Loads GUI with buttons for each stop
         * User chooses stops from different cities by clicking
         * 2 results are returned, later to be used by Dijkstra's algorithm.
         */

        CompletableFuture<List<BusStop>> futureChoice;
        try {
            futureChoice = CompletableFuture.supplyAsync(() -> gui.election(allBusStops));

            while (!futureChoice.isDone()) {
                logger.info("Waiting for choices");
                Thread.sleep(1000);
            }
            initialBusStop = futureChoice.get().get(0);
            finalBusStop = futureChoice.get().get(1);
            logger.info("FROM: " + initialBusStop.getName() + ", " + initialBusStop.getCity().getName());
            logger.info("TO: " + finalBusStop.getName() + " , " + finalBusStop.getCity().getName());
        } catch (ExecutionException | InterruptedException e) {
            logger.warn("Error: " + e.getMessage());
        }
        CompletableFuture<List<BusStop>> futureRoute = null;
        List<BusStop> shortestRoute = null;
        try {
            BusStop initialBusStopCopy = initialBusStop;
            BusStop finalBusStopCopy = finalBusStop;
            /*Dijkstra does his magic */
            futureRoute = CompletableFuture.supplyAsync(() -> Dijkstra.getShortestPath(initialBusStopCopy, finalBusStopCopy));
            /*Dijkstra.getShortestPath(initialBusStopCopy, finalBusStopCopy));*/

            futureRoute = CompletableFuture.supplyAsync(() ->
                    /*Dijkstra.getShortestPath(allBusStops.get(0), allBusStops.get(32)));*/
                    Dijkstra.getShortestPath(initialBusStopCopy, finalBusStopCopy));

            while (!futureRoute.isDone()) {
                logger.info("Waiting for route");
                /* 6.5-second loading window ~ while algorithm does the magic✨ */
                gui.loading(initialBusStopCopy, finalBusStopCopy);
                Thread.sleep(6500);
            }
            shortestRoute = futureRoute.get();
            logger.info("Shortest route: " + shortestRoute);
        } catch (ExecutionException | InterruptedException | FileNotFoundException e) {
            logger.warn("Error: " + e.getMessage());
        }

        /* Saves result to JSON & XML */
        File resultXML = new File("src/main/resources/results/xml/shortestRouteFound.xml");
        Parser.writeXml(shortestRoute, resultXML);

        File resultJSON = new File("src/main/resources/results/json/shortestRouteFound.json");
        Parser.writeJson(shortestRoute, resultJSON);

        List<Bus> buses = busService.getAll();
        List<String> strings = Dijkstra.showPathWithBuses((ArrayList<BusStop>) shortestRoute, buses);

        /* Displays results */
        try {
            gui.displayResult(strings);
        } catch (Exception e) {
            logger.warn("Error: " + e.getMessage());
        }
    }
}
