package org.cisco.cmad.BloggingApp.Database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.cisco.cmad.BloggingApp.api.BlogPost;
import org.cisco.cmad.BloggingApp.api.Comments;
import org.cisco.cmad.BloggingApp.api.UserDetails;

public class JPACommentsDAO {
	
//private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Blogging-App");
	private EntityManagerFactory factory = JPAUserDAO.factory;
	
	public Comments postComment(Comments comment,String blogid) {
		 
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		 BlogPost blogpost = em.find(BlogPost.class, blogid);
		 
		 if (blogpost!=null) {
			  	blogpost.getCommentslist().add(comment);
			 	//comment.setBlogpost(blogpost);
		 }	 
		
		em.persist(comment);
		tx.commit();
		em.close();
		
		em = factory.createEntityManager();
		tx = em.getTransaction();
		tx.begin();
		blogpost = em.find(BlogPost.class, blogid);
		List<Comments>commlist=blogpost.getCommentslist();
		
		em.close();
		
		return commlist.get(0);

	}

}
