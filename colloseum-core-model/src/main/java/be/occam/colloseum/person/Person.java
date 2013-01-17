package be.occam.colloseum.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person extends LivingBeing {
	
	protected String name;
	
	protected String firstName;
	
	protected String nickName;
	
	protected String url;

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
	
}
