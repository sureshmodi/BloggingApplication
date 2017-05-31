package org.cisco.cmad.BloggingApp.Database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.cisco.cmad.BloggingApp.api.BlogPost;
import org.cisco.cmad.BloggingApp.api.UserDetails;

public class JPAUserDAO implements UserDAOInf {
	public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Blogging-App");

	@Override
	public void createUser(UserDetails userdetails) throws Exception {
		
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			em.persist(userdetails);
			tx.commit();
		} catch(Exception ex) {
			System.out.println("Suresh,Caught excpetion:"+ex.toString());
			// tx.rollback();
			em.close();
			throw new Exception();
		}
		em.close();

	}

	@Override
	public UserDetails updateProfile(UserDetails userdetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails retreiveUser(String userid) {
		
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		UserDetails user = em.find(UserDetails.class,userid);
		tx.commit();
		em.close();
		return user;
	}
	
	


}