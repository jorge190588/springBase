package model;

import java.util.*;	
import org.hibernate.*;

import entities.Entiti;
import error.CustomException;
import error.ErrorFormat;
import tools.Pagination;
import tools.RestResponse;


@SuppressWarnings("unchecked")
public abstract class AbstractModel<T> {
	  	private Class<T> entityClass;
	  	
	    public AbstractModel() {}

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
	    		if (transaction !=null && transaction.isActive()){
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
	    
	    public List<T> getPage(int page, int pageSize) throws CustomException{
	    	page = page-1;
	    	List<T> result =null;
	    	Session session = null;
	    	Transaction transaction=null; 
	    	try{
	    		session=HibernateUtil.getSessionFactory().openSession();
	    		transaction = session.beginTransaction();
	    		result = session.createQuery("FROM "+entityClass.getName()+" order by id ").setFirstResult(page* pageSize).setMaxResults(pageSize).getResultList();
		        transaction.commit();
	    	}
	    	catch(Throwable exception){
	    		result=null;
	    		if (transaction !=null && transaction.isActive()){
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
	    
	    public Pagination getPagination(int page) throws CustomException{
	    	Pagination _page = new Pagination();
	    	_page.setCurrentPage(page);
	    	
	    	Session session = null;
	    	Transaction transaction=null; 
	    	try{
	    		session=HibernateUtil.getSessionFactory().openSession();
	    		transaction = session.beginTransaction();
	    		int rows= ((Long)session.createQuery("select count(id) FROM "+entityClass.getName()).uniqueResult()).intValue();
	    		_page.setTotalRows(rows);
		        transaction.commit();
	    	}
	    	catch(Throwable exception){
	    		_page.setTotalRows(0);
	    		if (transaction !=null && transaction.isActive()){
	    			transaction.rollback();
	    		}
	        	throw new CustomException(exception.getMessage(),exception,error.ErrorCode.DATABASE_TABLE,this.getClass().getSimpleName());
	    	}finally{
	    		if (session !=null){
	    			session.close();	
	    		}
	    	}
	        
	    	_page.calculate_maxPage();
	    	_page.setPreviousPage();
	    	_page.setNext();
	    	_page.setStart();
	    	_page.setEnd();
	        return _page;
	    }

		public List<T> getAll(String condition) throws CustomException{
	    	List<T> result =null;
	    	Session session = null;
	    	Transaction transaction = null;
	    	if (condition.length()>0) condition = " where "+condition;
	    	
	    	try{
	    		session=HibernateUtil.getSessionFactory().openSession();
	    		transaction =session.beginTransaction();
	    		result= session.createQuery("FROM " + entityClass.getName() + condition).getResultList();
	    		transaction.commit();
	    	}catch(Exception exception){
	    		result=null;
	    		if (transaction !=null && transaction.isActive()){
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
			    
	    public T create(T entity) throws Throwable{
	    	Session session = null;
	    	Transaction transaction = null;
	    	try{
	    		session =HibernateUtil.getSessionFactory().openSession();
	    		transaction =session.beginTransaction();
	    		session.save(entity);
	    		transaction.commit();
	    	}catch(Throwable exception){
	    		if (transaction !=null && transaction.isActive()){
	    			transaction.rollback();
	    		}
	    		throw new CustomException(exception.getMessage(),exception,error.ErrorCode.DATABASE_TABLE,this.getClass().getSimpleName());
	    	}finally{
	    		if (session !=null){
	    			session.close();	
	    		}
	    	}
	    	return entity;
	    }
	    
	    public T update(T entity) throws Throwable{
	    	Session session = null;
	    	Transaction transaction = null;
	    	try{
	    		session =HibernateUtil.getSessionFactory().openSession();
	    		transaction =session.beginTransaction();
	            session.update(entity);
	    		transaction.commit();
	    	}catch(Throwable exception){
	    		if (transaction !=null && transaction.isActive()){
	    			transaction.rollback();
	    		}
	    		throw new CustomException(exception.getMessage(),exception,error.ErrorCode.DATABASE_TABLE,this.getClass().getSimpleName());
	    	}finally{
	    		if (session !=null){
	    			session.close();	
	    		}
	    	}
	    	
	    	return entity;
	    }
	      
	    public Boolean delete(T entity) throws CustomException{
	    	Session session = null;
	    	Transaction transaction = null;
	    	try{
	    		session =HibernateUtil.getSessionFactory().openSession();
	    		transaction =session.beginTransaction();
	    	    session.delete(entity);
	    		transaction.commit();
	    	}catch(Exception exception){
	    		if (transaction !=null && transaction.isActive()){
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
	    		if (transaction !=null && transaction.isActive()){
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
