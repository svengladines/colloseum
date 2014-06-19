package be.occam.colloseum.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Timing {
	
	public static String datePattern 
		= "dd/MM/yyyy";
	
	public static String flatDatePattern 
		= "ddMMyyyy";
	
	public static String timePattern
		= "HH:mm";
	
	public static final SimpleDateFormat momentFormat
		= new SimpleDateFormat( new StringBuilder( datePattern ).append( " " ).append( timePattern ).toString() );
	
	public static final SimpleDateFormat dateFormat
		= new SimpleDateFormat( new StringBuilder( datePattern ).toString() );
	
	public static final SimpleDateFormat flatDateFormat
		= new SimpleDateFormat( new StringBuilder( flatDatePattern ).toString() );
	
	public static final SimpleDateFormat timeFormat
		= new SimpleDateFormat( new StringBuilder( timePattern ).toString() );
	
	public static Date date( String date ) {
		
		try {
			return dateFormat.parse( date );
		}
		catch( ParseException e ) {
			throw new RuntimeException( e );
		}
		
	}
	
	public static Date date( String date, SimpleDateFormat format ) {
		
		try {
			return format.parse( date );
		}
		catch( ParseException e ) {
			throw new RuntimeException( e );
		}
		
	}
	
	public static Date date( String date, String format ) {
		
		try {
			return  new SimpleDateFormat().parse( date ) ;
		}
		catch( ParseException e ) {
			throw new RuntimeException( e );
		}
		
	}
	
	public static Date moment( String date ) {
		
		try {
			return momentFormat.parse( date );
		}
		catch( ParseException e ) {
			throw new RuntimeException( e );
		}
		
	}
	
	public static Date moment( String date, String time ) {
		
		return moment( new StringBuilder( date ).append( " " ).append( time ).toString() );

	}
	
	public static String date( Date date ) {
		
		return dateFormat.format( date );
		
	}
	
	public static String time( Date date ) {
		
		return timeFormat.format( date );
		
	}
	
	public static String date( Date date, String format ) {
		
		return new SimpleDateFormat( format ).format( date );
		
	}
	
	public static String date( Date date, SimpleDateFormat format ) {
		
		return format.format( date );
		
	}
	
	public static String moment( Date date ) {
		
		return momentFormat.format( date );
		
	}

}
