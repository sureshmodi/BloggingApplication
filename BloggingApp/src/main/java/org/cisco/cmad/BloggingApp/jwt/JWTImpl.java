package org.cisco.cmad.BloggingApp.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.cisco.cmad.BloggingApp.api.InvalidUserCredentialsException;
import org.cisco.cmad.BloggingApp.api.JWTAuthentication;

import com.stormpath.sdk.api.ApiKey;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.*;

public class JWTImpl implements JWTAuthentication {
	
	//private static final Key signingKey = MacProvider.generateKey();
		//private static final Key signingKey1 = MacProvider.generateKey();
		
		private Key signingKey;
		private static Map<String, Key> userkeys = new HashMap<>();

		public JWTImpl() {
			
		}

		//Sample method to construct a JWT
		//@SuppressWarnings({ "null", "unused" })
		public String generateJwtToken(String id, String issuer, String subject, long ttlMillis) {
		 
		    //The JWT signature algorithm we will be using to sign the token
		    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
		    //final ApiKey apikey = null;
		    	     
		    long nowMillis = System.currentTimeMillis();
		    Date now = new Date(nowMillis);
		 
		    //We will sign our JWT with our ApiKey secret
		    //String secretkey = apikey.getSecret();
		    
		    //System.out.println("Suresh: Secretkey String"+secretkey);
		    
		    //byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("secretkey");
		    //Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		        
		    signingKey = MacProvider.generateKey();
		    
		    userkeys.put(id, signingKey);
		    
		    System.out.println("Suresh: Algorithm"+signingKey.getAlgorithm()); 
		    System.out.println("Suresh: Format: "+signingKey.getFormat());
		    System.out.println("Suresh: Encoding Format: "+signingKey.getEncoded());
		    System.out.println("Suresh: String format: "+signingKey.toString());
		    System.out.println("Suresh: Signing Key: "+signingKey);
		  	   
		    
		    //Let's set the JWT Claims
		    JwtBuilder builder = Jwts.builder().setId(id)
		                                .setIssuedAt(now)
		                                .setSubject(subject)
		                                .setIssuer(issuer)
		                                .signWith(signatureAlgorithm, signingKey);
		 
		    //if it has been specified, let's add the expiration
		    if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		        Date exp = new Date(expMillis);
		        builder.setExpiration(exp);
		    }
		 
		    //Builds the JWT and serializes it to a compact, URL-safe string
		    return builder.compact();
		}
		
		//Sample method to validate and read the JWT
		public void parseJwtToken(String userid,String jwttoken) {
		 
		    //This line will throw an exception if it is not a signed JWS (as expected)
			
			signingKey = userkeys.get(userid);
		    Claims claims = Jwts.parser()         
		       .setSigningKey(signingKey)
		       .parseClaimsJws(jwttoken).getBody();
		    
		    System.out.println("ID: " + claims.getId());
		    System.out.println("Subject: " + claims.getSubject());
		    System.out.println("Issuer: " + claims.getIssuer());
		    System.out.println("Expiration: " + claims.getExpiration());
		    
		    /*if (!(Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwttoken).getBody()
		    		.getSubject().equals(userid))) {
		    	
		    	throw new InvalidUserCredentialsException("Unauthorized User");
		    		
		    }*/
		    
		    try {
		    	
		    	Jwts.parser().setSigningKey(signingKey).parseClaimsJws(jwttoken);
		    	System.out.println("Suresh: User Authenticated Successfully");
		    	
		    } catch (Exception e) {
				
		    	throw new InvalidUserCredentialsException("Unauthorized User");
			}
		    
		}

		public void deleteJwtToken(String userid) {
			userkeys.remove(userid);
			System.out.print("User logged out Successfully");
			
		}
		
	
}
