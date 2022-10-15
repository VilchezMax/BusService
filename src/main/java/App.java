import busservice.models.BusStop;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import busservice.services.mybatis.CityService;
import gui.GUI;

import java.util.List;


public class App {
    public static void main(String[] args) {
//        System.out.println(DijkstraTest.getShortestPath("Knightsbridge", "Waterloo"));
        BusService busService = new BusService();
        BusStopService busStopService = new BusStopService();
        CityService cityService = new CityService();

        /* Test: BusStop.getAll() */
        List<BusStop> allBusStops = busStopService.getAll();
        GUI gui = new GUI(allBusStops);


    }
}
