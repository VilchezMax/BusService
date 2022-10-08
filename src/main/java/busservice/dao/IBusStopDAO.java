package busservice.dao;

import busservice.models.Bus;
import busservice.models.BusStop;

import java.util.List;

public interface IBusStopDAO extends ICrudDAO<BusStop> {

    List<Bus> getAllBusesPassing();

}

