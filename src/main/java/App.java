import busservice.models.BusStop;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import busservice.services.mybatis.CityService;

import java.util.List;


public class App {
    public static void main(String[] args) {
        /* Fetch data */
        BusService busService = new BusService();
        BusStopService busStopService = new BusStopService();
        CityService cityService = new CityService();

        List<BusStop> allBusStops = busStopService.getAll();

        /* Build GUI */
        //GUIFactory guiFactory = GUIFactory.getInstance();

        //GUI initialCityGui = guiFactory.createGUI(allBusStops, GUI.Stage.INITIAL_CITY);

        //GUI initialStopGui = guiFactory.createGUI(allBusStops, GUI.Stage.INITIAL_STOP, initialCityGui.getInitialCity());

        //GUI finalStopGui = guiFactory.createGUI(allBusStops, GUI.Stage.FINAL_STOP, initialCityGui.getInitialCity());


    }
}
