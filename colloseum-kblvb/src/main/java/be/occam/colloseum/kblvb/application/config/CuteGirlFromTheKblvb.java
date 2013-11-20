package be.occam.colloseum.kblvb.application.config;

import static be.occam.test.jtest.JTestUtil.getBytes;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import be.occam.colloseum.soccer.league.hats.CuteGirlFromTheLeague;
import be.occam.colloseum.soccer.ranking.Ranking;

public class CuteGirlFromTheKblvb extends CuteGirlFromTheLeague {
	
	protected final String url
		= "http://www.kblvb.be/Competitie/Kampioenschappen/Rangschikking.asp?SubNavID=competitie&Afdeling=1&Doelgroep=H&Reeks=3A&Seizoen=2013-2014&Keuze=RAN&bExcel=1";

	@Override
	public List<Ranking> whatAreTheRankings() {
		
		try {
		
			ResponseEntity<byte[]> excelResponse
				= getBytes( this.url, null);
			
			if ( ! HttpStatus.OK.equals( excelResponse.getStatusCode() ) ) {
				return null;
			} 
			
			byte[] body
				= excelResponse.getBody();
			
			String html
				= new String( body );
			
			Source source
				=new Source( html );
			
			List<Element> tables
				= source.getAllElements( "table" );
			
			if ( tables.size() != 1 ) {
				return null;
			}
			
			Element table
				= tables.get( 0 );
			
			List<Element> rows 
				= table.getAllElements( "tr" );
			
			List<Ranking> rankings
				= new ArrayList<Ranking>( rows.size() -1 );
			
			for ( int i=1; i < rows.size(); i++ ) {
				
				Element row
					= rows.get( i );
				
				Ranking ranking
					= new Ranking();
				
				// Club	Gespeeld	Gewonnen	Verloren	Goals voor	Goals tegen	Punten
				
				List<Element> columns
					= row.getAllElements( "td" );
				
				ranking.setTeam( this.whatsThatTeamThatsCalled( columns.get(1).getTextExtractor().toString() ) );
				ranking.setWon(  Integer.valueOf( columns.get(3).getTextExtractor().toString() ) );
				ranking.setLost( Integer.valueOf( columns.get(4).getTextExtractor().toString() ) );
				ranking.setTied( Integer.valueOf( columns.get(2).getTextExtractor().toString() ) - ranking.getWon() - ranking.getLost() );
				ranking.setScored( Integer.valueOf( columns.get(5).getTextExtractor().toString() ) );
				ranking.setAgainst( Integer.valueOf( columns.get(6).getTextExtractor().toString() ) );
				
				rankings.add( ranking );
				
			}
			
			return rankings;
			
			
		}
		catch ( Exception e ) {
			logger.warn( "could not process KBLVB rankings", e );
			throw new RuntimeException( e );
		}
		
	}
	
	

}
