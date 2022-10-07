package busservice.dao;

import busservice.models.Bus;
import busservice.models.BusStop;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface IBusStopDAO {
    void insertBusStop (BusStop object) throws SQLException;
    void updateBusStop (@Param("id") int id, @Param("object") BusStop object) throws SQLException;
    List<BusStop> getAllBusStops () throws SQLException;
    BusStop getByIdBusStop (int id) throws SQLException;
    void deleteByIdBusStop(int id) throws SQLException;
}
