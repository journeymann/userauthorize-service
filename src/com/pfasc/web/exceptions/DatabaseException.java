package com.pfasc.web.exceptions;

/**
 * @version 1.1 $
 * @author cg48910 $
 * To handle any database related exceptions.
 */

public class DatabaseException extends NestedException {
    /**
     * @param message
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * @param msg
     * @param cause
     */
    public DatabaseException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * @param msg
     * @param ex  exception
     */
    public DatabaseException(String msg, Exception ex) {
        super(msg, ex);
    }

    public Throwable getRootCause() {
        Throwable t = super.getRootCause();

        return t;
    }
}


