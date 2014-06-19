package be.occam.colloseum.model;

import javax.persistence.Entity;

@Entity
public class LivingBeing extends Thing {
	
	protected LivingBeing wakeUp() {
		return this;
	}
	
	protected LivingBeing sleep() {
		
		return this;
		
	}
	
}
