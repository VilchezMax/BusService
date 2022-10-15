package busservice.dao.mybatis;

import busservice.dao.ICityDAO;
import busservice.models.City;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class CityDAO implements ICityDAO {

    final Logger LOGGER = LogManager.getLogger(CityDAO.class);

    @Override
    public City getById(Integer id) {
        City city = null;
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            ICityDAO cityDAO = session.getMapper(ICityDAO.class);
            city = cityDAO.getById(id);
            session.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return city;
    }

    @Override
    public List<City> getAll() {
        List<City> cities = null;
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            ICityDAO cityDAO = session.getMapper(ICityDAO.class);
            cities = cityDAO.getAll();
            session.commit();
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return cities;
    }

    @Override
    public void insert(City object) {
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            ICityDAO cityDAO = session.getMapper(ICityDAO.class);
            cityDAO.insert(object);
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
    public void update(City object) {
        SqlSession session = null;
        try {
            session = MySessionFactory.getInstance().getFactory().openSession();
            ICityDAO cityDAO = session.getMapper(ICityDAO.class);
            cityDAO.update(object);
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
            ICityDAO cityDAO = session.getMapper(ICityDAO.class);
            cityDAO.deleteById(id);
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
