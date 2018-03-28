package model;

import java.util.*;	
import org.hibernate.*;


@SuppressWarnings("unchecked")
public abstract class AbstractModel<T> {
	  	private Class<T> entityClass;

	    public AbstractModel() {
	    }

	    public AbstractModel(Class<T> entityClass) {
	        this.entityClass = entityClass;
	    }

	    public List<T> getAll() {
	    	List<T> result =null;
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	Transaction transaction=null; 
	    	try{
	    		transaction = session.beginTransaction();
		        result = session.createQuery("FROM "+entityClass.getName()).getResultList();
		        transaction.commit();
	    	}catch(Exception ex){
	    		System.err.println("Initial SessionFactory creation failed." + ex);
	    		result=null;
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	    	}finally{
	    		session.close();
	    	}
	        
	        return result;
	    }

	    public List<T> getAll(String condition) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction trans = session.beginTransaction();
	        trans.begin();
	        Query query = session.createQuery("FROM " + entityClass.getName() + " WHERE " + condition);
	        //System.out.println("======FROM " + entityClass.getName()+ " WHERE "+condition+"======");
	        List<T> lst = query.list();
	        trans.commit();
	        session.close();
	        return lst;
	    }

	    public List<T> createHQLQuery(String hqlQuery) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction trans = session.beginTransaction();
	        trans.begin();
	        Query query = session.createQuery(hqlQuery);
	        //System.out.println("======FROM " + entityClass.getName()+ " WHERE "+condition+"======");
	        List<T> lst = query.list();
	        trans.commit();
	        session.close();
	        return lst;
	    }

	    public List<T> search(String condition) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction trans = session.beginTransaction();
	        trans.begin();
	        Query query = session.createQuery("FROM " + entityClass.getName() + " WHERE " + condition);
	        //System.out.println("======FROM " + entityClass.getName()+ " WHERE "+condition+"======");
	        List<T> lst = query.list();
	        trans.commit();
	        session.close();
	        return lst;
	    }

	    public void add(T entity) throws Exception{
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction trans = session.beginTransaction();
	        try {            
	            session.save(entity);
	            System.out.println("data saved on"+entity.getClass() );
	            trans.commit();            
	        } catch (Exception ex) {
	            trans.rollback();            
	            ex.printStackTrace();            
	            throw new Exception(ex.getMessage());
	        }        
	        session.close();
	    }

	    public void update(T entity) throws Exception{
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction trans = session.beginTransaction();        
	        try {
	            trans.begin();
	            session.update(entity);
	            trans.commit();
	        } catch (Exception ex) {
	            trans.rollback();
	            ex.printStackTrace();  
	            throw new Exception(ex.getMessage());
	        }        
	        session.close();
	    }

	    public void delete(T entity) throws Exception {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction trans = session.beginTransaction();        
	        try {
	            trans.begin();
	            session.delete(entity);
	            trans.commit();
	        } catch (Exception ex) {
	            trans.rollback();
	            ex.printStackTrace();  
	            throw new Exception(ex.getMessage());
	        }        
	        session.close();
	    }
	
}
