package busservice.service.mybatis;

import busservice.models.City;
import busservice.service.ICrudService;

import java.sql.SQLException;
import java.util.List;

public class CityService implements ICrudService<City> {
    @Override
    public void create(City object) throws SQLException {

    }

    @Override
    public City getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void update(City object) throws SQLException {

    }

    @Override
    public void remove(Integer id) throws SQLException {

    }

    @Override
    public List<City> getAll() throws SQLException {
        return null;
    }
}
