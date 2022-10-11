package busservice.models;

import java.util.ArrayList;

public class Bus {
    private int id;

    public ArrayList<BusStop> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<BusStop> route) {
        this.route = route;
    }

    private ArrayList<BusStop> route;

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
