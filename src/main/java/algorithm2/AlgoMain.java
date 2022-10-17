package algorithm2;

import busservice.models.Bus;
import busservice.models.BusStop;
import busservice.services.mybatis.BusStopService;

import java.util.ArrayList;
import java.util.List;

public class AlgoMain {
    public static void main(String[] args) {
        /* List<BusStop> allBusStops */
        BusStopService busStopService = new BusStopService();
        List<BusStop> allBusStops = busStopService.getAll();

        List<Vertex> vertices = createVertices(allBusStops);

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.computePath(vertices.get(0));

        List<Vertex> result = dijkstra.getShortestPathTo(vertices.get(18));
        result.forEach(vertex -> {
            System.out.println(vertex.getBusStop().getName());
        });
    }

    public static List<Vertex> createVertices(List<BusStop> allBusStops) {
        List<Vertex> vertices = new ArrayList<>();

        /* Fills 1vertex:1BusStop */
        for (BusStop busStop : allBusStops) {
            Vertex vertex = new Vertex(busStop);

            /* Gets Adjacencies to fill Vertex Edges */
            for (Bus bus : busStop.getBuses()) {
                List<BusStop> routes = bus.getRoute();

                int index = routes.indexOf(busStop);
                /* Gets position of bus stop in route
                 * If it has a connection:
                 * it calculates distance to that vertex and stores it as info on that edge,
                 * and that edge is stored on that vertex.
                 */

                if (index > 0) { /* Has previous connection in that bus route */
                    /*
                     * This nesting in this if and the next is because instead of "route.get(index-1)",
                     * we get the data from the original array "allBusStops", which is higher up the hierarchy,
                     * so it contains more layers of nested data, and we'll save that one.
                     */
                    BusStop previousBusStop = allBusStops.get(allBusStops.indexOf(routes.get(index - 1)));
                    double distance = vertex.calculateDistanceTo(previousBusStop);
                    Edge edgeToPrev = new Edge(distance, vertex, new Vertex(previousBusStop));
                    if (!vertex.getEdges().contains(edgeToPrev)) {
                        vertex.addNeighbour(edgeToPrev);
                    }
                }

                if (index < routes.size() - 1) { /* Has next connection in that bus route */
                    BusStop nextBusStop = allBusStops.get(allBusStops.indexOf(routes.get(index + 1)));
                    double distance = vertex.calculateDistanceTo(nextBusStop);
                    Edge edgeToNext = new Edge(distance, vertex, new Vertex(nextBusStop));
                    if (!vertex.getEdges().contains(edgeToNext)) {
                        vertex.addNeighbour(edgeToNext);
                    }
                }
            }
            /* Adds vertex with their edges stored.*/
            vertices.add(vertex);
        }
        return vertices;
    }


}
