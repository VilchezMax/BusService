package busservice.dao;

import busservice.models.BusStop;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MainMyBatis {
    private static final Logger logger = LogManager.getLogger(MainMyBatis.class);

    public static void main(String[] args) {
        try {
            BusService busService = new BusService();
            BusStopService busStopService = new BusStopService();


            List<BusStop> route = busService.getRouteByBusId(1);
            route.forEach(busStop -> logger.info(busStop));


            //HashMap<String, ArrayList<String>> adjacent = info.getAdjacentStops();
            //adjacent.forEach((key, value) -> logger.info(key + " = " + value));
//            for (BusStop stops : route) {
//                logger.info(stops);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
