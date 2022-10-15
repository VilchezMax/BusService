package busservice.views;

import busservice.models.BusStop;
import busservice.models.City;
import busservice.services.mybatis.BusStopService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/* GUI BUILDER
 * Populate GUI characteristics(stage,data,stops,cities chosen)
 * Used by GUI Factory
 */

/* GUI FACTORY
 * Create GUIs using GUI BUILDER
 * Used by App
 */

/* GUI STAGES
 * 1. Choose initial city
 * 2. Choose initial Stop
 * 3. Final City is chosen automatically
 * 4. Choose final Stop
 * 5. Show results
 *
 */
public class GUI<T> {

    private Stage stage;
    private List<T> data;

    private City initialCity;
    private City finalCity;
    private BusStop initialStop;
    private BusStop finalStop;

    public enum Stage {
        INITIAL_CITY, INITIAL_STOP, FINAL_CITY, FINAL_STOP
    }

    GUI() {
        /*switch(Stage stage) {
            case INITIAL_CITY:
                new GUI(busStops, Stage.INITIAL_CITY);
                break;
            case INITIAL_STOP:
                new GUI(busStops, Stage.INITIAL_STOP);
                break;
            case FINAL_CITY:
                new GUI(busStops, Stage.FINAL_CITY);
                break;
            case FINAL_STOP:
                new GUI(busStops, FINAL_STOP);
                break;
        }*/
    }

    public void initGUI(List<T> data) {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame("BUS SERVICE");

        int rows = (data.size() / 2) + 1;

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(rows, 2, 10, 10));

        JLabel city1 = new JLabel("Choose bus stop from city 1");
        JLabel city2 = new JLabel("");
        panel.add(city1);
        panel.add(city2);


        /*busStops.sort(Comparator.comparing(o -> o.getCity().getId()));
        for (BusStop busStop : busStops) {
            JButton button = new JButton(busStop.getName());

            button.addActionListener(e -> {
                System.out.println(busStop.getName());
            });
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Bus stops");
        frame.pack();
        frame.setVisible(true);*/

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public City getInitialCity() {
        return initialCity;
    }

    public void setInitialCity(City initialCity) {
        this.initialCity = initialCity;
    }

    public City getFinalCity() {
        return finalCity;
    }

    public void setFinalCity(City finalCity) {
        this.finalCity = finalCity;
    }


    public static void main(String[] args) {
        List<BusStop> allBusStops = new BusStopService().getAll();
        GUI gui = new GUI();
        gui.initGUI(allBusStops);
    }

    public static void cityGUI(List<City> cities) {

    }

    public static void busStopsGUI(List<BusStop> busStops) {

    }

}
