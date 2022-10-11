package busservice.models;

import java.util.ArrayList;

public class Bus {
    private int id;

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
