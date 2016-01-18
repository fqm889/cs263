package cs263w16;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.memcache.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

// The Worker servlet should be mapped to the "/worker" URL.
public class Worker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();

        String key = request.getParameter("keyname");
        String val = request.getParameter("value");

        Entity tne = new Entity("TaskData", key);
        tne.setProperty("value", val);
        Date date = new Date();
        tne.setProperty("date", date);
        datastore.put(tne);
        syncCache.put(key, tne);
    }
}

