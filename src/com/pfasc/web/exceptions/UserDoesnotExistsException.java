package com.pfasc.web.exceptions;
import java.lang.Exception;
/**
 * Simple SQLException extension which allows you to nest any exception inside.
 * @version $Revision: 1.1 $
 * @author $Author: cg48910 $
 */
public class UserDoesnotExistsException extends Exception {

    /**
     * 
     */
    public UserDoesnotExistsException() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public UserDoesnotExistsException(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public UserDoesnotExistsException(Throwable arg0) {
        super(arg0.getMessage());
        // TODO Auto-generated constructor stub
    }
}
