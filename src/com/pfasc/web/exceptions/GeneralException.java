package com.pfasc.web.exceptions;

import com.pfasc.web.exceptions.NestedRuntimeException;

/**
 * @version 1.1 $
 * @author cg48910 $
 * To handle any general exceptions.
 */
public class GeneralException extends NestedRuntimeException {
    /**
     * @param msg
     */
    public GeneralException(String msg) {
        super(msg);
    }

    /**
     * @param msg
     * @param cause
     */
    public GeneralException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
