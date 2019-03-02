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

@Path("apps")
public class WebAppResource {
		List<App> list;

		public WebAppResource() {
	    	  
		}

	    @GET
		@Produces(MediaType.APPLICATION_XML)	    
	    public List<App> getListOfApps() {
		  
		      list = new ArrayList<App>();	
		      WebServiceDAO dao = new WebServiceDAO();
		      List<App> temp = new ArrayList<App>();
		      App app = new App();
		      
		      try{
		    	  temp = dao.getAllApps();
		    	  Iterator<App> iter = temp.iterator();
		    	  
		    	  while(iter.hasNext()){
		    		  app = (App) iter.next();
			    	  list.add(app);
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
	    public App getInfo(@PathParam("tokens") String tokens) {
		      WebServiceDAO dao = new WebServiceDAO();
		      App retval = new App();
		      String[] params = new String[2];
		      int i = 0;
		      
		      for (String item: tokens.split(",")) {
		          params[i++] = item;
		      } 		      
		      
    		  retval = dao.getApp(Integer.parseInt(params[0]), params[1]);
      
		      return retval;
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
	
