import algorithm.DijkstraTest;
import busservice.models.BusStop;
import busservice.services.mybatis.BusService;
import busservice.services.mybatis.BusStopService;
import busservice.services.mybatis.CityService;
import gui.GUI;

import java.sql.SQLException;
import java.util.List;


public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println(DijkstraTest.getShortestPath("Knightsbridge", "Facultad de Derecho"));
    }
}
