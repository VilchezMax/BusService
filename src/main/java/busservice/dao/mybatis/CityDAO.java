package busservice.dao.mybatis;

import busservice.dao.ICityDAO;
import busservice.models.City;

import java.sql.SQLException;
import java.util.List;

public class CityDAO implements ICityDAO {
    @Override
    public City getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<City> getAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(City object) throws SQLException {

    }

    @Override
    public void update(City object) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }
}
