import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@jakarta.servlet.annotation.WebServlet("/lab5/q06")
public class Lab5Q06SessionDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("done by Krish Devkota");
        resp.setContentType("text/html; charset=UTF-8");

        HttpSession session = req.getSession(true);
        Integer count = (Integer) session.getAttribute("count");
        if (count == null) count = 0;
        count++;
        session.setAttribute("count", count);

        resp.getWriter().println("<h2>Session Demo</h2>");
        resp.getWriter().println("<p>Session ID: " + session.getId() + "</p>");
        resp.getWriter().println("<p>Visit count: " + count + "</p>");
        resp.getWriter().println("<p>done by Krish Devkota</p>");
    }
}

