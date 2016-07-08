package de.tudresden.bau.cib.vl.core.database.domain;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2581240220835991369L;
	Integer id = 0;
	String username;
    String firstName;
    String lastName;
    String password;
    String role;
    String email;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
    	return id;
    }
    
    public void setId(Integer id) {
		this.id = id;
	}
    
	@Override
	public int hashCode() {
		return id.hashCode() + username.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		User u = (User) obj;
		return u.username.equals(this.username) && u.password.equals(this.password) && u.hashCode() == this.hashCode();
	}
	
	@Override
	public String toString() {
		return this.id + " "+this.username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
