package br.com.mario.cadastro.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties())
            .build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			//http://www.guj.com.br/4797-hibernatecfgxml--hibernate-42--sql-server-2008
			
			/*SchemaExport se = new SchemaExport(configuration);  
	        se.create(true, true); */ 

			// sessionFactory = new
			// AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Cria��o do SessionFactory falhou (HibernateUtil)." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
