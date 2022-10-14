package busservice;

import busservice.models.Bus;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import busservice.services.mybatis.CityService;

import java.util.List;


public class App {
    public static void main(String[] args) {
//        System.out.println(DijkstraTest.getShortestPath("Knightsbridge", "Waterloo"));
        BusService busService = new BusService();
        BusStopService busStopService = new BusStopService();
        CityService cityService = new CityService();

        /* Test: BusStop.getAll() */
//        List<BusStop> allBusStops = busStopService.getAll();
//        allBusStops.forEach(System.out::println);
//        GUI gui = new GUI(allBusStops);

        /* Test BusStop.getById()*/
//        BusStop busStop = busStopService.getById(3);
//        System.out.println(busStop);

        /*Test: Bus.getById*/
//        Bus bus = busService.getById(1);
//        System.out.println(bus.getRoute());

        /* Test: Bus.getAll() */
        List<Bus> allBuses = busService.getAll();
        System.out.println(allBuses.get(0));

        /*Test Bus.getAllRoutes*/
//        List<List<BusStop>> allRoutes = busService.getAllRoutes();
//        System.out.println(allRoutes.get(0));
//        System.out.println(allRoutes.get(1));
//        System.out.println(allRoutes.get(2));

    }
}
