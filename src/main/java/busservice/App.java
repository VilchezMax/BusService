package busservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println(DijkstraTest.getShortestPath("Knightsbridge", "Waterloo"));
    }
}
