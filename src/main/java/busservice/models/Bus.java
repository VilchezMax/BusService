package busservice.models;

import busservice.dao.DBInfoHandler;
import busservice.dao.IBusDAO;
import busservice.dao.IBusStopDAO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bus {
    private Integer id;

    private Integer line;

    private ArrayList<BusStop> route;

    public Bus() {

    }

    public ArrayList<BusStop> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<BusStop> route) {
        this.route = route;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {


        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        IBusDAO busMapper = session.getMapper(IBusDAO.class);
        IBusStopDAO busStopMapper = session.getMapper(IBusStopDAO.class);

        DBInfoHandler info = new DBInfoHandler();
        List<Bus> lines = info.getBuses();
        List<BusStop> route = null;
        try {
            route = busMapper.getRouteByBusId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "Bus{" +
                "id=" + id +
                ", line=" + line +
                ", route=" + route +
                '}';
    }
}
