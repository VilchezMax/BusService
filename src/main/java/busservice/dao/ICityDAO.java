package busservice.dao;

import busservice.models.Bus;
import busservice.models.City;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO extends IDAO<City> {
    void insert (City object) throws SQLException;
    void update (@Param("object") City object) throws SQLException;
    List<City> getAll () throws SQLException;
    City getById (int id) throws SQLException;
    void deleteById(int id) throws SQLException;
}
