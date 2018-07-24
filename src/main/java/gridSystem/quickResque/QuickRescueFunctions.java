package gridSystem.quickResque;

import java.util.ArrayList;

import gridSystem.entities.Account;
import gridSystem.entities.Contact;

public interface QuickRescueFunctions {
	public ArrayList<Account> viewAllAccounts();

	public int addNewAccount(Account account);

	public Account updateAccount(int id, Account accountNew);

	public void deleteAccount(int id);

	public ArrayList<Contact> viewAllContactsOfAccount(int AccountId);

	public int addContactinAccount(Contact contact, int AccountID);

	public Contact updateContactOfAccount(int ContactId, Contact contactNew);

	public void deleteContactOfAccount(int contactId);

	public void deleteAllContactsOfAccount(int AccountId);

	public Account getAccount(int id);

	public Contact getContact(int id);
}
