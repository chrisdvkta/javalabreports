import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@jakarta.servlet.annotation.WebServlet("/lab5/q05")
public class Lab5Q05CookiesDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("done by Krish Devkota");
        resp.setContentType("text/html; charset=UTF-8");

        // Set a cookie
        Cookie cookie = new Cookie("course", "AJP");
        cookie.setMaxAge(60 * 10);
        resp.addCookie(cookie);

        // Read cookies
        Cookie[] cookies = req.getCookies();
        resp.getWriter().println("<h2>Cookies Demo</h2>");
        resp.getWriter().println("<p>Cookie 'course' set to 'AJP'</p>");

        resp.getWriter().println("<h3>Cookies received:</h3>");
        if (cookies == null) {
            resp.getWriter().println("<p>No cookies received.</p>");
        } else {
            resp.getWriter().println("<ul>");
            Arrays.stream(cookies).forEach(c -> {
                try {
                    resp.getWriter().println("<li>" + c.getName() + " = " + c.getValue() + "</li>");
                } catch (IOException ignored) {
                }
            });
            resp.getWriter().println("</ul>");
        }

        resp.getWriter().println("<p>done by Krish Devkota</p>");
    }
}

