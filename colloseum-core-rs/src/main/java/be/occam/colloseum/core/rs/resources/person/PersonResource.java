package be.occam.colloseum.core.rs.resources.person;

import javax.annotation.Resource;
import javax.ws.rs.Path;

import be.occam.colloseum.core.person.IDorm;

@Path("persons/{id}")
public class PersonResource {
	
	@Resource
	protected IDorm dorm;

}
