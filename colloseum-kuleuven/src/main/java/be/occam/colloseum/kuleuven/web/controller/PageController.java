package be.occam.colloseum.kuleuven.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/{page}.html")
public class PageController {
	
	private final static Logger logger = LoggerFactory.getLogger( PageController.class );
	
	@RequestMapping(method=RequestMethod.GET) 
	public ModelAndView view( @PathVariable String page )  {
		
		ModelAndView mav
			= new ModelAndView();
		
		mav.setViewName( page );
		
		return mav;
		
	}
	
}
