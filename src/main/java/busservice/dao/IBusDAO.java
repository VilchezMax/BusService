package busservice.dao;

import busservice.models.Bus;
import busservice.models.BusStop;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface IBusDAO extends IDAO<Bus> {
    Bus getById(Integer id) throws SQLException;

    List<Bus> getAll() throws SQLException;

    void insert(@Param("bus") Bus object) throws SQLException;

    void update(@Param("bus") Bus object) throws SQLException;

    void deleteById(Integer id) throws SQLException;

    List<BusStop> getRouteByBusId(Integer id) throws SQLException;

    List<List<BusStop>> getAllRoutes() throws SQLException;
}
