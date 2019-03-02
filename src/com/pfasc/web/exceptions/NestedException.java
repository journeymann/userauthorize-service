package com.pfasc.web.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @version $Revision: 1.1 $
 * @author $Author: cg48910 $
 */
public class NestedException extends Exception {

    private Throwable rootCause = null;

    /**
     * @param reason
     */
    public NestedException(String reason) {
        super(reason);
    }

    /**
     * @param reason
     * @param cause
     */
    public NestedException(String reason, Throwable rootCause) {
        super();

        this.rootCause = rootCause;
    }

    public Throwable getRootCause() {
        return rootCause;
    }

    /**
     * Custom stack trace
     */
    public void printStackTrace() {
        printStackTrace(System.out);
    }

    /**
     * Custom stack trace
     * @param PrintStream
     */
    public void printStackTrace(PrintStream s) {
        printStackTrace(new PrintWriter(s));
    }

    /**
     * Custom stack trace
     * @param PrintWriter
     */
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);

        if (rootCause != null) {
            s.println("*** Root cause is :");
            rootCause.printStackTrace(s);
        }
    }

}
