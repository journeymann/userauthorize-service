package com.pfasc.web.exceptions;

/**
 * Nested Runtime Exception used any time failure occurs when an external is being generated
 * or imported.<br/><br/>
 * Suggest converting any code that uses this exception to better report behavior.<br/>
 * @version $Revision: 1.1 $
 * @author $Author: cg48910 $
 */
public class GenerationException extends NestedRuntimeException
{

    /**
     * @param reason
     */
	public GenerationException(String reason) {
        super(reason);
    }
    /**
     * @param reason
     * @param cause
     */
    public GenerationException(String reason, Throwable rootCause) {
        super(reason, rootCause);
    }

}
