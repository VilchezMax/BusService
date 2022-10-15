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
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return bus;
    }

    @Override
    public List<Bus> getAll() {
        List<Bus> buses = null;
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            IBusDAO busDAO = session.getMapper(IBusDAO.class);
            buses = busDAO.getAll();
            session.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return buses;
    }

    @Override
    public void insert(Bus object) {
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            IBusDAO busDAO = session.getMapper(IBusDAO.class);
            busDAO.insert(object);
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
    public void update(Bus object) {
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            IBusDAO busDAO = session.getMapper(IBusDAO.class);
            busDAO.update(object);
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
            IBusDAO busDAO = session.getMapper(IBusDAO.class);
            busDAO.deleteById(id);
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
    public List<BusStop> getRouteByBusId(Integer id) {
        List<BusStop> route = null;
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            IBusDAO busDAO = session.getMapper(IBusDAO.class);
            route = busDAO.getRouteByBusId(id);
            session.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return route;
    }

    @Override
    public List<List<BusStop>> getAllRoutes() throws SQLException {
        List<List<BusStop>> routes = null;
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            routes = session.getMapper(IBusDAO.class).getAllRoutes();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return routes;
    }
}
