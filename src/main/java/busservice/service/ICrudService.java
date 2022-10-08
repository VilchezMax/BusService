package busservice.service;

import java.sql.SQLException;
import java.util.List;

public interface ICrudService<T> {
    void create(T object) throws SQLException;

    T getById(Integer id) throws SQLException;

    void update(T object) throws SQLException;

    void remove(Integer id) throws SQLException;

    List<T> getAll() throws SQLException;
}
