package busservice.service;

import busservice.models.Bus;
import busservice.models.BusStop;

import java.util.List;

public interface IBusStopService extends ICrudService<BusStop> {

    List<Bus> getAllBusesPassing(); // length > 1 means that is a bus stop that connects multiple bus routes.
}
