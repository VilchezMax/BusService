package busservice.models;


import java.util.List;

public class Bus {
    private Integer id;

    private Integer line;

    private List<BusStop> route;

    public Bus() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public List<BusStop> getRoute() {
        return route;
    }

    public void setRoute(List<BusStop> route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", line=" + line +
                ", route=" + route +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus)) return false;

        Bus bus = (Bus) o;

        return id.equals(bus.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
