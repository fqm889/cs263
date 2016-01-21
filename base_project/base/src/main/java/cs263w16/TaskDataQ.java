package cs263w16;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sicongfeng on 16/1/20.
 */
public class TaskDataQ extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("keyname");
        String val = request.getParameter("value");

        // Add the task to the default queue.
        Queue queue = QueueFactory.getDefaultQueue();
        queue.add(TaskOptions.Builder.withUrl("/rest/ds").method(TaskOptions.Method.POST).param("keyname", key).param("value", val));

        response.sendRedirect("/Done.html");
    }
}
