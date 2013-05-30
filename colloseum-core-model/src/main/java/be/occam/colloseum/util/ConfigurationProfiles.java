package be.occam.colloseum.util;

public class ConfigurationProfiles {

	public static final String TEST = "test";
	public static final String PRODUCTION = "production";
	public static final String DEV = "development";
	public static final String DEFAULT = PRODUCTION;

	public static final String SPRING_PROFILE_PROPERTY = "spring.profiles.active";

	/*
	 * Use the -D switch to change the default profile
	 */
	public static String activeProfile(){
		return System.getProperty(SPRING_PROFILE_PROPERTY, DEFAULT);
	}
	
	public static boolean isActive(String profile){
		return profile.equals(activeProfile());
	}
	
	public static void activateProfile(String profile) {
		
		if ( System.getProperty(SPRING_PROFILE_PROPERTY ) == null) {
			System.setProperty(SPRING_PROFILE_PROPERTY, profile);
		}
		else if ( profile.equals( activeProfile() ) ) {
			// do nothing
		}
		else {
			throw new RuntimeException("could not activate profile; another profile was already active: [" + System.getProperty(SPRING_PROFILE_PROPERTY) + "]");
		}
	}
	
	public static void activateProductionProfile() {
		
		activateProfile(PRODUCTION);
		
	}

	public static void activateTestProfile(){
		
		activateProfile( TEST );
		
	}

	public static void activateDevProfile() {
		
		activateProfile( DEV );
		
	}

}
