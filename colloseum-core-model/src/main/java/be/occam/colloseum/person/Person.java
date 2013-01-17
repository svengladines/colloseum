package be.occam.colloseum.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import be.occam.colloseum.model.LivingBeing;

@XmlRootElement
public class Person extends LivingBeing {
	
	protected String name;
	
	protected String firstName;
	
	protected String nickName;
	
	protected String url;
	
	protected String email;

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
	
	
	
}
