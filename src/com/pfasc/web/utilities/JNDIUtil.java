package com.pfasc.web.utilities;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @version $Revision: 1.1 $
 * @author $Author: cg48910 $
 */
public class JNDIUtil {
    private static Log logger = LogFactory.getLog(JNDIUtil.class);

    public static Context getInitialContext() throws NamingException {
        return new InitialContext();
    }

    public static Object lookup(String name) throws NamingException {
        Object obj = null;
        Context init_ctx = null;
        Context env_ctx = null;

        try {
            init_ctx = getInitialContext();
            env_ctx = (Context)init_ctx.lookup("java:/comp/env");
            obj = env_ctx.lookup(name);
        } finally {
            //close(env_ctx); Tomcat & cannot close context !!!
        }

        return obj;
    }

    public static void close(Context ctx) {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (Exception ex) {
                logger.error("Exception closing context", ex);
            }
        }
    }

}
