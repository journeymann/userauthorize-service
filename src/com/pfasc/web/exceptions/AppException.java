package com.pfasc.web.exceptions;

import com.pfasc.web.exceptions.NestedRuntimeException;
/**
 * @version 1.1 $
 * @author cg48910 $
 * To handle any general application exceptions.
 */
public class AppException extends NestedRuntimeException {
    /**
     * @param msg
     */
    public AppException(String msg) {
        super(msg);
    }

    /**
     * @param msg
     * @param cause
     */
    public AppException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
