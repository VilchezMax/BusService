package busservice.dao.mybatis;

import busservice.dao.IBusDAO;
import busservice.models.Bus;
import busservice.models.BusStop;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BusDAO implements IBusDAO {
    @Override
    public void create(Bus object) throws SQLException {

    }

    @Override
    public Bus getById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public void update(Bus object) throws SQLException {

    }

    @Override
    public void remove(Integer id) throws SQLException {

    }

    @Override
    public List<Bus> getAll() throws SQLException {
        return null;
    }

    @Override
    public HashMap<Bus, LinkedList<BusStop>> getAllRoutes() {
        return null;
    }
}
