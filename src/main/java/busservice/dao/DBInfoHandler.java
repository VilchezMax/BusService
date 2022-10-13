package busservice.dao;

import busservice.models.Bus;
import busservice.models.BusStop;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBInfoHandler {
    IBusDAO busMapper;
    IBusStopDAO busStopMapper;

    private static final Logger logger = LogManager.getLogger(DBInfoHandler.class);


    public DBInfoHandler() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();
            busMapper = session.getMapper(IBusDAO.class);
            busStopMapper = session.getMapper(IBusStopDAO.class);
        } catch (IOException e) {
            logger.error("Error getting mybatis config");
            logger.error(e.getMessage());
        }
    }

    public List<Bus> getBuses() {
        List<Bus> buses = new ArrayList<>();
        try {
            buses = busMapper.getAll();

        } catch (SQLException e) {
            logger.error("Error getting all buses");
            logger.error(e.getMessage());
        }
        return buses;
    }

    public HashMap<String, ArrayList<String>> getAdjacentStops() {
        HashMap<String, ArrayList<String>> stops = new HashMap<>();
        String prevStop = null;
        for (Bus bus : getBuses()) {
            for (BusStop stop : bus.getRoute()) {
                if (!stops.containsKey(stop.getName())) {
                    stops.put(stop.getName(), new ArrayList<String>());
                    if (prevStop != null) {
                        stops.get(stop.getName()).add(prevStop);
                    }
                }
                if (prevStop != null) {
                    stops.get(prevStop).add(stop.getName());
                    stops.get(stop.getName()).add(prevStop);
                }
                prevStop = stop.getName();
            }
        }
        return stops;
    }



    /*

    hash stop adj[]

    for buses
        for stops

           if  hash !has stop
               hash.key.add stop
               if prevstop
                hash.key.add prevstop
           else
               if hash.key !has stop
                    hash.add stop

           prevStop = stop

     */
}