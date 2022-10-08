package busservice.dao;

import busservice.models.Bus;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface IBusDAO extends IDAO<Bus> {
    void insert (Bus object) throws SQLException;
    void update (@Param("object") Bus object) throws SQLException;
    List<Bus> getAll () throws SQLException;
    Bus getById (int id) throws SQLException;
    void deleteById(int id) throws SQLException;
}
