package gridSystem.quickResque;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import gridSystem.entities.Account;
import gridSystem.entities.Address;
import gridSystem.entities.Contact;


@SuppressWarnings("unchecked")
public class RescueManager implements QuickRescueFunctions {
	SessionFactory factory;

	/*
	 * This Constructor also used to initialize the SessionFactory's Object.
	 */
	public RescueManager() {
		try {
			factory = new Configuration().configure("hibernate.cfg.xml").addPackage("entities")
					.addAnnotatedClass(Account.class).buildSessionFactory();
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * This method is used to view all the accounts in the account table
	 * It returns an ArrayList of all the accounts to be displayed
	 * @see manager.QuickRescueFunctions
	 */
	public ArrayList<Account> viewAllAccounts() {
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<Account> accounts = new ArrayList<Account>();
		try {
			tx = session.beginTransaction();

			String hql = "FROM Account";

			accounts = (ArrayList<Account>) session.createQuery(hql).list();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return accounts;
	}

	/*
	 * It adds a new account into the account table
	 * the only Argument is the object of the account to be added
	 * return type is integer
	 * @see manager.QuickRescueFunctions
	 */
	public int addNewAccount(Account account) {
		Session session = factory.openSession();
		Transaction tx = null;
		int accountID = -1;

		try {
			tx = session.beginTransaction();
			accountID = (Integer) session.save(account);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return accountID;
	}

	/*
	 * it is used to update the account whose id is provided in the arguments.
	 * second argument is the updated account object it may or may not contain all the fields
	 * but the account object should contain all the fields which are being updated
	 * Its return type is the Account object after updating 
	 * @see manager.QuickRescueFunctions
	 */
	public Account updateAccount(int id, Account accountNew) {
		Session session = factory.openSession();
		Transaction tx = null;
		Account account = null;
		try {
			tx = session.beginTransaction();

			account = (Account) session.get(Account.class, id);
			if (accountNew.getName() != null)
				account.setName(accountNew.getName());
			if (accountNew.getEmailDomain() != null)
				account.setEmailDomain(accountNew.getEmailDomain());
			if (accountNew.getTimeZoneCity() != null)
				account.setTimeZoneCity(accountNew.getTimeZoneCity());
			session.update(account);

			tx.commit();
		} catch (Exception ex) {

		}
		return account;
	}

	/*
	 * This method is used to delete the account whose ID is provided in the arguments
	 * @see manager.QuickRescueFunctions
	 */
	public void deleteAccount(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Account account = null;
		try {
			tx = session.beginTransaction();

			account = (Account) session.get(Account.class, id);
			String hql = "FROM Contact WHERE account_id = " + id;
			ArrayList<Contact> contacts = (ArrayList<Contact>) session.createQuery(hql).list();
			for (Contact contact : contacts) {
				Address address = contact.getAddress();
				session.delete(contact);
				session.delete(address);
			}
			session.delete(account);

			tx.commit();
		} catch (Exception ex) {

		}
	}

	/*
	 * This method is used to view all the contacts of a specific account
	 * it takes the account id as an argument and give us all the corresponding contacts of that account
	 * its returns an Array list of contacts of that account
	 * @see manager.QuickRescueFunctions
	 */
	public ArrayList<Contact> viewAllContactsOfAccount(int AccountId) {
		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		try {
			tx = session.beginTransaction();

			String hql = "FROM Contact WHERE account_id = " + AccountId;

			contacts = (ArrayList<Contact>) session.createQuery(hql).list();
			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return contacts;
	}

	/*
	 * This method is used to add a contact into an account
	 * by taking account id and Contact's object as arguments
	 * It also returns the id of the newly added account 
	 * @see manager.QuickRescueFunctions
	 */
	public int addContactinAccount(Contact contact, int AccountId) {
		Session session = factory.openSession();
		Transaction tx = null;
		int contactID = -1;

		try {
			tx = session.beginTransaction();

			Account account = (Account) session.get(Account.class, AccountId);
			Address address = contact.getAddress();
			contact.setAccount(account);
			session.save(address);
			contactID = (Integer) session.save(contact);

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return contactID;
	}
	
	/*
	 * This method is used to a update a single contact
	 * It takes in arguments as ContactId and the new contact you want to update
	 * @see manager.QuickRescueFunctions
	 */
	public Contact updateContactOfAccount(int ContactId, Contact contactNew) {
		Session session = factory.openSession();
		Transaction tx = null;
		Contact contact = null;
		try {
			tx = session.beginTransaction();

			contact = (Contact) session.get(Contact.class, ContactId);
			if (contactNew.getFirstName() != null)
				contact.setFirstName(contactNew.getFirstName());
			if (contactNew.getLastName() != null)
				contact.setLastName(contactNew.getLastName());
			if (contactNew.getEmailAddress() != null)
				contact.setEmailAddress(contactNew.getEmailAddress());
			if (contactNew.getGender() != null)
				contact.setGender(contactNew.getGender());
			if (contactNew.getPhoneNumber() != null)
				contact.setPhoneNumber(contactNew.getPhoneNumber());
			if (contactNew.getStatus() != null)
				contact.setStatus(contactNew.getStatus());
			
			
			if (contactNew.getAddress().getStreetAddress() != null)
				contact.getAddress().setStreetAddress(contactNew.getAddress().getStreetAddress());
			if (contactNew.getAddress().getCity() != null)
				contact.getAddress().setCity(contactNew.getAddress().getCity());
			if (contactNew.getAddress().getState() != null)
				contact.getAddress().setState(contactNew.getAddress().getState());
			if (contactNew.getAddress().getCountry() != null)
				contact.getAddress().setCountry(contactNew.getAddress().getCountry());

			session.update(contact.getAddress());
			session.update(contact);
			

			tx.commit();
		} catch (Exception ex) {

		}

		return contact;
	}
	
	/*
	 * This method is used to delete a single contact at a time
	 * It takes in argument contact id to delete that contact
	 * @see manager.QuickRescueFunctions
	 */
	public void deleteContactOfAccount(int contactId) {
		Session session = factory.openSession();
		Transaction tx = null;
		Contact contact = null;
		try {
			tx = session.beginTransaction();

			contact = (Contact) session.get(Contact.class, contactId);
			Address address = contact.getAddress();

			session.delete(contact);
			session.delete(address);

			tx.commit();
		} catch (Exception ex) {

		}
	}
	
	/*
	 * This method is used to delete all the contacts belonging to a single account at a time.
	 * It takes in argument accountId whose contact you want to delete.
	 * @see manager.QuickRescueFunctions
	 */
	public void deleteAllContactsOfAccount(int AccountId) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			String hql = "FROM Contact WHERE account_id = " + AccountId;
			ArrayList<Contact> contacts = (ArrayList<Contact>) session.createQuery(hql).list();
			for (Contact contact : contacts) {
				Address address = contact.getAddress();
				session.delete(contact);
				session.delete(address);
			}

			tx.commit();
		} catch (Exception ex) {

		}
	}
	
	/*
	 * This method is used to get a single account from its id
	 * It takes in argument id and return the corresponding account
	 * @see manager.QuickRescueFunctions
	 */
	public Account getAccount(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Account account =null;
		try {
			tx = session.beginTransaction();

			account = (Account) session.get(Account.class, id);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	}
	
	/*
	 * This method is used to get a single contact from its id
	 * It takes in argument id of account and return its corresponding contact
	 * @see manager.QuickRescueFunctions
	 */
	public Contact getContact(int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		Contact contact =null;
		try {
			tx = session.beginTransaction();

			contact = (Contact) session.get(Contact.class, id);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return contact;
	}

}
