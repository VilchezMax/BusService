package busservice.dao;

import busservice.models.Bus;
import busservice.models.City;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface ICityDAO {
    void insertCity (City object) throws SQLException;
    void updateCity (@Param("id") int id, @Param("object") City object) throws SQLException;
    List<City> getAllCities () throws SQLException;
    City getByIdCity (int id) throws SQLException;
    void deleteByIdCity(int id) throws SQLException;
}
