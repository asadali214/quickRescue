package gridSystem.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//corporate members info
@Entity
@Table(name = "Account")
public class Account {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Email_Domain")
	private String emailDomain;

	@Column(name = "TimeZoneCity")
	private String timeZoneCity;

	@OneToMany(mappedBy = "account")
	private Collection<Contact> contacts = new ArrayList<Contact>();
	
	@OneToMany(mappedBy = "account")
	private Collection<AlertProfile> alerts = new ArrayList<AlertProfile>();

	public Account() {

	}

	// Constructor to directly set all the properties:
	public Account(String name, String emailDomain, String timeZoneCity) {
		super();
		this.name = name;
		this.emailDomain = emailDomain;
		this.timeZoneCity = timeZoneCity;

	}

	// All the Getters&Setters are below:

	public int getId() {
		return id;
	}

	public Collection<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailDomain() {
		return emailDomain;
	}

	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}

	public String getTimeZoneCity() {
		return timeZoneCity;
	}

	public void setTimeZoneCity(String timeZoneCity) {
		this.timeZoneCity = timeZoneCity;
	}

}
