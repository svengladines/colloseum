package be.occam.colloseum.credential;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Credential {
	
	public enum Status {
		Created,
		Confirmed,
		Passed,
		Rejected,
		Check
	}
	
	@Id
	protected String id;
	protected String reference;
	protected String passWord;
	protected String userId;
	protected String protectedUrl;
	protected Status status;
	
	public String getReference() {
		return reference;
	}
	
	public Credential setReference(String reference) {
		this.reference = reference;
		return this;
	}
	
	public String getPassWord() {
		return passWord;
	}
	
	public Credential setPassWord(String password) {
		this.passWord = password;
		return this;
	}

	public String getProtectedUrl() {
		return protectedUrl;
	}

	public Credential setProtectedUrl(String protectedUrl) {
		this.protectedUrl = protectedUrl;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public Credential setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
