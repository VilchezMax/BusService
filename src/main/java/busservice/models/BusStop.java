package busservice.models;

import java.util.List;

public class BusStop {
    private Integer id;
    private Integer longitude;
    private Integer latitude;
    private String name;
    private Boolean isTerminal;
    private City city;
    private List<Bus> buses;

    public BusStop() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "\nBusStop{" + "\n" +
                "  id=" + id + "\n" +
                ", longitude=" + longitude + "\n" +
                ", latitude=" + latitude + "\n" +
                ", name='" + name + '\'' + "\n" +
                ", isTerminal=" + isTerminal + "\n" +
                ", city=" + city + "\n" +
                ", buses=" + buses + "\n" +
                '}';
    }
}
