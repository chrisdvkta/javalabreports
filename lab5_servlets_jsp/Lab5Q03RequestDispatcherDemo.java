import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@jakarta.servlet.annotation.WebServlet("/lab5/q03")
public class Lab5Q03RequestDispatcherDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("done by Krish Devkota");
        req.setAttribute("msg", "Forwarded using RequestDispatcher");
        req.setAttribute("author", "Krish Devkota");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/Lab5Q03Result.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            resp.setContentType("text/plain; charset=UTF-8");
            resp.getWriter().println("Forward failed: " + e.getMessage());
        }
    }
}

