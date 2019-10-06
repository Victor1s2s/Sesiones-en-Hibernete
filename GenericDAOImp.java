package DAOIplementations;



import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import intercaces.GenericDAO;

public class GenericDAOImp <T, Id extends Serializable> implements GenericDAO<T, Id>{

    private Class<T> type;

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    // Not showing implementations of getSession() and setSessionFactory()
    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public GenericDAOImp(Class<T> type) {
		this.type = type;
	}

        
        
        @Transactional(readOnly = true)
        @Override
        public T findOne(Id id) {
            return (T) getSession().get(type, id);
        }

        @SuppressWarnings("unchecked")
        @Transactional(readOnly = true)
        @Override
        public List<T> findAll() {
            return (List<T>) getSession().createCriteria(type).list();
        }
        

		@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
        @Override
        public void create(T entity) {
            getSession().save(entity);
        }

        @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
        @Override
        public void update(T entity) {
            getSession().update(entity);
        }
        
        @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
        @Override
        public void delete(T entity) {
            getSession().delete(entity);
        }

        @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
        @Override
        public void deleteById(Id id) {
            T entity = getSession().load(type, id);
            getSession().delete(entity);
        }

}
