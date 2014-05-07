package be.occam.colloseum.person;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Embedded;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.action.ActionDTO;
import be.occam.colloseum.model.LivingBeing;
import be.occam.colloseum.model.Tag;
import be.occam.colloseum.person.repository.IPersonRepository;

@XmlRootElement
public class Person extends LivingBeing {
	
	protected String familyName;
	
	protected String givenName;
	
	protected String nickName;
	
	protected String url;
	
	protected String email;
	
	protected List<ActionDTO> todo
		= new LinkedList<ActionDTO>();
	
	protected List<Action<?>> done
		= new LinkedList<Action<?>>();
	
	@Resource
	IPersonRepository personRepository;
	
	public Person() {
	}
	
	public Person( Person person ) {
		this.setId( person.getId() );
		this.setGivenName( person.getGivenName() );
		this.setFamilyName( person.getFamilyName() );
		this.setEmail( person.getEmail() );
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
	public String getNickName() {
		return nickName;
	}

	public Person setNickName(String nickName) {
		this.nickName = nickName;
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
	
	public Person accept( ActionDTO action ) {
		
		this.todo.add( action );
		return this;
		
	}
	
}
