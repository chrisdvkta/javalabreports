import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;

@jakarta.servlet.annotation.WebServlet("/lab5/q02")
public class Lab5Q02GenericServletDemo extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        System.out.println("done by Krish Devkota");
        res.setContentType("text/plain; charset=UTF-8");
        res.getWriter().println("GenericServlet demo");
        res.getWriter().println("done by Krish Devkota");
    }
}

