package be.occam.colloseum.soccer.ranking.resources;

import static be.occam.colloseum.util.Resource.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import be.occam.colloseum.soccer.club.hats.Ranker;
import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.match.actions.Plan;
import be.occam.colloseum.soccer.ranking.Ranking;
import be.occam.colloseum.util.Timing;

@Controller
@RequestMapping( {"/rankings"} )
public class RankingsResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Ranking[] array = {};
	
	@Resource
	Ranker ranker;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<Ranking[]> query() {
		
		List<Ranking> rankings
			= this.ranker.whatAreTheCurrentRankings();
		
		return response( rankings.toArray( this.array ), HttpStatus.OK );
		
	}
	
}
