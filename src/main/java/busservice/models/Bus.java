package busservice.models;

import java.util.ArrayList;

public class Bus {
    private Integer id;

    private Integer line;

    private ArrayList<BusStop> route;

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
                ", line=" + line +
                ", route=" + route +
                '}';
    }
}
