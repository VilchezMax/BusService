package algorithm;

import busservice.models.Bus;
import busservice.models.BusStop;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
    public static void connections(List<BusStop> finalRoute, List<Bus> buses) {
        Map<Bus, List<BusStop>> busRoutes = null;
        for (Bus bus : buses) {
//            busRoutes = bus.getBusRoutes();
            for (Map.Entry<Bus, List<BusStop>> entry : busRoutes.entrySet()) {
                List<BusStop> busStops = entry.getValue();
                for (int i = 0; i < busStops.size() - 1; i++) {
                    if (finalRoute.contains(busStops.get(i)) && finalRoute.contains(busStops.get(i + 1))) {
                        finalRoute.add(busStops.get(i));
                        finalRoute.add(busStops.get(i + 1));
                    }
                }
            }
        }
        for (BusStop busStop : finalRoute) {
            for (Bus bus : buses) {
                for (BusStop stop : bus.getRoute()) {
                    if (stop.equals(busStop)) {
                        System.out.println(busStop + " is connected to " + bus);
                    }
                }
            }
        }






/*        for (int i = 0; i < finalRoute.size(); i++) {
            BusStop currentStop = finalRoute.get(i);
            BusStop nextStop = finalRoute.get(i + 1);

            Bus currentBus = finalRoute.get(i).getBuses().get(0);
            Bus nextBus = null;
            if (i + 1 < finalRoute.size()) {
                nextBus = finalRoute.get(i + 1).getBuses().get(0);
            }

            *//* get index in bus list from bus stop to check for equals *//*
            for (Bus bus : buses) {


            }
        }*/


    }

    public static void main(String[] args) {
        BusService busService = new BusService();
        BusStopService busStopService = new BusStopService();


        List<Bus> buses = busService.getAll();
        List<BusStop> busStops = busStopService.getAll();
        List<BusStop> possibleResult = new ArrayList<>();
        possibleResult.add(busStops.get(0));
        possibleResult.add(busStops.get(4));
        possibleResult.add(busStops.get(6));
        possibleResult.add(busStops.get(8));
        possibleResult.add(busStops.get(7));
        possibleResult.add(busStops.get(15));
        possibleResult.add(busStops.get(33));
        possibleResult.add(busStops.get(23));
        possibleResult.add(busStops.get(22));
        Utils.connections(possibleResult, buses);
    }
}
