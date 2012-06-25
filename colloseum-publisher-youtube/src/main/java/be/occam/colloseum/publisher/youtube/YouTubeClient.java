package be.occam.colloseum.publisher.youtube;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.google.api.client.googleapis.auth.clientlogin.ClientLogin.Response;
import com.google.api.client.googleapis.json.JsonCParser;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpParser;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpRequest;
import com.google.api.client.http.json.JsonHttpRequestInitializer;
import com.google.api.client.http.xml.XmlHttpParser;
import com.google.api.client.http.xml.atom.AtomParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.Key;
import com.google.api.client.xml.XmlNamespaceDictionary;

public class YouTubeClient {
	
        private static final Logger logger 
        	= LoggerFactory.getLogger(YouTubeClient.class);
        
        private static final String YOUTUBE_GDATA_BASE_URL 
        	= "http://gdata.youtube.com";
        
        private static final String YOUTUBE_UPLOADS_GDATA_BASE_URL 
        	= "http://uploads.gdata.youtube.com";
        
        private static final String YOUTUBE_FEEDS_API_URL 
        	= YOUTUBE_GDATA_BASE_URL + "/feeds/api/";
        
        private static final String YOUTUBE_FEEDS_API_UPLOAD_URL 
        	= YOUTUBE_UPLOADS_GDATA_BASE_URL + "/feeds/api/users/default/uploads";
        
        /** From http://code.google.com/intl/en/apis/youtube/2.0/reference.html#API_Request_XML_Element_Definitions */
        private static final XmlNamespaceDictionary YOUTUBE_NAMESPACE_DICT = new XmlNamespaceDictionary()
                .set("", "http://www.w3.org/2005/Atom") //Atom Syndication Format
                .set("openSearch", "http://a9.com/-/spec/opensearch/1.1/") // Open Search Schema
                .set("media", "http://search.yahoo.com/mrss/") // Media RSS
                .set("yt", "http://gdata.youtube.com/schemas/2007") // YouTube XML Schema
                .set("gd", "http://schemas.google.com/g/2005") // Google Data Schema
                .set("georss", "http://www.georss.org/georss") // GeoRSS
                .set("gml", "http://www.opengis.net/gml") // Geography Markup Language
                .set("app", "http://www.w3.org/2007/app") // Atom Publishing Protocol
                .set("batch", "http://schemas.google.com/gdata/batch"); // Google Data API Batch Processing
        private static final XmlNamespaceDictionary ERROR_NAMESPACE_DICT = new XmlNamespaceDictionary()
                .set("", ""); // Just for mapping from "" namespace URI something != null (to prevent a NPE)
        
        /** Stores the parsed error response
         * @author fhackenberger
         */
        protected class YoutubeUnsuccessfulResponseHandler implements
                        HttpUnsuccessfulResponseHandler {
                YoutubeErrors errors = null;

                @Override
                public boolean handleResponse(HttpRequest request, HttpResponse response, boolean retrySupported) throws IOException {
                        errors = response.parseAs(YoutubeErrors.class);
                        return false;
                }
        }
        
