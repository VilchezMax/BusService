package busservice.dao;

import busservice.models.Bus;
import busservice.models.BusStop;
import busservice.models.City;

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

public class MainMyBatis {
    private static final Logger logger = LogManager.getLogger(MainMyBatis.class);
    public static void main(String[] args) throws IOException, SQLException {
        try{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();


        session.commit();
        session.close();
    } catch (Exception e) {
        e.printStackTrace();
        logger.error(e.getMessage());
        }
    }
}
