package busservice.dao;

import busservice.models.Bus;
import busservice.models.BusStop;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface IBusStopDAO extends IDAO<BusStop> {
    void insert (BusStop object) throws SQLException;
    void update (@Param("object") BusStop object) throws SQLException;
    List<BusStop> getAll () throws SQLException;
    BusStop getById (int id) throws SQLException;
    void deleteById(int id) throws SQLException;
}
