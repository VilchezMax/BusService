package busservice.services.mybatis;

import busservice.dao.mybatis.CityDAO;
import busservice.models.City;
import busservice.services.IService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CityService implements IService<City> {

    final Logger LOGGER = LogManager.getLogger(CityService.class);

    CityDAO cityDAO = new CityDAO();

    @Override
    public City getById(Integer id) {
        City city = null;
        try {
            city = cityDAO.getById(id);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return city;
    }

    @Override
    public List<City> getAll() {
        List<City> cities = null;
        try {
            cities = cityDAO.getAll();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return cities;
    }

    @Override
    public void insert(City object) {
        try {
            cityDAO.insert(object);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @Override
    public void update(City object) {
        try {
            cityDAO.update(object);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            cityDAO.deleteById(id);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
