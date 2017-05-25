package org.cisco.cmad.BloggingApp.Database;
import java.util.HashMap;
import java.util.Map;

import org.cisco.cmad.BloggingApp.api.BlogUsers;

public class Database {
	
	private static Map<Long, BlogUsers> profiles = new HashMap<>();
	
	public static Map<Long, BlogUsers> getProfiles() {
		
		return profiles;
		
	}

}
