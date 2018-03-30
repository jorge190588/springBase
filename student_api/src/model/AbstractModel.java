package model;

import java.util.*;	
import org.hibernate.*;

import tools.CustomException;


@SuppressWarnings("unchecked")
public abstract class AbstractModel<T> {
	  	private Class<T> entityClass;

	    public AbstractModel() {
	    }

	    public AbstractModel(Class<T> entityClass) {
	        this.entityClass = entityClass;
	    }

	    public List<T> getAll() throws CustomException{
	    	List<T> result =null;
	    	Session session = null;
	    	Transaction transaction=null; 
	    	try{
	    		session=HibernateUtil.getSessionFactory().openSession();
	    		transaction = session.beginTransaction();
		        result = session.createQuery("FROM "+entityClass.getName()).getResultList();
		        transaction.commit();
	    	}catch(CustomException exception){
	    		result=null;
	    		throw new CustomException(exception);
	    	}finally{
	    		if (session !=null){
	    			session.close();	
	    		}
	    	}
	        
	        return result;
	    }

	    public List<T> getAll(String condition) {
	    	List<T> result =null;
	    	Session session = null;
	    	Transaction transaction = null;
	    	try{
	    		session=HibernateUtil.getSessionFactory().openSession();
	    		transaction =session.beginTransaction();
	    		result= session.createQuery("FROM " + entityClass.getName() + " WHERE " + condition).getResultList();
	    		transaction.commit();
	    	}catch(Exception ex){
	    		result=null;
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	    	}finally{
	    		session.close();
	    	}
	        
	        return result;	        
	    }
	    
	    public void create(T entity) throws Exception{
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	Transaction transaction = null;
	    	try{
	    		transaction =session.beginTransaction();
	    		session.save(entity);
	    		transaction.commit();
	    	}catch(Exception ex){
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	    	}finally{
	    		session.close();
	    	}
	    }
	    
	    public void update(T entity) throws Exception{
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	Transaction transaction = null;
	    	try{
	    		transaction =session.beginTransaction();
	            session.update(entity);
	    		transaction.commit();
	    	}catch(Exception ex){
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	    		ex.printStackTrace();  
	    	}finally{
	    		session.close();
	    	}
	    }
	      
	    public void delete(T entity) throws Exception{
	    	Session session = HibernateUtil.getSessionFactory().openSession();
	    	Transaction transaction = null;
	    	try{
	    		transaction =session.beginTransaction();
	    	       session.delete(entity);
	    		transaction.commit();
	    	}catch(Exception ex){
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	    		ex.printStackTrace();  
	    	}finally{
	    		session.close();
	    	}
	    }

	    public List<T> query(String query) {
	    	List<T> result =null;
	    	Session session =null;
	    	Transaction transaction=null; 
	    	try{
	    		session = HibernateUtil.getSessionFactory().openSession();
	    		transaction = session.beginTransaction();
		        result = session.createQuery(query).getResultList();
		        transaction.commit();
	    	}catch(Exception ex){
	    		result=null;
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	    	}finally{
	    		session.close();
	    	}
	        
	        return result;
	    }	    	
}
