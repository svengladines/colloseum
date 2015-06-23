package be.occam.colloseum.kuleuven.domain.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

// @Entity
// @Table(name="TOL_KISS_OAUTH_DATA")
// @SequenceGenerator(name="pkGenerator",sequenceName="SEQ_TOL_KISS")
public class OAuthData {

	@Id
	@GeneratedValue( strategy=GenerationType.SEQUENCE, generator="pkGenerator" )
	protected long id;
	
	protected String userID;
	protected String scope;
	protected String authorizationToken;
	protected String accessToken;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAuthorizationToken() {
		return authorizationToken;
	}
	public void setAuthorizationToken(String authorizationToken) {
		this.authorizationToken = authorizationToken;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

}
