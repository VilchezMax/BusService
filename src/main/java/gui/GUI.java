package gui;

import busservice.models.BusStop;
import busservice.services.mybatis.BusStopService;
import busservice.services.mybatis.CityService;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class GUI {
    //TODO:
    // THIS CLASS WILL BE USED TO CREATE USER INTERFACE FOR BUS_STOP SELECTION
    // BUTTONS WILL BE GENERATED FROM AN ARRAY OF STATIONS, YET TO BE IMPLEMENTEDgit
    // THIS IS JUST AN EXAMPLE OF HOW IT SHOULD LOOK LIKE


    public GUI(List<BusStop> busStops) {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("My First gui");

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(busStops.size() + 1, 2, 10, 10));

        JLabel city1 = new JLabel("Choose bus stop from city 1");
        JLabel city2 = new JLabel("Choose bus stop from city 2");

        busStops.sort(Comparator.comparing(o -> o.getCity().getId()));

        CityService cityService = new CityService();
        cityService.getAll().forEach(System.out::println);
//        for (BusStop busStop : busStops) {
//            if (busStop.getCity().getId() == 1) {
//                JButton button = new JButton(busStop.getName());
//                panel.add(button);
//            }
//        }
//
//        for (BusStop busStop : busStops) {
//            if (busStop.getCity().getId() == 2) {
//                JButton button = new JButton(busStop.getName());
//                panel.add(button);
//            }
//        }
/*        JButton buttonRetiro = new JButton("Retiro");
        JButton buttonWaterloo = new JButton("Waterloo");*/


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bus stops");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        List<BusStop> allBusStops = new BusStopService().getAll();
        GUI gui = new GUI(allBusStops);
    }
}
