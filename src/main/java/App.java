import algorithm.DijkstraTest;
import busservice.dao.mybatis.BusStopDAO;
import busservice.models.BusStop;
import busservice.parsers.Parser;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import busservice.services.mybatis.CityService;
import org.apache.ibatis.annotations.Mapper;

import java.io.File;
import java.sql.SQLException;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException {
        /* Fetch data */
        BusService busService = new BusService();
        BusStopService busStopService = new BusStopService();
        CityService cityService = new CityService();

        List<BusStop> allBusStops = busStopService.getAll();

        /* Build GUI */
        //GUIFactory guiFactory = GUIFactory.getInstance();

        //System.out.println(DijkstraTest.getShortestPath("Knightsbridge", "Florida"));

        //GUI initialCityGui = guiFactory.createGUI(allBusStops, GUI.Stage.INITIAL_CITY);

        //GUI initialStopGui = guiFactory.createGUI(allBusStops, GUI.Stage.INITIAL_STOP, initialCityGui.getInitialCity());

        //GUI finalStopGui = guiFactory.createGUI(allBusStops, GUI.Stage.FINAL_STOP, initialCityGui.getInitialCity());

        // Testing xml and json parser

        /*List<BusStop> busStops1 = busService.getRouteByBusId(1);

        BusStops shortestRoute = new BusStops();
        shortestRoute.setBusStopList(busStops1);

        File filename = new File("src/main/resources/xml/shortestRouteFound.xml");
        Parser.writeXml(shortestRoute,filename);

        File filename2 = new File("src/main/resources/json/shortestRouteFound.json");
        Parser.writeJson(shortestRoute,filename2);
*/

        BusStopDAO busStopDAO = new BusStopDAO();

        //DijkstraTest.getShortestPath(busStopDAO.getById(1), busStopDAO.getById(33));
        System.out.println(busStopDAO.getById(31));
        System.out.println(busStopDAO.getById(15));
        DijkstraTest.getShortestPath(busStopDAO.getById(31), busStopDAO.getById(15));
    }
}
