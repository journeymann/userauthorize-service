package com.pfasc.web.utilities;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pfasc.web.exceptions.NestedSQLException;

/**
 * @version $Revision: 1.1 $
 * @author $Author: cg48910 $
 */
public class JDBCUtil
{
    private static final Log logger = LogFactory.getLog(JDBCUtil.class);

    public static Connection getConnection(String dataSourceName) throws SQLException
    {
        try
        {
            DataSource ds = (DataSource) JNDIUtil.lookup(dataSourceName);
            return ds.getConnection();
        }
        catch (NamingException nex)
        {
            throw new NestedSQLException("getConnection("+dataSourceName+")", nex);
        }
    }
    
    public static Connection getConnection(String driverName, String url, String userName, String pass) throws SQLException
    {
        Driver driver = null;
        Properties props = new Properties();

        try
        {
            props.put("user", userName);
            props.put("password", pass);
            driver = (Driver) Class.forName(driverName).newInstance();
        }
        catch (Exception ex)
        {
            throw new NestedSQLException("getConnection("+driverName+","+url+","+userName+","+pass+",)"+ex.toString());
        }

        return driver.connect(url, props);

    }

    public static void close(Connection conn, Statement stmt, ResultSet rs)
    {
        close(rs);
        close(stmt);
        close(conn);
    }

    public static void close(Connection conn, Statement stmt)
    {
        close(stmt);
        close(conn);
    }

    public static void close(Connection conn)
    {
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (Exception ex)
            {
                logger.warn("close(Connection) failed", ex);
            }
        }
    }

    public static void close(Statement stmt)
    {
        if (stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (Exception ex)
            {
                logger.warn("close(Statement) failed", ex);
            }
        }
    }

    public static void close(ResultSet rs)
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (Exception ex)
            {
                logger.warn("close(ResultSet) failed", ex);
            }
        }
    }

}
