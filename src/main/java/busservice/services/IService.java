package busservice.services;

import java.util.List;

public interface IService<T> {
    T getById(Integer id);

    List<T> getAll();

    void insert(T object);

    void update(T object);

    void deleteById(Integer id);
}
