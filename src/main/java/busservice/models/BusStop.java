package busservice.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "BusStop")
@XmlAccessorType(XmlAccessType.FIELD)

public class BusStop {
    @XmlElement(name = "id")
    private Integer id;
    @XmlElement(name = "longitude")
    private Integer longitude;
    @XmlElement(name = "latitude")
    private Integer latitude;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "is_terminal")
    private Boolean isTerminal;
    @XmlElement(name = "city")
    private City city;
    private List<Bus> buses;

    public BusStop() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    public double calculateDistanceTo(BusStop other) {
        int x1 = this.getLatitude();
        int y1 = this.getLongitude();
        int x2 = other.getLatitude();
        int y2 = other.getLongitude();

        return Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusStop)) return false;

        BusStop busStop = (BusStop) o;

        return id.equals(busStop.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