        /** Represents an Atom formatted upload request for YouTube
         * @see http://code.google.com/intl/en/apis/youtube/2.0/developers_guide_protocol_direct_uploading.html#Sending_a_Direct_Upload_API_Request
         * 
         * <entry xmlns="http://www.w3.org/2005/Atom"
         * xmlns:media="http://search.yahoo.com/mrss/"
         * xmlns:yt="http://gdata.youtube.com/schemas/2007">
         * <media:group>
         *      <media:title type="plain">Bad Wedding Toast</media:title>
         *      <media:description type="plain">
         *              I gave a bad toast at my friend's wedding.
         *      </media:description>
         *      <media:category
         *              scheme="http://gdata.youtube.com/schemas/2007/categories.cat">People
         *      </media:category>
         *      <media:keywords>toast, wedding</media:keywords>
         * </media:group>
         * </entry>
         * @author fhackenberger
         */
        public static class UploadEntry {
                @Key("media:group")
                MediaGroup group = new MediaGroup();
        }
        /**
         * @see UploadEntry
         * @author fhackenberger
         */
        public static class MediaGroup {
                @Key("media:title")
                MediaAttribute title = new MediaAttribute();
                @Key("media:description")
                MediaAttribute description = new MediaAttribute();
                @Key("media:category")
                MediaCategory category = new MediaCategory();
                @Key("media:keywords")
                String keywords;
                @Key("media:thumbnail")
                List<MediaThumbNail> thumbnail;
        }
        /**
         * @see UploadEntry
         * @author fhackenberger
         */
        public static class MediaAttribute {
                @Key("@type")
                String type = "plain";
                @Key("text()")
                String value;
        }
        /**
         * @see UploadEntry
         * @author fhackenberger
         */
        public static class MediaCategory {
                @Key("@scheme")
                String scheme = "http://gdata.youtube.com/schemas/2007/categories.cat";
                @Key("text()")
                String category;
        }
        
        public static class MediaThumbNail {
        	@Key("@url")
            String url;
            @Override
            public String toString() {
                return url;
            }
        }
        
        /** Represents an error response from Youtube
         * @see http://code.google.com/intl/en/apis/youtube/2.0/developers_guide_protocol_error_responses.html
         * <errors>
         *      <error>
         *              <domain>yt:validation</domain>
         *              <code>invalid_value</code>
         *              <location type='xpath'>media:group/media:category[@scheme='http://gdata.youtube.com/schemas/2007/categories.cat']/text()
         *              </location>
         *      </error>
         * </errors>
         * @author fhackenberger
         *
         */
        public static class YoutubeErrors {
                @Key("error")
                List<YoutubeError> errors;
        }
        /**
         * @see YoutubeErrors
         * @author fhackenberger
         */     
        public static class YoutubeError {
                @Key
                String domain;
                @Key
                String code;
                @Key
                YoutubeErrorLocation location;
                @Override
                public String toString() {
                        return "domain: " + domain + "; code: " + code + "; location: (" + location + ")";
                }
        }
        /**
         * @see YoutubeError
         * @author fhackenberger
         */
        public static class YoutubeErrorLocation {
                @Key("@type")
                String type;
                @Key("text()")
                String location;
                @Override
                public String toString() {
                        return "type: " + type + "; location: " + location;
                }
        }
        
        /** Represents a YouTube video feed
         * @see http://code.google.com/intl/en/apis/youtube/2.0/developers_guide_protocol_understanding_video_feeds.html
         * @author fhackenberger
         */
        public static class VideoFeed {
                @Key
                List<Video> items;

                @Override
                public String toString() {
                        return "Items: " + items;
                }
        }
        /** A single video entry
         * @see VideoFeed
         * @author fhackenberger
         */
        public static class Video {
                @Key
                String id;
                @Key
                String title;
                @Key
                String description;
                @Key
                Player player;
                @Key("link")
                List<Link> links;
                @Key("media:group")
                MediaGroup group;
                @Override
                public String toString() {
                        return "Id: " + id + " Title: " + title + " Description: " + description + " Player: " + player + " Links: " + links;
                }
        }
        /** A related link for a {@link Video}
         * @see VideoFeed
         * @author fhackenberger
         */
        public static class Link {
                @Key("@rel")
                String rel;
                @Key("@href")
                String href;
                @Key("@type")
                String type;
                @Override
                public String toString() {
                        return href;
                }
        }
        /** The URL for the YouTube video player for a {@link Video}
         * @see VideoFeed
         * @author fhackenberger
         */     
        public static class Player {
                @Key("default")
                String defaultUrl;
                @Override
                public String toString() {
                        return "DefaultURL: " + defaultUrl;
                }
        }
        
        public static class YouTubeUploadUrl extends GenericUrl {
                YouTubeUploadUrl() {
                        super(YOUTUBE_FEEDS_API_UPLOAD_URL);
                }
        }
        public static class FeedsYouTubeUrl extends GenericUrl {
                @Key
                final String alt = "jsonc";
                @Key
                String author;
                @Key("max-results")
                Integer maxResults;

