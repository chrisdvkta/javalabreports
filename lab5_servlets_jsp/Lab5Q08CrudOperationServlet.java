import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@jakarta.servlet.annotation.WebServlet("/lab5/q08")
public class Lab5Q08CrudOperationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("done by Krish Devkota");
        String action = safe(req.getParameter("action"));

        try (Connection c = connect()) {
            ensureStudentsTable(c);

            if ("delete".equalsIgnoreCase(action)) {
                int id = parseInt(req.getParameter("id"), -1);
                if (id >= 0) delete(c, id);
                resp.sendRedirect(req.getContextPath() + "/lab5/q08");
                return;
            }

            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<h2>CRUD Operation (students)</h2>");
            resp.getWriter().println("<form method='post'>");
            resp.getWriter().println("Id: <input name='id' /> ");
            resp.getWriter().println("Name: <input name='name' /> ");
            resp.getWriter().println("Age: <input name='age' /> ");
            resp.getWriter().println("<button type='submit' name='action' value='create'>Create</button> ");
            resp.getWriter().println("<button type='submit' name='action' value='update'>Update</button>");
            resp.getWriter().println("</form>");

            resp.getWriter().println("<h3>Students</h3>");
            resp.getWriter().println("<table border='1' cellpadding='6' cellspacing='0'>");
            resp.getWriter().println("<tr><th>Id</th><th>Name</th><th>Age</th><th>Action</th></tr>");
            try (Statement st = c.createStatement();
                 ResultSet rs = st.executeQuery("SELECT id, name, age FROM students ORDER BY id")) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    resp.getWriter().println("<tr>");
                    resp.getWriter().println("<td>" + id + "</td><td>" + name + "</td><td>" + age + "</td>");
                    resp.getWriter().println("<td><a href='" + req.getContextPath() + "/lab5/q08?action=delete&id=" + id + "'>Delete</a></td>");
                    resp.getWriter().println("</tr>");
                }
            }
            resp.getWriter().println("</table>");
            resp.getWriter().println("<p>done by Krish Devkota</p>");
        } catch (Exception e) {
            resp.setContentType("text/plain; charset=UTF-8");
            resp.getWriter().println("CRUD error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("done by Krish Devkota");
        String action = safe(req.getParameter("action"));

        int id = parseInt(req.getParameter("id"), -1);
        String name = safe(req.getParameter("name"));
        int age = parseInt(req.getParameter("age"), -1);

        try (Connection c = connect()) {
            ensureStudentsTable(c);

            if ("create".equalsIgnoreCase(action)) {
                if (id >= 0 && !name.isEmpty() && age >= 0) create(c, id, name, age);
            } else if ("update".equalsIgnoreCase(action)) {
                if (id >= 0 && !name.isEmpty() && age >= 0) update(c, id, name, age);
            }
        } catch (Exception ignored) {
        }

        resp.sendRedirect(req.getContextPath() + "/lab5/q08");
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

    private static void ensureStudentsTable(Connection c) throws Exception {
        try (Statement st = c.createStatement()) {
            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "age INT NOT NULL" +
                ")"
            );
        }
    }

    private static void create(Connection c, int id, String name, int age) throws Exception {
        try (PreparedStatement ps = c.prepareStatement("INSERT INTO students (id, name, age) VALUES (?, ?, ?)")) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.executeUpdate();
        }
    }

    private static void update(Connection c, int id, String name, int age) throws Exception {
        try (PreparedStatement ps = c.prepareStatement("UPDATE students SET name=?, age=? WHERE id=?")) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }

    private static void delete(Connection c, int id) throws Exception {
        try (PreparedStatement ps = c.prepareStatement("DELETE FROM students WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private static int parseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return fallback;
        }
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim();
    }
}

