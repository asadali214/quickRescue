package gridSystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AlertLocation")
public class AlertLocation {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;
	
	@ManyToOne
	private AlertProfile alert;
	
	

	public AlertLocation() {
		
	}

	public AlertLocation(int id, String city, String country, AlertProfile alert) {
		super();
		this.id = id;
		this.city = city;
		this.country = country;
		this.alert = alert;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public AlertProfile getAlert() {
		return alert;
	}

	public void setAlert(AlertProfile alert) {
		this.alert = alert;
	}
	
	


}
