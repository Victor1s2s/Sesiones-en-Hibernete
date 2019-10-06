package DAOIplementations;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import intercaces.GenericDAO;

public class GenericDAOImpl <T, Id extends Serializable> implements GenericDAO<T, Id>{
	
	private Class<T> type;
	 private SessionFactory sessionFactory;
	 @Autowired
	 private EntityManagerFactory entityManagerFactory;
	
	private static Logger LOGGER = LoggerFactory.getLogger(GenericDAOImpl.class);

		
	
	public GenericDAOImpl(Class<T> type) {
		this.type = type;
		sessionFactory=entityManagerFactory.unwrap(SessionFactory.class);
	}

	@Override
	public T findOne(Id id) {
		Session session = sessionFactory.getCurrentSession();
        try {
        	Transaction trans=session.beginTransaction();
    	    T entity = (T)  session.get(type, id);
    	    trans.commit();
            return entity;
        } catch (Exception ex) {
        	LOGGER.error(ex.toString());
        }
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Session session = sessionFactory.getCurrentSession();
	    Transaction trans=session.beginTransaction();
	    final Criteria crit = session.createCriteria(type);
	    List<T> list = crit.list();
	    trans.commit();
	    return list;
	}


	@Override
	 public void create(T entity) {
		Session session = sessionFactory.getCurrentSession();
        
		try {
       	 Transaction trans=session.beginTransaction();
       	session.save(entity);
       	 trans.commit();
       
       } catch (Exception ex) {
       LOGGER.error(ex.toString());
       }
    }

	@Override
	public void update(T entity) {
		Session session = sessionFactory.getCurrentSession();
        try {
        	 Transaction trans=session.beginTransaction();
        	session.saveOrUpdate(entity);
        	 trans.commit();
        
        } catch (Exception ex) {
        LOGGER.error(ex.toString());
        }
		
	}

	@Override
	public void delete(T entity) {
		Session session = sessionFactory.getCurrentSession();
        try {
        	 Transaction trans=session.beginTransaction();
             session.delete(entity);
        	 trans.commit();
        
        } catch (Exception ex) {
        LOGGER.error(ex.toString());
        }
    }

	@Override
	public void deleteById(Id id) {
		
    	Session session = sessionFactory.getCurrentSession();
    
        try {
            session.beginTransaction();
            //T entity = session.load(type, id);
            T entity = (T) session.get(type, id);
            /*if (entity == null) {
                throw new BussinessException(new BussinessMessage(null, "Los datos a borrar ya no existen"));
            }*/
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception ex) {
                LOGGER.error(ex.toString());
        }
	}


}
