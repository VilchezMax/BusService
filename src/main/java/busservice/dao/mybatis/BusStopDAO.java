package busservice.dao.mybatis;

import busservice.dao.IBusStopDAO;
import busservice.models.BusStop;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class BusStopDAO implements IBusStopDAO {

    final Logger LOGGER = LogManager.getLogger(BusStopDAO.class);

    @Override
    public BusStop getById(Integer id) {
        BusStop busStop = null;
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            IBusStopDAO busStopDAO = session.getMapper(IBusStopDAO.class);
            busStop = busStopDAO.getById(id);
            session.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return busStop;
    }

    @Override
    public List<BusStop> getAll() {
        List<BusStop> busStops = null;
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            IBusStopDAO busStopDAO = session.getMapper(IBusStopDAO.class);
            busStops = busStopDAO.getAll();
            session.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return busStops;
    }

    @Override
    public void insert(BusStop object) {
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            IBusStopDAO busStopDAO = session.getMapper(IBusStopDAO.class);
            busStopDAO.insert(object);
            session.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(BusStop object) {
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            IBusStopDAO busStopDAO = session.getMapper(IBusStopDAO.class);
            busStopDAO.update(object);
            session.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            IBusStopDAO busStopDAO = session.getMapper(IBusStopDAO.class);
            busStopDAO.deleteById(id);
            session.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
