package busservice.dao;


import busservice.models.City;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO extends IDAO<City> {
    City getById(Integer id) throws SQLException;

    List<City> getAll() throws SQLException;

    void insert(@Param("city") City object) throws SQLException;

    void update(@Param("city") City object) throws SQLException;

    void deleteById(Integer id) throws SQLException;
}
