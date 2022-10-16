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

    private static final Logger logger = LogManager.getLogger (DBInfoHandler.class);

    public DBInfoHandler () {
        try {
            InputStream inputStream = Resources.getResourceAsStream ("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder ().build (inputStream);
            SqlSession session = sqlSessionFactory.openSession ();
            busMapper = session.getMapper (IBusDAO.class);
            busStopMapper = session.getMapper (IBusStopDAO.class);
        } catch (IOException e) {
            logger.error ("Error getting mybatis config");
            logger.error (e.getMessage ());
        }
    }

    public List<Bus> getBuses () {
        List<Bus> buses = new ArrayList<> ();
        try {
            buses = busMapper.getAll ();
        } catch (SQLException e) {
            logger.error ("Error getting all buses");
            logger.error (e.getMessage ());
        }
        return buses;
    }

    public HashMap<String, ArrayList<String>> getAdjacentStops () {
        HashMap<String, ArrayList<String>> stops = new HashMap<> ();
        for (Bus bus : getBuses ()) {
            String prevStop = null;
            for (BusStop stop : bus.getRoute ()) {
                if (!stops.containsKey (stop.getName ())) {
                    stops.put (stop.getName (), new ArrayList<> ());
                } else {
                    if (prevStop != null) {
                        stops.get (stop.getName ()).add (prevStop);
                    }
                }
                if (stops.containsKey (prevStop) && !stops.get (prevStop).contains (stop.getName ()) && stop.getName () != null) {
                    stops.get (prevStop).add (stop.getName ());
                }
                if (!stops.get (stop.getName ()).contains (prevStop) && prevStop != null) {
                    stops.get (stop.getName ()).add (prevStop);
                }
                prevStop = stop.getName ();
            }
        }
        return stops;
    }

    public HashMap<String, int[]> getStopsCoordinates () throws SQLException {
        ArrayList<BusStop> stops = (ArrayList<BusStop>) busStopMapper.getAll ();

        HashMap<String, int[]> result = new HashMap<> ();

        for (BusStop current : stops) {
            result.put (current.getName (), new int[]{current.getLatitude (), current.getLongitude ()});
        }

        return result;
    }

    public HashMap<Integer, String[]> getLinesStops () {
        HashMap<Integer, String[]> lines = new HashMap<> ();

        for (Bus bus : getBuses ()) {

            ArrayList<String> stopsAux = new ArrayList<> ();

            for (BusStop stop : bus.getRoute ()) {
                stopsAux.add (stop.getName ());
            }
            String[] stops = stopsAux.toArray (new String[0]);

            lines.put (bus.getId (), stops);
        }

        return lines;
    }

/*    public HashMap<String, ArrayList<String>> getAdjacentStops2() {
        HashMap<String, ArrayList<String>> stops = new HashMap<>();
        String prevStop = null;
        for (Bus bus : getBuses()) {
            for (BusStop stop : bus.getRoute()) {
                if (!stops.containsKey(stop.getName())) {
                    stops.put(stop.getName(), new ArrayList<String>());
                    if (prevStop != null) {
                        stops.get(stop.getName()).add(prevStop);}
                } else {
                    stops.get(stop.getName()).add(prevStop);
                }
                if (prevStop != null) {
                    stops.get(prevStop).add(stop.getName());
                    stops.get(stop.getName()).add(prevStop);
                }
                prevStop = stop.getName();
            }
        }
        return stops;
    }*/
}