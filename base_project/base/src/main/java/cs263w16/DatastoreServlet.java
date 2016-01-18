package cs263w16;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.memcache.*;
import com.google.appengine.repackaged.com.google.common.base.Flag;

@SuppressWarnings("serial")
public class DatastoreServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      resp.setContentType("text/html");
      resp.getWriter().println("<html><body>");

      //Add your code here
      DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
      String keyname = req.getParameter("keyname");
      String value = req.getParameter("value");

      for (Enumeration<String> e = req.getParameterNames(); e.hasMoreElements();) {
          String s = e.nextElement();
          if (!(s.equals("value") || s.equals("keyname"))) {
              resp.getWriter().println("<h3>Error. "+s + " Input must be keyname and value.</h3>");
              return;
          }
      }

      if (keyname!=null) {
          if (value != null) {
//              Entity tne = new Entity("TaskData",keyname);
//              tne.setProperty("value", value);
//              Date date = new Date();
//              tne.setProperty("date", date);
//              datastore.put(tne);
//              String out = String.format("<h3>Stored %s and %s in Datastore</h3>", keyname, value);
//              resp.getWriter().println(out);

              syncCache.put(keyname, value);
              String out = String.format("<h3>Stored %s and %s in Memcache</h3>", keyname, value);
              resp.getWriter().println(out);
          }
          else {
              Key k = KeyFactory.createKey("TaskData", keyname);
              Boolean inds = true, inmc = true;
              if (!syncCache.contains(keyname)) {
                  inmc = false;
              }
              try {
                  Entity tne = datastore.get(k);
                  String out = String.format("<h3>TaskData keyname:%s with value:%s on Date:%s in Datastore</h3>",
                          keyname, tne.getProperty("value"), tne.getProperty("date"));
                  resp.getWriter().println(out);
                  if (inmc) resp.getWriter().println("Both");
                  else {
                      syncCache.put(keyname, tne.getProperty("value"));
                      resp.getWriter().println("Datastore");
                  }
              } catch (EntityNotFoundException e) {
                  inds = false;
                  if (inmc) resp.getWriter().println("Memcache");
                  else resp.getWriter().println("Neither");
              }
          }
      }
      else {
          Query q = new Query("TaskData");
          PreparedQuery pq = datastore.prepare(q);
          for (Entity res : pq.asIterable()) {
              Key key = res.getKey();
              String kn = key.getName();
              if (syncCache.contains(kn)) {
                  Object ent = syncCache.get(kn);
                  String v;
                  if (ent.getClass().equals(Entity.class)) {
                      v = (String)((Entity) syncCache.get(kn)).getProperty("value");
                  }
                  else {
                      v = (String) syncCache.get(kn);
                  }
                  String out = String.format("<h3>Stored %s and %s in Memcache</h3>", kn, v);
                  resp.getWriter().println(out);
              }
              else {
                  Key k = KeyFactory.createKey("TaskData", kn);
                  try {
                      String val = (String)datastore.get(k).getProperty("value");
                      syncCache.put(kn, val);
                      String out = String.format("<h3>Stored %s and %s in DataStore and stored in Memcache</h3>", kn, val);
                      resp.getWriter().println(out);
                  } catch (EntityNotFoundException e) {
                  }
              }
          }
      }

      resp.getWriter().println("</body></html>");
  }
}