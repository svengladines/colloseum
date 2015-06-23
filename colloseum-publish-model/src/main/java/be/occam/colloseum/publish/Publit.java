package be.occam.colloseum.publit;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Publit {
	
	public static enum Status {
		Draft,
		Published,
		Retracted
	}

	@Id
	protected String id;
	
	protected String title;
	protected String text;
	
	protected String url;
	
	protected Date touched;
	
	protected Status status;
	
	/*
	// protected String play;
	// protected String preview;
	// protected String data;
	
	// protected MediaType mediaType;
	
	
	
	protected String dataScriptUrl;
	protected String assembleScriptUrl;
	
	protected String track;
	
	protected long timestamp;
	
	protected String metaData;
	
	protected boolean canRender;
	*/
	
	

	public String getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public Publit setStatus(Status status) {
		this.status = status;
		return this;
	}

	public Publit setId(String id) {
		this.id = id;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Publit setUrl(String url) {
		this.url = url;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Publit setTitle( String title ) {
		this.title = title;
		return this;
	}

	public String getText() {
		return text;
	}

	public Publit setTest(String text) {
		this.text = text;
		return this;
	}

	public Date getTouched() {
		return touched;
	}

	public Publit setTouched(Date touched) {
		this.touched = touched;
		return this;
	}

	public Publit setText(String text) {
		this.text = text;
		return this;
	}

	/*
	public MediaType getMediaType() {
		return mediaType;
	}

	public Publit setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
		return this;
	}
	
	public String getDataAsString() {
		try {
			return new String( this.content, "utf-8" );
		}
		catch( UnsupportedEncodingException e ) {
			throw new RuntimeException( e );
		}
	}

	public boolean getCanRender() {
		return canRender;
	}

	public Publit setCanRender(boolean canRender) {
		this.canRender = canRender;
		return this;
	}

	public String getTitle() {
		return title;
	}

	/*
	public String getPlay() {
		return play;
	}

	public Publit setPlay(String play) {
		this.play = play;
		return this;
	}

	public String getPreview() {
		return preview;
	}

	public Publit setPreview(String preview) {
		this.preview = preview;
		return this;
	}

	public byte[] getContent() {
		return content;
	}

	public Publit setContent(byte[] content) {
		this.content = content;
		return this;
	}

	public String getDataScriptUrl() {
		return dataScriptUrl;
	}

	public Publit setDataScriptUrl(String dataScriptUrl) {
		this.dataScriptUrl = dataScriptUrl;
		return this;
	}

	public String getAssembleScriptUrl() {
		return assembleScriptUrl;
	}

	public Publit setAssembleScriptUrl(String assembleScriptUrl) {
		this.assembleScriptUrl = assembleScriptUrl;
		return this;
	}

	public String getTrack() {
		return track;
	}

	public Publit setTrack(String track) {
		this.track = track;
		return this;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Publit setTimestamp(long timestamp) {
		this.timestamp = timestamp;
		return this;
	}
	
	*/
	
	
}
