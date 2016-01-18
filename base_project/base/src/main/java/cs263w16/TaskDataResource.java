package cs263w16;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;
import java.util.logging.*;
import com.google.appengine.api.datastore.*;
import javax.xml.bind.JAXBElement;

public class TaskDataResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String keyname;

    public TaskDataResource(UriInfo uriInfo, Request request, String kname) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.keyname = kname;
    }
    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public TaskData getTaskDataHTML() {
        //add your code here (get Entity from datastore using this.keyname)
        // throw new RuntimeException("Get: TaskData with " + keyname +  " not found");
        //if not found
        return null;
    }

    // for the application
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public TaskData getTaskData() {
        //same code as above method
        return null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
        public Response putTaskData(String val) {
        Response res = null;
        //add your code here
        //first check if the Entity exists in the datastore
        //if it is not, create it and
        //signal that we created the entity in the datastore
        res = Response.created(uriInfo.getAbsolutePath()).build();
        //else signal that we updated the entity
        res = Response.noContent().build();

        return res;
    }

    @DELETE
    public void deleteIt() {

        //delete an entity from the datastore
        //just print a message upon exception (don't throw)
    }
}

