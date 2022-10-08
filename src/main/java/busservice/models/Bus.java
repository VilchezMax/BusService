package busservice.models;

import java.util.HashMap;
import java.util.List;

public class Bus {
    private int id;

    private HashMap<BusStop, List<BusStop>> route;

    public Bus() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<BusStop, List<BusStop>> getRoute() {
        return route;
    }

    public void setRoute(HashMap<BusStop, List<BusStop>> route) {
        this.route = route;
    }

}
