package busservice.dao;

import algorithm.VertexTable;
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
import java.util.*;

public class MainMyBatis {
    private static final Logger logger = LogManager.getLogger(MainMyBatis.class);
    public static void main(String[] args) throws IOException, SQLException {
        try{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        IBusStopDAO busStopMapper = session.getMapper(IBusStopDAO.class);
        List<BusStop> busStops = busStopMapper.getAll();

        IBusDAO bus = session.getMapper(IBusDAO.class);
        List<Bus> buses = bus.getAll();


            DBInfoHandler infoHandler = new DBInfoHandler();

            HashMap<String, String[]> vertices = infoHandler.getAdjacentStops();


            for (Map.Entry<String, String[]> set : vertices.entrySet()) {

                System.out.println(set.getKey() + " " + Arrays.toString(set.getValue()));
            }

        for(BusStop stop : busStops){
            logger.info(stop);
        }

        for(Bus buss: buses){
            logger.info(buss);
        }

        session.commit();
        session.close();
    } catch (Exception e) {
        e.printStackTrace();
        logger.error(e.getMessage());
        }
    }
}
