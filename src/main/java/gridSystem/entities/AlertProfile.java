package gridSystem.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AlertProfile")
public class AlertProfile {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@ManyToOne
	private Account account;

	@OneToMany(mappedBy = "alert")
	private Collection<AlertLocation> locations = new ArrayList<AlertLocation>();

	public AlertProfile() {

	}

	public AlertProfile(int id, Account account) {
		super();
		this.id = id;
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Collection<AlertLocation> getLocations() {
		return locations;
	}

	public void setLocations(Collection<AlertLocation> locations) {
		this.locations = locations;
	}

	
}
