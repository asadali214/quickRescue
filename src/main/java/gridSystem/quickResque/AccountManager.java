package gridSystem.quickResque;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import gridSystem.entities.Account;

public class AccountManager implements BeanCrudManager{

	
	public ArrayList<Object> viewAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int addNew(Object account) {
		Session session = HibernateFactory.getfactory().openSession();
		Transaction tx = null;
		int accountID = -1;

		try {
			tx = session.beginTransaction();
			accountID = (Integer) session.save((Account)account);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return accountID;
	}

	public Object update(int id, Object accountNew) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public Object get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
