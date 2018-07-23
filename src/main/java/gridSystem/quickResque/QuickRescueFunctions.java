package gridSystem.quickResque;

import java.util.ArrayList;

import gridSystem.entities.Account;
import gridSystem.entities.Contact;


public interface QuickRescueFunctions {
	/* To view all accounts */
	public ArrayList<Account> viewAllAccounts();

	/* To add a new account */
	public int addNewAccount(Account account);

	/* To update an account */
	public Account updateAccount(int id, Account accountNew);

	/* To delete an account */
	public void deleteAccount(int id);

	/* To view all the employees of an account */
	public ArrayList<Contact> viewAllContactsOfAccount(int AccountId);

	/* To add a new employee in an account */
	public int addContactinAccount(Contact contact,int AccountID);

	/* To update an existing employee of an account */
	public Contact updateContactOfAccount(int ContactId, Contact contactNew);

	/* To delete an employee of an account */
	public void deleteContactOfAccount(int contactId);

	/* To delete all employees of an account */
	public void deleteAllContactsOfAccount(int AccountId);
	
	/*used to get an account with id*/
	public Account getAccount(int id);
	
	/*used to get a contact with id*/
	public Contact getContact(int id);

}
