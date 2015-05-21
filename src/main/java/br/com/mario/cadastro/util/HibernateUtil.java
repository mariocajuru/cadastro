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

		} catch (Throwable ex) {
			System.err.println("Criação do SessionFactory falhou (HibernateUtil)." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
