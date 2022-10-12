package busservice.dao.mybatis;

import busservice.dao.IBusDAO;
import busservice.models.Bus;
import busservice.models.BusStop;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class BusDAO implements IBusDAO {
    final Logger LOGGER = LogManager.getLogger(BusDAO.class);

    @Override
    public Bus getById(Integer id) {
        Bus bus = null;
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            IBusDAO busDAO = session.getMapper(IBusDAO.class);
            bus = busDAO.getById(id);
            session.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            session.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bus;
    }

    @Override
    public List<Bus> getAll() throws SQLException {
        return null;
    }

    @Override
    public void insert(Bus object) throws SQLException {

    }

    @Override
    public void update(Bus object) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public List<BusStop> getRouteByBusId(Integer id) throws SQLException {
        return null;
    }
}
