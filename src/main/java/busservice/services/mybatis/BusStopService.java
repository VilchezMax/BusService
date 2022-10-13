package busservice.services.mybatis;

import busservice.dao.mybatis.BusStopDAO;
import busservice.models.BusStop;
import busservice.services.IService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BusStopService implements IService<BusStop> {
    final Logger LOGGER = LogManager.getLogger(BusStopService.class);

    BusStopDAO busStopDAO;

    @Override
    public BusStop getById(Integer id) {
        BusStop busStop = null;
        try {
            busStop = busStopDAO.getById(id);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return busStop;
    }

    @Override
    public List<BusStop> getAll() {
        List<BusStop> busStops = null;
        try {
            busStops = busStopDAO.getAll();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return busStops;
    }

    @Override
    public void insert(BusStop object) {
        try {
            busStopDAO.insert(object);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @Override
    public void update(BusStop object) {
        try {
            busStopDAO.update(object);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            busStopDAO.deleteById(id);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
