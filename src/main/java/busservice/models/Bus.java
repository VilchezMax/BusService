package busservice.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bus {
    private Integer id;

    private Integer line;

    private ArrayList<BusStop> route;

    public ArrayList<BusStop> getStops() {
        return stops;
    }

    public void setStops(ArrayList<BusStop> stops) {
        this.stops = stops;
    }

    private ArrayList<BusStop> stops;

    public Bus() {

    }

    public ArrayList<BusStop> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<BusStop> route) {
        this.route = route;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", route=" + route +
                '}';
    }
}
