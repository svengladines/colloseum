package be.occam.colloseum.publish.appengine.itests;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import be.occam.colloseum.publish.application.config.PublishAppEngineApplicationConfig;
import be.occam.colloseum.publit.Publit;
import be.occam.colloseum.publit.repository.IPublitRepository;
import be.occam.colloseum.util.ConfigurationProfiles;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(ConfigurationProfiles.TEST)
@ContextConfiguration( classes= { PublishAppEngineApplicationConfig.class } )
public class TestPublits {
	
	@Resource
	IPublitRepository publitRepository;
	
	 private final LocalServiceTestHelper helper 
	 	= new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

	
	@Test
	public void doesItSmoke() {
		
		Publit publit
			= new Publit().setId( "o-x-o" );
		
		this.publitRepository.save( publit );
		
	}

}
