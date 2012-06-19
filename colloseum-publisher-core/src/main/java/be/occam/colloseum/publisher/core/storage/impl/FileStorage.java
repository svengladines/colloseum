package be.occam.colloseum.publisher.core.storage.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import be.occam.colloseum.publisher.core.Publit;
import be.occam.colloseum.publisher.core.storage.IPublitStorage;

@Component
public class FileStorage implements IPublitStorage {
	
	@Value("directory")
	String directory = "/tmp/";

	@Override
	public IPublitStorage persist( Publit publit ) {
	 
		try {
		
			// write description
			String id
				= publit.getId();
			
			String description
				= publit.getDescription();
			
			File descript
				= this.description( id );
		
			FileOutputStream dfos
				= new FileOutputStream( descript );
		
			OutputStreamWriter wos
				= new OutputStreamWriter( dfos );
		
			wos.write( description != null ? description : "" );
			wos.flush();
			wos.close();
			
			if ( publit.getPreview() != null ) {
				// write preview
				File preview
					= this.preview( id );
				
				FileOutputStream fos
					= new FileOutputStream( preview );
				
				fos.write( publit.getPreview().getBytes( "utf-8" ) );
			
				fos.close();
			}
			
			if ( publit.getData() != null ) {
		
				// write data
				File data
					= this.data( id );
			
				FileOutputStream afos
					= new FileOutputStream( data );
			
				afos.write( publit.getData().getBytes( "utf-8" ) );
		
				afos.close();
				
			}
			
			return this;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e );
		}
		
	}
	
	protected File data( String id ) {
		
		StringBuilder b
			= new StringBuilder( this.directory );
		
		if ( id != null ) {
			
			b.append( id ).append( ".data" );
			
		}
		
		return new File( b.toString() );
		
	}
	
	protected File preview( String id ) {
		
		StringBuilder b
			= new StringBuilder( this.directory  );
		
		if ( id != null ) {
			
			b.append( id ).append( ".jpg" );
			
		}
		
		return new File( b.toString() );
		
	}
	
	protected File description( String id ) {
		
		StringBuilder b
			= new StringBuilder( this.directory  );
		
		if ( id != null ) {
			
			b.append( "/" ).append( id ).append( ".description" );
			
		}
		
		return new File( b.toString() );
		
	}

	@Override
	public Publit load( String id ) {
		
		try {
			
			Publit shareable
				= new Publit().setId( id );
			
			if ( true ) {
				
				File f
					= this.description( id );
			
				FileInputStream fis
					= new FileInputStream( f );
				
				ByteArrayOutputStream bos
					= new ByteArrayOutputStream( 10240 );
				
				byte[] buffer
					= new byte[ 10240 ];
				
				int read = 0;
				
				while ( read != -1 ) {
					
					bos.write( buffer, 0, read );
					read = fis.read( buffer );
					
				}
			
				shareable = shareable.setDescription( new String( bos.toByteArray( ), "utf-8" ) );
			
			}
			
			if ( true ) {
				
				File f
					= this.data( id );
				
				FileInputStream fis
					= new FileInputStream( f );
				
				ByteArrayOutputStream bos
					= new ByteArrayOutputStream( 102400 );
				
				byte[] buffer
					= new byte[ 10240 ];
				
				int read = 0;
				
				while ( read != -1 ) {
					
					bos.write( buffer, 0, read );
					read = fis.read( buffer );
					
				}
				
				shareable = shareable.setContent( bos.toByteArray() );
			}
			
			if ( true ) {
				
				File f
					= this.preview( id );
				
				FileInputStream fis
					= new FileInputStream( f );
				
				ByteArrayOutputStream bos
					= new ByteArrayOutputStream( 102400 );
				
				byte[] buffer
					= new byte[ 10240 ];
				
				int read = 0;
				
				while ( read != -1 ) {
					
					bos.write( buffer, 0, read );
					read = fis.read( buffer );
					
				}
				
				shareable = shareable.setPreview( new String( bos.toByteArray(), "utf-8" ) );
			}
			
			return shareable;
			
		} 
		catch( Exception e ){
			throw new RuntimeException( e );
		}
		
	}

	@Override
	public IPublitStorage delete(Publit publit) {
		return this;
	}


}
