package busservice.dao;

import busservice.models.Bus;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface IBusDAO {
    void insertBus (Bus object) throws SQLException;
    void updateBus (@Param("id") int id, @Param("object") Bus object) throws SQLException;
    List<Bus> getAllBuses () throws SQLException;
    Bus getByIdBus (int id) throws SQLException;
    void deleteByIdBus(int id) throws SQLException;
}
