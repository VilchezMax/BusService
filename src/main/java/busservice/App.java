package busservice;

import algorithm.DijkstraTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 */
public class App {
    final static Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        try {
            LOGGER.info(DijkstraTest.getShortestPath("Knightsbridge", "St. Paul's").replace(",", " "));
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
