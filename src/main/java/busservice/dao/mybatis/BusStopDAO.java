package busservice.dao.mybatis;

import busservice.dao.IBusStopDAO;
import busservice.models.BusStop;

import java.sql.SQLException;
import java.util.List;

public class BusStopDAO implements IBusStopDAO {
    @Override
    public BusStop getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<BusStop> getAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(BusStop object) throws SQLException {

    }

    @Override
    public void update(BusStop object) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }
}
