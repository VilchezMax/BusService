package busservice;

import algorithm.DijkstraTest;

import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println(DijkstraTest.getShortestPath("Knightsbridge", "Facultad de Derecho"));


    }
}
