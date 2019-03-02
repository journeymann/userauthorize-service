package com.pfasc.web.ws;

import javax.ws.rs.*;
/**
 * @version $Revision: 1.1 $
 * @author $Author: cg48910 $
 */


@Path("helloworld")
public class WebResource {
    @GET
    @Produces("text/plain")
    public String getMessage() {
        return "Rest Never Sleeps";
    }
    
}