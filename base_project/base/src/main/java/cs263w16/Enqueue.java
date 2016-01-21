package cs263w16;

// The Enqueue servlet should be mapped to the "/enqueue" URL.
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

public class Enqueue extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("keyname");
        String val = request.getParameter("value");

        // Add the task to the default queue.
        Queue queue = QueueFactory.getDefaultQueue();
        queue.add(TaskOptions.Builder.withUrl("/worker").param("keyname", key).param("value", val));
        // queue.add(TaskOptions.Builder.withUrl("/rest/ds").method(TaskOptions.Method.POST).param("keyname", key).param("value", val));

        response.sendRedirect("/Done.html");
    }
}

