package com.pfasc.web.exceptions;

/**
 * Nested Exception used any time failure occurs when data is being updated.
 * @version $Revision: 1.1 $
 * @author $Author: cg48910 $
 */
public class DataUpdateException extends NestedException {



    /**
     * @param reason
     */
    public DataUpdateException(String reason) {
        super(reason);
    }

    /**
     * @param reason
     * @param rootCause
     */
    public DataUpdateException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }



    /* return the contained SQLException.
     */
    public Throwable getRootCause() {
        Throwable t = super.getRootCause();

        return t;
    }

}
