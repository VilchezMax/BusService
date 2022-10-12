package busservice.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    T getById (Integer id) throws SQLException;

    List<T> getAll () throws SQLException;

    void insert (T object) throws SQLException;

    void update (T object) throws SQLException;

    void deleteById(Integer id) throws SQLException;
}

