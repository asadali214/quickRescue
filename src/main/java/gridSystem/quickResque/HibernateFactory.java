package gridSystem.quickResque;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import gridSystem.entities.Account;

public class HibernateFactory {
	private static SessionFactory factory=null;
	public static SessionFactory getfactory() {
		if (factory==null) {
			try {
				factory = new Configuration().configure("hibernate.cfg.xml").addPackage("entities")
						.addAnnotatedClass(Account.class).buildSessionFactory();
			} catch (Throwable ex) {
				ex.printStackTrace();
			}
		}
		return factory;
			
	}
}
