package busservice.dao.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;

public class MySessionFactory {
    private static MySessionFactory instance;
    private static SqlSessionFactory factory;

    private final Logger logger = LogManager.getLogger(MySessionFactory.class);

    private MySessionFactory() {
        String configFile = "mybatis-config.xml";
        Reader configReader = null;
        try {
            configReader = Resources.getResourceAsReader(configFile);
            factory = new SqlSessionFactoryBuilder().build(configReader);
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
    }

    public static MySessionFactory getInstance() {
        if (instance == null) {
            instance = new MySessionFactory();
        }
        return instance;
    }

    public SqlSessionFactory getFactory() {
        return factory;
    }
}
