package busservice;

import busservice.models.Bus;
import busservice.models.BusStop;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import busservice.services.mybatis.CityService;

import java.util.List;

public class ServicesTest {

    static BusService busService = new BusService();
    static BusStopService busStopService = new BusStopService();
    static CityService cityService = new CityService();

    public static void main(String[] args) {
        testBusService();
//        testBusStopService();
//        testCityService();
    }

    public static void testBusService() {
        System.out.println("---- Test: Bus Service------------------------------");

        /*get By ID*/
        System.out.println("---- Bus: get By Id");

        Bus bus = busService.getById(3);
        System.out.println(bus);

        /* get All */
/*        System.out.println("---- Bus: get All");

        List<Bus> allBuses = busService.getAll();
        System.out.println(allBuses.get(0));
        System.out.println(allBuses.get(allBuses.size() - 1));*/

        /*get All Routes*/
/*        System.out.println("---- Bus: get All Routes");
        List<List<BusStop>> allRoutes = busService.getAllRoutes();
        System.out.println(allRoutes.get(0));
        System.out.println(allRoutes.get(1));
        System.out.println(allRoutes.get(2));*/


    }

    public static void testBusStopService() {
        System.out.println("---- Test: BusStop Service-------------------");
        /* get By ID*/
        System.out.println("---- BusStop: get By Id");

        BusStop busStop = busStopService.getById(3);
        System.out.println(busStop);

        /* get All */
        System.out.println("---- BusStop: get All");

        List<BusStop> allBusStops = busStopService.getAll();
        allBusStops.forEach(System.out::println);
    }

    public static void testCityService() {
        System.out.println("---- Test: City Service-------------------");

    }
}
