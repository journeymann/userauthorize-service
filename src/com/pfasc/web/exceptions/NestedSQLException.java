package com.pfasc.web.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Simple SQLException extention which allows you to nest any exception inside.
 * @version $Revision: 1.1 $
 * @author $Author: cg48910 $
 */
public class NestedSQLException extends SQLException {

    private Throwable rootCause = null;

    public NestedSQLException(String reason) {
        super(reason);
    }

    /**
     * Custom stack trace
     * @param reason
     * @param cause
     */
    public NestedSQLException(String reason, Throwable rootCause) {
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
