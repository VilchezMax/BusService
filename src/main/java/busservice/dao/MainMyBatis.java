package busservice.dao;

import busservice.models.Bus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainMyBatis {
    private static final Logger logger = LogManager.getLogger(MainMyBatis.class);

    public static void main(String[] args) {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();

            IBusDAO busMapper = session.getMapper(IBusDAO.class);

            DBInfoHandler info = new DBInfoHandler();
            HashMap<Integer, String[]> lines = info.getLinesStops();
            Bus test = busMapper.getById(1);
            //List<BusStop> route = busMapper.getRouteByBusId(1);
            HashMap<String, String[]> adjacent = info.getAdjacentStops();
            //logger.info(test);


            for (Map.Entry<Integer, String[]> set : lines.entrySet()) {

                logger.info(set.getKey() + " = " + Arrays.toString(set.getValue()));

            }

            logger.info(Arrays.toString(lines.get(13)).contains("Tower Hill"));

            adjacent.forEach((key, value) -> logger.info(key + " = " + Arrays.toString(value)));

            HashMap<String, int[]> coordinates = info.getStopsCoordinates();


            //HashMap<String, ArrayList<String>> adjacent = info.getAdjacentStops();
            //adjacent.forEach((key, value) -> logger.info(key + " = " + value));
//            for (BusStop stops : route) {
//                logger.info(stops);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
