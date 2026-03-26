import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@jakarta.servlet.annotation.WebServlet("/lab5/q04")
public class Lab5Q04SendRedirectDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("done by Krish Devkota");
        String msg = URLEncoder.encode("Redirected using sendRedirect()", StandardCharsets.UTF_8);
        resp.sendRedirect(req.getContextPath() + "/Lab5Q04RedirectTarget.jsp?msg=" + msg);
    }
}

