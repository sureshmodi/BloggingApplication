package org.cisco.cmad.BloggingApp.api;

public interface JWTAuthentication {
	
	public String generateJwtToken(String userid, String issuer, String subject, long ttlMillis);
	public void parseJwtToken(String userid,String jwttoken);
	public void deleteJwtToken(String userid);

}
