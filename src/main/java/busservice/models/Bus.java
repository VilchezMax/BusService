package busservice.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
