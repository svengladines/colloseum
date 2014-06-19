package be.occam.colloseum.soccer.registration;

public class RSVP {
	
	public static enum Answer {
		Yep, Later, Maybe, Nope
	}
	
	protected Answer answer;
	protected String comment;
	
	public RSVP( ) {
	}
	
	public RSVP( Answer answer ) {
		this.answer = answer;
	}
	
	public RSVP( String answer ) {
		this.answer = Answer.valueOf( new StringBuilder().append( answer.substring(0,1).toUpperCase()) .append( answer.substring(1) ).toString() );
	}
	
	public Answer getAnswer() {
		return answer;
	}
	public RSVP setAnswer(Answer answer) {
		this.answer = answer;
		return this;
	}
	public String getComment() {
		return comment;
	}
	public RSVP setComment(String comment) {
		this.comment = comment;
		return this;
	}
	
}
