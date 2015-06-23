package be.occam.colloseum.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Tag {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
	
	protected String id;
	protected String value;
	
	private Tag( ) {
		
	}
	
	public Tag( String id, String value ) {
		assert( value != null );
		this.id = id;
		this.value = value;
	}
	
	public Tag( String value ) {
		this( UUID.randomUUID().toString(), value );
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder( this.id ).append(":").append( this.value.toString() ).toString();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
