package intercaces;
import java.util.List;

public interface GenericDAO <T,Id> {
    T findOne(Id id);
    List<T> findAll();
    void create(T entity);
    void update(T entity);
    void delete(T entity);
    void deleteById(Id id);    
}
