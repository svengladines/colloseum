package be.occam.colloseum.soccer.application.web;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.LoggerFactory;

import be.occam.colloseum.util.ConfigurationProfiles;
import be.occam.test.jtest.JTest;

public class RunLocal_Production extends JTest {
	
	public RunLocal_Production() {
		super("/colloseum-soccer");
		this.forcePort = 8089;
	}
	
	public void test( ) throws Exception {
		
		
		
	}
	
	public static void main( String[] args ) throws Exception {
		
		ConfigurationProfiles.activateProductionProfile();
		
		RunLocal_Production tst = new RunLocal_Production();
		
		try {
			
			disableSSLVerification();
			
			tst.setUp();
			
			Thread.sleep( 1000000 );
			
		}
		catch( Throwable e ) {
			LoggerFactory.getLogger( tst.getClass() ).warn( "something got thrown", e );
		}
		finally {
			tst.tearDown();
			System.exit( 1 );
		}
	}
	
	public static void disableSSLVerification() {
		
	    try {
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        X509TrustManager tm = new X509TrustManager() {

	            public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
	            }

	            public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
	            }

	            public X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	        };
	        ctx.init(null, new TrustManager[]{tm}, null);
	        SSLContext.setDefault(ctx);
	    } catch (Exception ex) {
	        throw new RuntimeException( ex );
	    }
		
	}

}
