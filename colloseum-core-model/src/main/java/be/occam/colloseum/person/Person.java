package be.occam.colloseum.person;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import be.occam.colloseum.model.Tag;

import com.google.appengine.api.datastore.Key;

@Entity
@XmlRootElement
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@DiscriminatorValue(value="PN")
public class Person {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;
	
	protected String id;
	
	protected String familyName;
	
	protected String givenName;
	
	protected String url;
	
	protected String email;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER )
	protected Set<Tag> tags
		= new HashSet<Tag>();
	
	/*
	protected List<ActionDTO> todo
		= new LinkedList<ActionDTO>();
	
	protected List<Action<?>> done
		= new LinkedList<Action<?>>();
	*/
	
	public Person() {
	}
	
	public Person( Person person ) {
		this.setId( person.getId() );
		this.setGivenName( person.getGivenName() );
		this.setFamilyName( person.getFamilyName() );
		this.setEmail( person.getEmail() );
		this.getTags().addAll( person.getTags() );
	}
	
	@XmlAttribute
	public String getFamilyName() {
		return this.familyName;
	}

	public Person setFamilyName(String name) {
		this.familyName = name;
		return this;
	}

	@XmlAttribute
	public String getGivenName() {
		return givenName;
	}

	public Person setGivenName(String givenName) {
		this.givenName = givenName;
		return this;
	}

	@XmlAttribute
	public String getUrl() {
		return url;
	}

	public Person setUrl(String url) {
		this.url = url;
		return this;
	}

	@XmlAttribute
	public String getEmail() {
		return email;
	}

	public Person setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public Person persist() {
		
		// this.personRepository.persist( this , null );
		return this;
		
	}
	
	/*
	public Person accept( ActionDTO action ) {
		
		this.todo.add( action );
		return this;
		
	}
	*/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public Key getKey() {
		return key;
	}

	public Person setKey(Key key) {
		this.key = key;
		return this;
	}

}
