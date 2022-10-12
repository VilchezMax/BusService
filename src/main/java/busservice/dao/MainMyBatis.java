package busservice.dao;

import busservice.models.Bus;
import busservice.models.BusStop;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;

public class MainMyBatis {
    private static final Logger logger = LogManager.getLogger(MainMyBatis.class);

    public static void main(String[] args) {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession session = sqlSessionFactory.openSession();

            IBusDAO busMapper = session.getMapper(IBusDAO.class);
            IBusStopDAO busStopMapper = session.getMapper(IBusStopDAO.class);

            DBInfoHandler info = new DBInfoHandler();
            List<Bus> lines = info.getBuses();
            Bus test = busMapper.getById(1);
            BusStop busStop = busStopMapper.getById(1);
            List<BusStop> route = busMapper.getRouteByBusId(1);
            //HashMap<String, ArrayList<String>> adjacent = info.getAdjacentStops();


            //adjacent.forEach((key, value) -> logger.info(key + " = " + value));

            for (BusStop stops : route) {
                logger.info(stops);
            }

            logger.info(test);

            session.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
