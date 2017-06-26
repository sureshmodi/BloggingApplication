package org.cisco.cmad.BloggingApp.Database;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.cisco.cmad.BloggingApp.api.BlogPostEntity;
import org.cisco.cmad.BloggingApp.api.Comments;
import org.cisco.cmad.BloggingApp.api.UserCredentials;
import org.cisco.cmad.BloggingApp.api.UserDetails;
import org.cisco.cmad.BloggingApp.jwt.JWTImpl;

public class DBImpl {
	
		 
	//private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Blogging-App");
	public static void main(String[] args) { 
		      
			 //DBImpl impl = new DBImpl();
			  
//			 impl.createBook("Hibernate in Action", 12347,"Joshua Bloch" , "2nd edition");
//			 impl.createBook("JavaScript",12348,"Gavin King","4th edition");
//		     
//			 Books book=impl.retrieveBook(12347);
//			 System.out.println("Book title:"+book.getTitle());
//			 book=null;
//			 book=impl.retrieveBook(12348);
//			 System.out.println("Book title:"+book.getTitle());
//			 book=null;
//			 book=impl.updateBook(12348);
//			 System.out.println("Book title after update:"+book.getTitle());
			 			 		 
			 //impl.createUser("SURESH MODI", "sureshmodhi@gmail.com", "Bangalore", 9008505599L);
			 //Vehicles vehicle = impl.retrieveVehicle("KA01MC1348");
						    
			 //impl.createUserdetails();
			  
			  JWTImpl jwttoken = new JWTImpl();
			  
			  String key = jwttoken.generateJwtToken("Suresh","CMAD","Cisco",100000);
			  System.out.println("Suresh: Generated Token: "+key);
			  
			  //jwttoken.parseJWT(key);
	}

	public  void createBook(String title, int isbn, String author, String edition) {
		
//		Books book=new Books();
//		book.setTitle(title);
//		book.setIsbn_id(isbn);
//		book.setAuthor(author);
//		book.setYearofpublish(new Date());
//		book.setEdition_no(edition);
		
//		EntityManager em = factory.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		//em.persist(book);
//		tx.commit();
//		em.close();
				
	}
	
//	public Books retrieveBook(int isbn) {
//				
//		EntityManager em = factory.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		Books book = em.find(Books.class, isbn);
//		tx.commit();
//		em.close();
//		return book;			
//		
//	}
//	
//	public Books updateBook(int isbn) {
//		
//		EntityManager em = factory.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		Books book = em.find(Books.class, isbn);
//		System.out.println("Book title before update:"+book.getTitle());
//		book.setTitle("Spring in Action");
//		tx.commit();
//		em.close();
//		return book;			
//		
//	}
//	
//public  void createUser(String name,String mailid,String place,long mobile) {
//		
//		Users user=new Users();
//		user.setName(name);
//		user.setMailid(mailid);
//		user.setLocation(place);
//		user.setMobile(mobile);
//			
//		 car.setVehicleID("KA01MC1348");
//		 car.setVehiclename("Ford IKON");
//		 car.setVehicleType("Four Wheeler");
//		 car.setUser(user);
//		 
//		 bike.setVehiclename("Baja Pulsar");
//		 bike.setVehicleID("KA01EM6107");
//		 bike.setVehicleType("Two Wheeler");
//		 bike.setUser(user);
//		 
//		 user.getVehicle().add(car);
//		 user.getVehicle().add(bike);
//		 
//		 
//		//vehicle.setUser(user);
//				
//		EntityManager em = factory.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//		
//		em.persist(user);
//		tx.commit();
//		em.close();
//				
//	}
//
//public Vehicles retrieveVehicle(String vehicleid) {
//	
//	EntityManager em = factory.createEntityManager();
//	EntityTransaction tx = em.getTransaction();
//	tx.begin();
//	
//	Vehicles vehicle = em.find(Vehicles.class, vehicleid);
//	tx.commit();
//	em.close();
//	//Users user = vehicle.getUser();
//	//System.out.println("Owner name:"+user.getName());
//	return vehicle;			
//	
//}
//
//public void createUserdetails() {
//	
//	//UserDetails userdetails = new UserDetails("sureshmodhi@gmail.com","Suresh Modi","Nandi Park",
//	//										   9008505599L,"sureshmodi","abcdef");
//	
//	BlogPost blogpost1 = new BlogPost();
//	
//	blogpost1.setTitle("MATHS");
//	blogpost1.setBlogContent("Topic about Algebra");
//	//blogpost1.setUser(userdetails);
//	
//	Comments comment1= new Comments();
//	comment1.setComment("#comment1");
//	
//    blogpost1.getCommentslist().add(comment1);
//    //userdetails.getBloglist().put("blog1", blogpost1);
//    //blogpost1.setUser(userdetails);
//   // comment1.setBlogpost(blogpost1);
//    
//	EntityManager em = factory.createEntityManager();
//	EntityTransaction tx = em.getTransaction();
//	tx.begin();
//	
//	//em.persist(userdetails);
//	em.persist(blogpost1);
//	em.persist(comment1);
//	
//	tx.commit();
//	em.close();
//}

}
