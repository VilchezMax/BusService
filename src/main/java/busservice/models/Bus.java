package busservice.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bus {
    private int id;

    private HashMap<BusStop, List<BusStop>> route;

    public ArrayList<BusStop> getStops() {
        return stops;
    }

    public void setStops(ArrayList<BusStop> stops) {
        this.stops = stops;
    }

    private ArrayList<BusStop> stops;

    public Bus() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
