package busservice.dao;


import busservice.models.BusStop;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

public interface IBusStopDAO extends IDAO<BusStop> {
    BusStop getById(Integer id) throws SQLException;

    List<BusStop> getAll() throws SQLException;

    void insert(@Param("busStop") BusStop object) throws SQLException;

    void update(@Param("busStop") BusStop object) throws SQLException;

    void deleteById(Integer id) throws SQLException;
}
