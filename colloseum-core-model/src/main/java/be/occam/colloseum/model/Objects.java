package be.occam.colloseum.model;

import java.util.HashMap;
import java.util.Map;

import be.occam.colloseum.person.Person;

public class Objects {
	
	private final Map<String,String> objects
		= new HashMap<String, String>();
	
	public Objects() {
		
		this.objects.put( "homer.simpson" , Person.class.getCanonicalName() );
		this.objects.put( "god" , Person.class.getCanonicalName() );
		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T object( String id ) {
			
			try { 
				
				String t
					= objects.get( id );
				
				if ( t == null ) {
					return null;
				}
			
				Class<T> clz 
					= (Class<T>) Class.forName( t );
			
				T object
					= clz.newInstance();
				
				return object;
				
			}
			catch( Exception e ) {
				throw new RuntimeException( e );
			}
			
		}
		
}
