import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@jakarta.servlet.annotation.WebServlet("/lab5/q07")
public class Lab5Q07LoginValidationDbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("done by Krish Devkota");
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("<h2>Login Validation (DB)</h2>");
        resp.getWriter().println("<form method='post'>");
        resp.getWriter().println("Username: <input name='username' /> <br/>");
        resp.getWriter().println("Password: <input name='password' type='password' /> <br/>");
        resp.getWriter().println("<button type='submit'>Login</button>");
        resp.getWriter().println("</form>");
        resp.getWriter().println("<p>Default user (auto-created if DB is reachable): <b>krish</b> / <b>ajp</b></p>");
        resp.getWriter().println("<p>done by Krish Devkota</p>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("done by Krish Devkota");
        String username = safe(req.getParameter("username"));
        String password = safe(req.getParameter("password"));

        boolean ok;
        String error = null;

        try (Connection c = connect()) {
            ensureUsersTable(c);
            ok = validate(c, username, password);
        } catch (Exception e) {
            ok = false;
            error = e.getMessage();
        }

        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().println("<h2>Login Result</h2>");
        if (error != null) {
            resp.getWriter().println("<p>DB error: " + error + "</p>");
        } else if (ok) {
            resp.getWriter().println("<p>Login successful for user: <b>" + username + "</b></p>");
        } else {
            resp.getWriter().println("<p>Login failed.</p>");
        }
        resp.getWriter().println("<p><a href='" + req.getContextPath() + "/lab5/q07'>Back</a></p>");
        resp.getWriter().println("<p>done by Krish Devkota</p>");
    }

    private static Connection connect() throws Exception {
        String port = System.getenv().getOrDefault("AJP_MYSQL_PORT", "3310");
        String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:" + port + "/ajp");
        String user = System.getenv().getOrDefault("DB_USER", "ajp");
        String pass = System.getenv().getOrDefault("DB_PASS", "ajp");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
        }

        return DriverManager.getConnection(url, user, pass);
    }

    private static void ensureUsersTable(Connection c) throws Exception {
        try (Statement st = c.createStatement()) {
            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS users (" +
                    "username VARCHAR(100) PRIMARY KEY, " +
                    "password VARCHAR(100) NOT NULL" +
                ")"
            );
        }
        try (PreparedStatement ps = c.prepareStatement(
            "INSERT INTO users (username, password) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE password = password")) {
            ps.setString(1, "krish");
            ps.setString(2, "ajp");
            ps.executeUpdate();
        }
    }

    private static boolean validate(Connection c, String username, String password) throws Exception {
        try (PreparedStatement ps = c.prepareStatement("SELECT 1 FROM users WHERE username=? AND password=?")) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim();
    }
}

