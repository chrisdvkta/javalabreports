import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@jakarta.servlet.annotation.WebServlet("/lab5/q01")
public class Lab5Q01WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("done by Krish Devkota");
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("<h2>Welcome Servlet Program</h2>");
        resp.getWriter().println("<p>done by Krish Devkota</p>");
    }
}

