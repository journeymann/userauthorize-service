package com.pfasc.web.db.dao;

import java.io.IOException;
import java.text.ParseException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.HashMap;
import oracle.jdbc.*;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.io.FileUtils;


import com.pfasc.web.constants.Constants;
import com.pfasc.web.db.dao.CommonDAO;
import com.pfasc.web.utilities.JDBCUtil;

import com.pfasc.web.db.domain.Login;
import com.pfasc.web.db.domain.App;
import com.pfasc.web.exceptions.*;

/**
 * @version 1.1 $
 * @author cg48910 $
 * General database access methods
 */
public class WebServiceDAO extends CommonDAO {
	/** Uses log4J and the variable is being declared. Logger defined in the Constants class */
	private static Log logger = LogFactory.getLog(Constants.APP_LOGGER);
	
	/** Database connection is being setup. JNDI key is defined in the constants class
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return this.getConnection(Constants.APP_DS);
    }
    
	/** Returns an App object based on the search criteria
     * @param param1 toggle value to determine value to search by i.e. by id or by name
     * @param param2 value to search by 
     * @return App object 
     * @throws SQLException
     */
    public App getApp(int param1, String param2){
    	String query = "";
    	switch(param1){
	    	case 1: 
	        	query =	"select t.application_name, t.application_id from applications t where t.application_id = "+param2;	    		
	    		break;
	    	case 2:
	        	query =	"select t.application_name, t.application_id from applications t where t.application_name = \'"+param2+"\'";	    		
	    		break;
	    	default:
    	}
    	
    	App retval = new App();
    	PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        try{
            con = getConnection();
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
            	retval.setId(rs.getInt("application_id"));
            	retval.setName(rs.getString("application_name"));
            }	
        }
        catch(Exception e)
        {
        	logger.error(this.getClass().getName()+":getApp(): Error retreiving application information:+"+e.getMessage());
        }
        
        finally
        {
        	 try {
            	if(rs != null)
            		rs.close();
            	if(ps != null)
            		ps.close();
            	if(con != null)
            		con.close();
        	 }
        	 catch(Exception e)
        	 {
        		 logger.error(this.getClass().getName()+": getApp(): Error closing resources:+"+e.getMessage());
        	 }
        }
    	
    	return retval;

    	
    }

	/** Returns a List of all App objects
     * @return List<App> of App objects 
     * @throws DatabaseException
     * @throws SQLException 
     */
    public ArrayList<App> getAllApps()throws DatabaseException, SQLException{
    	
    	String query =	"select t.application_name, t.application_id from applications t";
    	PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        ArrayList<App> list = new ArrayList<App>();
        App app = null;
        try{
            con = getConnection();
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
            	app = new App();
            	app.setName(rs.getString("application_name"));
            	app.setId(rs.getInt("application_id"));
            	list.add(app);
            }	
        }
        catch(Exception e)
        {
        	logger.error(this.getClass().getName()+":getAllApps(): Error retreiving application information:+"+e.getMessage());
        }
        
        finally
        {
        	 try {
            	if(rs != null)
            		rs.close();
            	if(ps != null)
            		ps.close();
            	if(con != null)
            		con.close();
        	 }
        	 catch(Exception e)
        	 {
        		 logger.error(this.getClass().getName()+": getAllApps(): Error closing resources:+"+e.getMessage());
        	 }
        }
    	
    	return list;

    	
    }
    
    /**
     * Retrieves user login information. Used when user navigates the site. loginName is the NT Token login name eg CG48910 
     * @param loginName user name of user we are retrieving login information
     * @param appName application name  
     * @return Login
     * @throws IOException
     * @throws ParseException
     */
    public Login getLogin(String loginName, String appName) throws IOException, ParseException{
    	
    	
    	Connection conn = null;
        CallableStatement stmt = null;
        String sql;
        Login login = new Login();
        String result = "";
        try {
        	conn = this.getConnection();

            sql = "BEGIN PkgAppSec.spCheckLoginUser(?,?,?,?,?); END;";
            stmt = (CallableStatement) conn.prepareCall(sql);
            stmt.setString(1, loginName);
            stmt.setString(2, appName);            
            stmt.registerOutParameter(3, OracleTypes.VARCHAR);
            stmt.registerOutParameter(4, OracleTypes.VARCHAR);
            stmt.registerOutParameter(5, OracleTypes.VARCHAR);
            stmt.execute();
           
            login.setLoginName(loginName);
            login.setUserName(stmt.getString(3));
            login.setValid(stmt.getString(4).trim().equalsIgnoreCase("Y"));
            
            result = stmt.getString(5);
            
        } catch (GeneralException sqle) {
            logger.error("GeneralException.getLogin Exception" + result + sqle.getMessage());
        } catch (SQLException sqle) {
            logger.error("GeneralException.getLogin SQLException "+ loginName +" result" + result + " " +sqle.getMessage());
        } finally {
        	try {
        		if(stmt != null)
                	stmt.close();
            	
            	if (conn != null) 
                conn.close();
            	
            }  catch (Exception e) {
                    logger.error("GeneralException.getLogin..Unable to close connection. " + e.getMessage());
                }
            
        }
    	
    	return login;
    }    
    
    /**
     * Retrieves information for all users.
     * @param none
     * @return ArrayList<Login>
     * @throws DatabaseException
     * @throws SQLException
     */
    public ArrayList<Login> getAllLogins() throws DatabaseException, SQLException{
    	
    	ArrayList<Login> retVal = new ArrayList<Login>();
    	Login login = new Login();
    	
    	String query =	"select t.employeeid, t.username, t.firstname || ' ' || t.lastname as name, 1 as valid";
    	query += " from employees t, application_access ae";
    	query += " where t.employeeid = ae.employeeid and ae.application_id = 10004";
    	
    	PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        try{
            con = getConnection();
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
            	login = new Login();
            	
            	login.setLoginName(rs.getString("username"));
            	login.setRoleDesc("read-write");
            	login.setRoleid(100);
            	login.setUserid(rs.getInt("employeeid"));
            	login.setUserName(rs.getString("name"));
            	login.setValid(true);
            	
            	retVal.add(login);
            }	
        }
        catch(Exception e)
        {
        	logger.error(this.getClass().getName()+":getAllLogins(): Error retreiving user information:+"+e.getMessage());
        }
        
        finally
        {
        	 try {
            	if(rs != null)
            		rs.close();
            	if(ps != null)
            		ps.close();
            	if(con != null)
            		con.close();
        	 }
        	 catch(Exception e)
        	 {
        		 logger.error(this.getClass().getName()+": getAllLogins(): Error closing resources:+"+e.getMessage());
        	 }
        }
    	
    	return retVal;
    }    
}

