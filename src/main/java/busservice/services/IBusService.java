package busservice.services;

import busservice.models.Bus;
import busservice.models.BusStop;

import java.util.List;

public interface IBusService extends IService<Bus> {
    Bus getById(Integer id);

    List<Bus> getAll();

    void insert(Bus object);

    void update(Bus object);

    void deleteById(Integer id);

    List<BusStop> getRouteByBusId(Integer id);

}
