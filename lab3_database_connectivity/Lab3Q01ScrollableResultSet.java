import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lab3Q01ScrollableResultSet {
    public static void main(String[] args) {
        String url = args.length > 0 ? args[0] : System.getenv().getOrDefault("DB_URL", defaultUrl());
        String user = args.length > 1 ? args[1] : System.getenv().getOrDefault("DB_USER", "ajp");
        String pass = args.length > 2 ? args[2] : System.getenv().getOrDefault("DB_PASS", "ajp");

        // Optional: helps if you're using MySQL. Safe to ignore if driver isn't
        // present.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
            System.out.println("MySQL JDBC driver not found on classpath.");
            System.out.println("Tip: Add MySQL Connector/J (mysql-connector-j) to your classpath.");
        }

        try (Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY)) {

            System.out.println("Connected to database.");
            ensureStudentsTableExists(connection);
            ensureSampleData(connection);

            try (ResultSet rs = statement.executeQuery("SELECT id, name, age FROM students ORDER BY id")) {
                if (!rs.next()) {
                    System.out.println("No rows found in students table.");
                    System.out.println("done by Krish Devkota");
                    return;
                }

                rs.first();
                printRow("First row", rs);

                rs.last();
                printRow("Last row", rs);

                if (rs.absolute(2)) {
                    printRow("Absolute(2) row", rs);
                } else {
                    System.out.println("Absolute(2) row: not available");
                }

                if (rs.previous()) {
                    printRow("Previous row (from current)", rs);
                } else {
                    System.out.println("Previous row: not available");
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            if (e.getMessage() != null && e.getMessage().contains("No suitable driver")) {
                System.out.println("Tip: This usually means the JDBC driver jar is missing.");
            }
            System.out.println("Tip: Provide DB args: java Lab3Q01ScrollableResultSet <url> <user> <pass>");
        }

        System.out.println("done by Krish Devkota");
    }

    private static String defaultUrl() {
        String port = System.getenv().getOrDefault("AJP_MYSQL_PORT", "3310");
        return "jdbc:mysql://localhost:" + port + "/ajp";
    }

    private static void printRow(String label, ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        System.out.println(label + ": id=" + id + ", name=" + name + ", age=" + age);
    }

    private static void ensureStudentsTableExists(Connection connection) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS students (" +
                            "id INT PRIMARY KEY, " +
                            "name VARCHAR(100) NOT NULL, " +
                            "age INT NOT NULL" +
                            ")");
        } catch (SQLException e) {
            System.out.println("Warning: could not create table 'students': " + e.getMessage());
        }
    }

    private static void ensureSampleData(Connection connection) {
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT COUNT(*) AS c FROM students")) {
            rs.next();
            int count = rs.getInt("c");
            if (count > 0)
                return;
        } catch (SQLException e) {
            System.out.println("Warning: could not check sample data: " + e.getMessage());
            return;
        }

        try (Statement st = connection.createStatement()) {
            st.executeUpdate("INSERT INTO students (id, name, age) VALUES (1, 'Krish', 21)");
            st.executeUpdate("INSERT INTO students (id, name, age) VALUES (2, 'Sita', 20)");
            st.executeUpdate("INSERT INTO students (id, name, age) VALUES (3, 'Ram', 22)");
        } catch (SQLException e) {
            System.out.println("Warning: could not insert sample data: " + e.getMessage());
        }
    }
}
