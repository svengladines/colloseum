package be.occam.colloseum.publisher;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PublisherDTO {
	
	protected String id;
	protected String url;
	public String getId() {
		return id;
	}
	public PublisherDTO setId(String id) {
		this.id = id;
		return this;
	}
	public String getUrl() {
		return url;
	}
	public PublisherDTO setUrl(String url) {
		this.url = url;
		return this;
	}
	
	

}
