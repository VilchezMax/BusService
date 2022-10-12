package busservice.services.mybatis;

import busservice.dao.mybatis.BusDAO;
import busservice.models.Bus;
import busservice.models.BusStop;
import busservice.services.IBusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BusService implements IBusService {
    final Logger LOGGER = LogManager.getLogger(BusService.class);

    BusDAO busDAO;

    @Override
    public Bus getById(Integer id) {
        Bus bus = null;
        try {
            bus = busDAO.getById(id);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return bus;
    }

    @Override
    public List<Bus> getAll() {
        return null;
    }

    @Override
    public void insert(Bus object) {

    }

    @Override
    public void update(Bus object) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<BusStop> getRouteByBusId(Integer id) {
        return null;
    }
}
