package be.occam.colloseum.publisher.core;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Publit {

	protected String id;
	protected String title;
	protected String description;
	protected String play;
	protected String preview;
	protected String data;
	
	protected MediaType mediaType;
	
	protected String url;
	
	protected String previewUrl;
	protected String dataUrl;
	
	protected String dataScriptUrl;
	protected String assembleScriptUrl;
	
	@XmlTransient
	protected byte[] content;
	
	
	
	protected boolean canRender;

	public String getId() {
		return id;
	}

	public Publit setId(String id) {
		this.id = id;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public String getData() {
		return data;
	}

	public Publit setUrl(String url) {
		this.url = url;
		return this;
	}
	
	public Publit setTitle( String title ) {
		this.title = title;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Publit setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getPreviewUrl() {
		return previewUrl;
	}

	public Publit setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
		return this;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	public Publit setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
		return this;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public Publit setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
		return this;
	}
	
	public Publit setData( String data ) {
		
		this.data = data;
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
	
}
