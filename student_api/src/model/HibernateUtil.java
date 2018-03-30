package model;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.log4j.spi.ErrorCode;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.service.ServiceRegistry;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import entities.*;
import tools.CustomException;

public class HibernateUtil {
	
    //XML based configuration
    private static SessionFactory sessionFactory;

    //Annotation based configuration
    private static SessionFactory sessionAnnotationFactory;

    //Property based configuration
    private static SessionFactory sessionJavaConfigFactory;
   
    private static SessionFactory buildSessionFactory() throws CustomException {
        try{
    	
    		 StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
    		 .configure( "hibernate.cfg.xml" )
    		 .build();
    		 
    		 Metadata metadata = new MetadataSources( standardRegistry )
    		 .addAnnotatedClass(Student.class ) 
    		 // You can add more entity classes here like above
    		 .addResource( "entities/Student.hbm.xml" )
    		 .getMetadataBuilder()
    		 .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
    		 .build();
    		 
    		 SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
    		 return sessionFactory;
    		 
        }catch(Exception exception){       	
        	String _className =  Hibernate.class.getSimpleName();
        	throw new CustomException(exception.getMessage(),exception,tools.ErrorCode.DATABASE,_className );
        }
    }

    private static SessionFactory buildSessionAnnotationFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate-annotation.cfg.xml");
            System.out.println("Hibernate Annotation Configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate Annotation serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = new Configuration();

            //Create Properties, can be read from property files too
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            props.put("hibernate.connection.url", "jdbc:sqlserver://192.168.99.100;databaseName=RandomStudent");
            props.put("hibernate.connection.username", "sa");
            props.put("hibernate.connection.password", "ComputerDB190%");
            props.put("hibernate.current_session_context_class", "thread");

            configuration.setProperties(props);

            //we can set mapping file or class with annotation
            //addClass(Employee1.class) will look for resource
            // com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
            configuration.addAnnotatedClass(Quiz.class);
            configuration.addAnnotatedClass(QuizDetail.class);
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(Teacher.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() throws CustomException {
        if(sessionFactory == null)
			try {
				sessionFactory = buildSessionFactory();
				if (sessionFactory==null){
					String _className =  Hibernate.class.getSimpleName();
					throw new CustomException("Error to create session factory ",tools.ErrorCode.DATABASE,_className);	
				}
			} catch (CustomException ex) {
				throw new CustomException(ex);
			}
        return sessionFactory;
    }

    public static SessionFactory getSessionAnnotationFactory() {
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }

    public static SessionFactory getSessionJavaConfigFactory() {
        if(sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        return sessionJavaConfigFactory;
    }
}