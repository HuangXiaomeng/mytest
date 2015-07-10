package com.armon.test.http;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("rest")
public class RestfulResource {

    @POST
    @Path("hello")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello(@FormParam("token") String token, @FormParam("time") long timestamp,
            @FormParam("name") String appName, @FormParam("my") String myname) {

        String data = "hello " + myname + " , I'm restful";
        return Response.status(200).entity(data).build();
    }

    @GET
    @Path("hi")
    @Produces(MediaType.TEXT_PLAIN)
    public String hi() {
        return "hi, my name is restful";
    }
}
