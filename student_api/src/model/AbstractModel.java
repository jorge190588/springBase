package model;

import java.util.*;	
import org.hibernate.*;

import error.CustomException;


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
	    	}
	    	catch(Throwable exception){
	    		result=null;
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	        	throw new CustomException(exception.getMessage(),exception,error.ErrorCode.DATABASE_TABLE,this.getClass().getSimpleName());
	    	}finally{
	    		if (session !=null){
	    			session.close();	
	    		}
	    	}
	        
	        return result;
	    }

	    public List<T> getAll(String condition) throws CustomException{
	    	List<T> result =null;
	    	Session session = null;
	    	Transaction transaction = null;
	    	try{
	    		session=HibernateUtil.getSessionFactory().openSession();
	    		transaction =session.beginTransaction();
	    		result= session.createQuery("FROM " + entityClass.getName() + " WHERE " + condition).getResultList();
	    		transaction.commit();
	    	}catch(Exception exception){
	    		result=null;
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	        	throw new CustomException(exception.getMessage(),exception,error.ErrorCode.DATABASE_TABLE,this.getClass().getSimpleName());
	    	}finally{
	    		if (session !=null){
	    			session.close();	
	    		}
	    	}
	        
	        return result;	        
	    }
	    
	    public boolean create(T entity) throws Exception{
	    	Session session = null;
	    	Transaction transaction = null;
	    	try{
	    		session =HibernateUtil.getSessionFactory().openSession();
	    		transaction =session.beginTransaction();
	    		session.save(entity);
	    		transaction.commit();
	    	}catch(Exception exception){
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	    		throw new CustomException(exception.getMessage(),exception,error.ErrorCode.DATABASE_TABLE,this.getClass().getSimpleName());
	    	}finally{
	    		if (session !=null){
	    			session.close();	
	    		}
	    	}
	    	return true;
	    }
	    
	    public void update(T entity) throws Exception{
	    	Session session = null;
	    	Transaction transaction = null;
	    	try{
	    		session =HibernateUtil.getSessionFactory().openSession();
	    		transaction =session.beginTransaction();
	            session.update(entity);
	    		transaction.commit();
	    	}catch(Exception exception){
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	    		throw new CustomException(exception.getMessage(),exception,error.ErrorCode.DATABASE_TABLE,this.getClass().getSimpleName());
	    	}finally{
	    		if (session !=null){
	    			session.close();	
	    		}
	    	}
	    }
	      
	    public void delete(T entity) throws CustomException{
	    	Session session = null;
	    	Transaction transaction = null;
	    	try{
	    		session =HibernateUtil.getSessionFactory().openSession();
	    		transaction =session.beginTransaction();
	    	    session.delete(entity);
	    		transaction.commit();
	    	}catch(Exception exception){
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	    		throw new CustomException(exception.getMessage(),exception,error.ErrorCode.DATABASE_TABLE,this.getClass().getSimpleName());
	    	}finally{
	    		if (session !=null){
	    			session.close();	
	    		}
	    	}
	    }

	    public List<T> query(String query) throws CustomException {
	    	List<T> result =null;
	    	Session session =null;
	    	Transaction transaction=null; 
	    	try{
	    		session = HibernateUtil.getSessionFactory().openSession();
	    		transaction = session.beginTransaction();
		        result = session.createQuery(query).getResultList();
		        transaction.commit();
	    	}catch(Exception exception){
	    		result=null;
	    		if (transaction !=null){
	    			transaction.rollback();
	    		}
	    		throw new CustomException(exception.getMessage(),exception,error.ErrorCode.DATABASE_TABLE,this.getClass().getSimpleName());
	    	}finally{
	    		if (session !=null){
	    			session.close();	
	    		}
	    	}
	        
	        return result;
	    }	    	
}
