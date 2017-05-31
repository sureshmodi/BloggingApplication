package org.cisco.cmad.BloggingApp.Database;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.cisco.cmad.BloggingApp.api.BlogPost;
import org.cisco.cmad.BloggingApp.api.Comments;
import org.cisco.cmad.BloggingApp.api.UserDetails;

public class JPABlogPostDAO implements BlogPostDAOInf {

	//private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Blogging-App");
	
	private EntityManagerFactory factory = JPAUserDAO.factory;
	@Override
	public void createBlogpost(BlogPost blogpost,String userid, String blogid) {
		 
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		UserDetails user = em.find(UserDetails.class, userid);
		user.getBloglist().put(blogid, blogpost);
		blogpost.setUser(user);
		em.persist(blogpost);
		tx.commit();
		em.close();

	}

	@Override
	public BlogPost deleteBlogpost(String blogpostid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogPost retrieveBlogpost(String blogpostid, String userid) {
		
		BlogPost blogpost = null;
		Map<String, BlogPost> blogpostlist = null;
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		UserDetails user = em.getReference(UserDetails.class, userid);
		if (user!=null) {
			blogpostlist = user.getBloglist();
		} else {
			//tx.commit();
			em.close();
			return null;
			
		}
		
		if(blogpostlist != null) {
			blogpost = blogpostlist.get(blogpostid);
			//blogpost.getCommentslist();
			//blogpost.setCommentslist(comm);
			//blogpost.getUser();
			//blogpost.setDatecreated(blogpost.getDatecreated());
		} else {
			//tx.commit();
			em.close();
			return null;
		}
		
		if(blogpost!=null) {
			//tx.commit();
			em.close();
			return blogpost;
		} else {
			//tx.commit();
			em.close();
			return null;
		}
	}

	@Override
	public List<BlogPost> listallBlogPosts() {
		// TODO Auto-generated method stub
		return null;
	}

}
