package busservice;

import algorithm.DijkstraTest;
import busservice.models.BusStop;
import busservice.models.BusStops;
import busservice.parsers.Parser;
import busservice.services.mybatis.BusService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;


/**
 * Hello world!
 */
public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args) throws Exception {

        BusService busService = new BusService();
        List<BusStop> busStops1 = busService.getRouteByBusId(1);

        BusStops shortestRoute = new BusStops();
        shortestRoute.setBusStopList(busStops1);

        File filename = new File("src/main/resources/xml/shortestRouteFound.xml");
        Parser.writeXml(shortestRoute,filename);

        File filename2 = new File("src/main/resources/json/shortestRouteFound.json");
        Parser.writeJson(shortestRoute,filename2);


        System.out.println(DijkstraTest.getShortestPath("Liverpool Street", "Waterloo"));
    }
}
