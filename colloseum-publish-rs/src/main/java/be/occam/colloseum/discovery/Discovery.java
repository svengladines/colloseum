package be.occam.colloseum.discovery;

import javax.xml.bind.annotation.XmlRootElement;

import be.occam.colloseum.publisher.PublisherDTO;
import be.occam.colloseum.publit.Publit;

@XmlRootElement
public class Discovery {
	
	protected Publit publit;
	protected PublisherDTO publisher;

}
