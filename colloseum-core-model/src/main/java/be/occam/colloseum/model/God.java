package be.occam.colloseum.model;

import be.occam.colloseum.person.Person;

public class God extends Person {

	@Override
	public String getFamilyName() {
		return "Almighty";
	}

	@Override
	public String getGivenName() {
		return "The";
	}

}
