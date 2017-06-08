package org.cisco.cmad.BloggingApp.Database;

import java.util.List;

import org.cisco.cmad.BloggingApp.api.Comments;

public interface CommentsDAOInf {
	
	public Comments postComments(Comments comment,String blogid);
	public List<Comments> getallComments(String blogid);

}
