package be.occam.colloseum.person;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.action.ActionDTO;
import be.occam.colloseum.action.repository.IActionRepository;
import be.occam.colloseum.model.LivingBeing;
import be.occam.colloseum.person.repository.IPersonRepository;

@XmlRootElement
public class Person extends LivingBeing {
	
	protected String name;
	
	protected String firstName;
	
	protected String nickName;
	
	protected String url;
	
	protected String email;
	
	protected List<ActionDTO> todo
		= new LinkedList<ActionDTO>();
	
	protected List<Action<?>> done
		= new LinkedList<Action<?>>();
	
	@Resource
	IPersonRepository personRepository;
	
	@XmlAttribute
	public String getName() {
		return name;
	}

	public Person setName(String name) {
		this.name = name;
		return this;
	}

	@XmlAttribute
	public String getFirstName() {
		return firstName;
	}

	public Person setFirstName(String firstName) {
		this.firstName = firstName;
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

	@Override
	public Person setId(String id) {
		super.setId( id );
		return this;
	}
	
	public Person accept( ActionDTO action ) {
		
		this.todo.add( action );
		return this;
		
	}
	
}
