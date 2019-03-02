package com.pfasc.web.ws;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.InputStream;
import javax.ws.rs.core.Response;
import com.pfasc.web.db.dao.WebServiceDAO;
import com.pfasc.web.exceptions.*;
import java.text.ParseException;
import java.io.IOException;
import java.sql.SQLException;
import com.pfasc.web.db.domain.*;

/**
 * @version $Revision: 1.1 $
 * @author $Author: cg48910 $
 */

@Path("users")
public class WebUserResource {
		List<Login> list;

		public WebUserResource() {
	    	  
		}

	    @GET
		@Produces(MediaType.APPLICATION_XML)	    
	    public List<Login> getListOfUserlogins() {
		  
		      list = new ArrayList<Login>();	
		      WebServiceDAO dao = new WebServiceDAO();
		      List<Login> temp = new ArrayList<Login>();
		      Login login = new Login();
		      
		      try{
		    	  temp = dao.getAllLogins();
		    	  Iterator<Login> iter = temp.iterator();
		    	  
		    	  while(iter.hasNext()){
		    		  login = (Login) iter.next();
			    	  list.add(login);
		    	  }
		    	  
		      }catch(DatabaseException de){
		    	  System.out.println(de.getMessage());
		      }catch(SQLException sqle){
		    	  System.out.println(sqle.getMessage());		    	  
		      }

		      return list;
	    }
	    
	    @GET
		@Produces(MediaType.APPLICATION_XML)
		@Path("{tokens}")		
	    public Login getUserloginById(@PathParam("tokens") String tokens) {
		      WebServiceDAO dao = new WebServiceDAO();
		      Login login = new Login();
		      String[] params = new String[2];
		      int i = 0;
		      
		      for (String item: tokens.split(",")) {
		          params[i++] = item;
		      } 		      
		      
		      try{
		    	  login = dao.getLogin(params[0], dao.getApp(1,params[1]).getName());
		      }catch(ParseException pe){
		    	  System.out.println(pe.getMessage());
		      }catch(IOException ioe){
		    	  System.out.println(ioe.getMessage());		    	  
		      }
		      
		      return login;
	    }	    
	    
	    //@POST
	    //@Consumes(MediaType.APPLICATION_XML)
	    //public Response createUserlogin(InputStream is) {
	    //    return new Response();
	    // }

	    //@PUT
	    //@Path("{customerId}")
	    //@Consumes(MediaType.APPLICATION_XML)
	    //public Response updateUserlogin(@PathParam("customerId") String customerId, InputStream is) {
	    //    return new Response();
	    //}

	    @DELETE
		@Path("tokens")
	    public void deleteUserlogin(@PathParam("tokens") String tokens) {
		      WebServiceDAO dao = new WebServiceDAO();
		      Login login = new Login();
		      String[] params = new String[2];
		      int i = 0;
		      
		      for (String item: tokens.split(",")) {
		          params[i++] = item;
		      } 		      
		      
		      try{
		    	  login = dao.getLogin(params[0], params[1]);
		      }catch(ParseException pe){
		    	  System.out.println(pe.getMessage());
		      }catch(IOException ioe){
		    	  System.out.println(ioe.getMessage());		    	  
		      }
		      
	     }      
}    
	