                FeedsYouTubeUrl(String endpoint) {
                        super(YOUTUBE_FEEDS_API_URL + endpoint);
                }
        }
        
        @Value("${google.apiKey}") String apiKey;
        @Value("${google.appName}") String appName;
        @Value("${youtube.userName}") String youtubeUser;
        @Value("${youtube.password}") String youtubePassword;
        Response clientLoginResponse = null;
        HttpRequestFactory requestFactory;
        HttpTransport transport;
        HttpParser jsonParser;
        HttpParser atomParser;
        HttpParser xmlParser;
        HttpParser textXmlParser;
        
        public YouTubeClient() {

        		this.transport
        			= new NetHttpTransport();
		
        		JacksonFactory jsonFactory 
        			= new JacksonFactory();
	    
        		JsonHttpRequestInitializer init
        			= new JsonHttpRequestInitializer() {
				
					@Override
					public void initialize(JsonHttpRequest jsonHttpRequest ) throws IOException {
						
						
					}
				
        		};
        	
        		this.jsonParser
                	= new JsonCParser( jsonFactory );
        		
                this.atomParser 
                	= new AtomParser( YOUTUBE_NAMESPACE_DICT );
                
                this.xmlParser 
                	= new XmlHttpParser( YOUTUBE_NAMESPACE_DICT );
                
                // XmlHttpParser is for application/xml, but we need text/xml as well (for error responses)
                textXmlParser 
                	= new XmlHttpParser( ERROR_NAMESPACE_DICT );
                // SGL| should set to text/xml
                
                // set up the HTTP request factory
                requestFactory 
                	= transport.createRequestFactory(
                			
                			new HttpRequestInitializer() {
            					@Override
		                        public void initialize(HttpRequest request) {
	                                // request.addParser(jsonParser);
	                                request.addParser(atomParser);
	                                request.addParser(xmlParser);
	                                request.addParser(textXmlParser);
	                                // set up the Google headers
	                                // GoogleHeaders headers = new GoogleHeaders();
	                                // headers.setApplicationName(appName);
	                                // headers.gdataVersion = "2";
	                                // headers.gdataKey = apiKey;
	                                // request.setHeaders( headers );
	                                // If we have a login response, we use it as the authentication header
	                                // if(clientLoginResponse != null) {
	                                //    request.getHeaders().setAuthorization( GoogleHeaders.getGoogleLoginValue(clientLoginResponse.auth) );
	                                // }
	                                request.setEnableGZipContent( true );
		                        }
                });
        }
        
        
        public Video video( String id ) {
        	
            try {
            	
            	GenericUrl gurl
            		= this.feedURL( id );
            	
                // build the HTTP GET request
                HttpRequest request 
                	= requestFactory.buildGetRequest( gurl );
                
                logger.info( "request-url: [{}]", request.getUrl() );
                
                HttpResponse response
                	= request.execute();
                
                logger.info( "response: [{}]", response );
                
                String status
                	= response.getStatusMessage();
                
                logger.info( "response-status: [{}]", status );
                
                String encoding = 
                	response.getContentEncoding();
                
                logger.info( "response-encoding: [{}]", encoding );
                
                /*
                String body
                	= response.parseAs( String.class );
                
                logger.info( "response-body: [{}]", body );
                */
                
                Video video
                	= response.parseAs( Video.class );
                
                logger.info( "response-video: [{}]", video );
                
                // execute the request and the parse video feed
                return video;
                
            }
            catch( Exception e ) {
            	
            	throw new RuntimeException( e );
            	
            }
              
                
        }
        
        protected GenericUrl feedURL( String id ) {
        	
        	String url 
        		= new StringBuilder( "http://gdata.youtube.com/feeds/api/videos/").append( id ).toString();
        	
        	return new GenericUrl( url );
        	
        }

}
