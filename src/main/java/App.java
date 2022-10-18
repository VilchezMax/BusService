import algorithm.DijkstraTest;
import busservice.models.Bus;
import busservice.models.BusStop;
import busservice.models.BusStops;
import busservice.parsers.Parser;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import busservice.services.mybatis.CityService;
import busservice.views.gui.GUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws SQLException {
        GUI gui = GUI.getInstance();

        /* Fetch data */
        BusService busService = new BusService();
        BusStopService busStopService = new BusStopService();
        CityService cityService = new CityService();
        List<BusStop> allBusStops = busStopService.getAll();

        BusStop initialBusStop = null;
        BusStop finalBusStop = null;

        /* Displays Map for User */

        // gui.map();
        logger.info("Map displayed. User passes to choosing screen.");

        /*
         * Loads GUI with buttons for each stop
         * User chooses stops from different cities by clicking
         * 2 results are returned, later to be used by Dijkstra's algorithm.
         */

        ArrayList<BusStop> shortestRoute = DijkstraTest.getShortestPath(allBusStops.get(0), allBusStops.get(32));

        CompletableFuture<List<BusStop>> future = null;
        try {
            future = CompletableFuture.supplyAsync(() -> gui.election(allBusStops));


            while (!future.isDone()) {
                logger.info("Waiting for choices");
                Thread.sleep(1000);
            }
            initialBusStop = future.get().get(0);
            finalBusStop = future.get().get(1);
            logger.info("Initial bus stop: " + initialBusStop);
            logger.info("Final bus stop: " + finalBusStop);
        } catch (ExecutionException | InterruptedException e) {
            logger.warn("Error: " + e.getMessage());
        }

        /* Loading window - 6.5seconds */
        /*try {
            gui.loading(initialBusStop, finalBusStop);
        } catch (FileNotFoundException e) {
            logger.warn("Error: " + e.getMessage());
        }*/

        /*Dijkstra does his magic */





        /* SAVE TO JSON AND XML */
        // Testing xml and json parser



        File filename = new File("src/main/resources/results/xml/shortestRouteFound.xml");
        Parser.writeXml(shortestRoute,filename);

        File filename2 = new File("src/main/resources/results/json/shortestRouteFound.json");
        Parser.writeJson(shortestRoute,filename2);


        /* Displays results */
        try {
            //gui.displayResult(List<BusStop> result);
        } catch (Exception e) {
            logger.warn("Error: " + e.getMessage());
        }

    }
}
