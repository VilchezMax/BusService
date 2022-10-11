package busservice.dao;

import busservice.models.Bus;
import busservice.models.BusStop;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBinfoHandler {

    IBusDAO busMapper;
    IBusStopDAO busStopMapper;

    public DBinfoHandler() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        busMapper = session.getMapper(IBusDAO.class);
        busStopMapper = session.getMapper(IBusStopDAO.class);
    }


    public ArrayList<Bus> getBuses() throws SQLException {

        List<Bus> buses = busMapper.getAll();


        return (ArrayList<Bus>) buses;
    }


    public HashMap<String, ArrayList<String>> getAdjacentStops() throws SQLException {

        HashMap<String, ArrayList<String>> stops = new HashMap<>();


        List<Bus> buses = busMapper.getAll();

        String prevStop = null;

        for (Bus bus : buses) {

            for (BusStop stop : bus.getRoute()) {


                if (!stops.containsKey(stop.getName())) {
                    stops.put(stop.getName(), new ArrayList<String>());
                } else {

                    stops.get(stop.getName()).add(prevStop);

                }

                stops.get(prevStop).add(stop.getName());
                stops.get(stop.getName()).add(prevStop);

                prevStop = stop.getName();
            }


        }
        return stops;
    }
}
