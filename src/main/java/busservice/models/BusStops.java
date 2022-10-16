package busservice.models;

import busservice.models.BusStop;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "busStops")
@XmlAccessorType(XmlAccessType.FIELD)

public class BusStops {

    @XmlElement(name = "busStop", nillable = true)
    @XmlElementWrapper(name = "busStops")

    private List<BusStop> busStopList = null;

    public List<BusStop> getBusStopList() {
        return busStopList;
    }

    public void setBusStopList(List<BusStop> busStopList) {
        this.busStopList = busStopList;
    }

    @Override
    public String toString() {
        return "BusStops{" +
                "busStopList=" + busStopList +
                '}';
    }
}
