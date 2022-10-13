package busservice;

import algorithm.DijkstraTest;
import busservice.models.BusStop;
import busservice.parsers.JacksonParser;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * Hello world!
 */
public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args) throws Exception {

        BusStopService busStopService = new BusStopService();

        BusService busService = new BusService();
        List<BusStop> busStops = busService.getRouteByBusId(1);
        List<BusStop> busStops1 = busService.getRouteByBusId(2);

        JacksonParser.parse(busStops);
        JacksonParser.parse(busStops);
        JacksonParser.parse(busStops);



        System.out.println(DijkstraTest.getShortestPath("Liverpool Street", "Waterloo"));
    }
}
