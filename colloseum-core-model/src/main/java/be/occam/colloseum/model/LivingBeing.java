package be.occam.colloseum.model;

import javax.annotation.Resource;

import be.occam.colloseum.action.service.IActionService;


public class LivingBeing extends Thing {
	
	@Resource
	IActionService actionService;

	protected LivingBeing wakeUp() {
		return this;
	}
	
	protected LivingBeing sleep() {
		
		return this;
		
	}
	
}
