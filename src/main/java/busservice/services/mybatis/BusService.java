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

    BusDAO busDAO = new BusDAO();

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
        List<Bus> buses = null;
        try {
            buses = busDAO.getAll();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return buses;
    }

    @Override
    public void insert(Bus object) {
        try {
            busDAO.insert(object);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @Override
    public void update(Bus object) {
        try {
            busDAO.update(object);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @Override
    public void deleteById(Integer id) {
        try {
            busDAO.deleteById(id);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @Override
    public List<BusStop> getRouteByBusId(Integer id) {
        List<BusStop> route = null;
        LOGGER.info(1);
        try {
            route = busDAO.getRouteByBusId(id);

        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return route;
    }

    //NOT SUPPOSED TO BE HERE, WILL BE USED IN GETALL()
    public List<List<BusStop>> getAllRoutes() {
        List<List<BusStop>> routes = null;
        try {
            routes = busDAO.getAllRoutes();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return routes;

    }
}
