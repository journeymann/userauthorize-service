package com.pfasc.web.db.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.pfasc.web.utilities.JDBCUtil;

/**
 * @version 1.1 $
 * @author cg48910 $
 *
 */
public class CommonDAO {
  
    private final Logger logger = Logger.getLogger(CommonDAO.class);
    /**
     * Get connection 
     * @param dsName
     * @return
     * @throws SQLException
     */
    public Connection getConnection(String dsName) throws SQLException {
        return JDBCUtil.getConnection(dsName);
    }
    
    /**
     * Close all open resources
     * @param con
     * @param rs
     * @param ps
     * @throws SQLException
     */
    public void cleanUp(Connection con, ResultSet rs, Statement ps) throws SQLException {
        try {
	        if (rs != null) {
	            rs.close();
	        }
	        if (ps != null) {
	            ps.close();
	        }
	        if (con != null) {
	            con.close();      
	        }
        } catch (SQLException e) {
            logger.debug("Error while closing resources.", e);
        }
    }
    
}
