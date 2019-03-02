package com.pfasc.web.exceptions;

/**
 * Simple SQLException extension which allows you to nest any exception inside.
 * @version $Revision: 1.1 $
 * @author $Author: cg48910 $
 */
public class UserNotAuthorizedException extends Exception {

    /**
     * 
     */
    public UserNotAuthorizedException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public UserNotAuthorizedException(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public UserNotAuthorizedException(Throwable arg0) {
        super(arg0.getMessage());
        // TODO Auto-generated constructor stub
    }
  
    
}
