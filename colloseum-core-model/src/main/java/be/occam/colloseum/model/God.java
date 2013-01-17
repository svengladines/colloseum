package be.occam.colloseum.model;

import javax.xml.bind.annotation.XmlRootElement;

import be.occam.colloseum.person.Person;

public class God extends Person {

	@Override
	public String getName() {
		return "Almighty";
	}

	@Override
	public String getFirstName() {
		return "The";
	}

	@Override
	public String getNickName() {
		return "God himself";
	}

}
