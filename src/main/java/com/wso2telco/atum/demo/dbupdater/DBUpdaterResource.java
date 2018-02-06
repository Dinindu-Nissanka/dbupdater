package com.wso2telco.atum.demo.dbupdater;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dbupdater")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DBUpdaterResource {

    private AppConfiguration configuration;
    private DBUpdateHandler dbUpdateHandler;

    public DBUpdaterResource(AppConfiguration configuration,
                             DBUpdateHandler dbUpdateHandler) {
        this.configuration = configuration;
        this.dbUpdateHandler = dbUpdateHandler;
    }

    @Path("/update/{id}")
    @GET
    public Response createMapping(@PathParam("id") String id){

        this.dbUpdateHandler.createMapping(id);
        return Response.status(200).build();
    }
}
