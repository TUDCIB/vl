package de.tudresden.bau.cib.vl.core.database.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author Ken Baumgaertel 
 * {@link "mailto:Ken.Baumgaertel@tu-dresden.de"}
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator")
public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2566301838485773086L;
	private int id;
	private String sessionId;
	private Date expireDate;
	private Locale locale;
	private Integer userId;
	
	public Session() {

	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	

	public String getSessionId() {
		return sessionId;
	}
	
	public Date getExpireDate() {
		return expireDate;
	}
	
	public Locale getLocale() {
		if(locale != null)
			return locale;
		else
			return Locale.getDefault();
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Integer getUserId() {
		return userId;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		Session s = (Session) obj;
		return s.expireDate.equals(this.getExpireDate()) && s.hashCode() == this.hashCode() && s.sessionId.equals(this.sessionId);
	}
	
	@Override
	public String toString() {
		return "Session-ID: "+sessionId+" ,expireDate: "+this.expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
