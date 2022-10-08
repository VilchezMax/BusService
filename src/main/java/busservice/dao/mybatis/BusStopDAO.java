package busservice.dao.mybatis;

import busservice.dao.IBusStopDAO;
import busservice.models.Bus;
import busservice.models.BusStop;

import java.sql.SQLException;
import java.util.List;

public class BusStopDAO implements IBusStopDAO {
    @Override
    public void create(BusStop object) throws SQLException {

    }

    @Override
    public BusStop getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void update(BusStop object) throws SQLException {

    }

    @Override
    public void remove(Integer id) throws SQLException {

    }

    @Override
    public List<BusStop> getAll() throws SQLException {
        return null;
    }

    @Override
    public List<Bus> getAllBusesPassing() {
        return null;
    }
}
