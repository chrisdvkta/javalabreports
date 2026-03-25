import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Lab3Q02UpdatableResultSet {
    public static void main(String[] args) {
        String url = args.length > 0 ? args[0] : System.getenv().getOrDefault("DB_URL", defaultUrl());
        String user = args.length > 1 ? args[1] : System.getenv().getOrDefault("DB_USER", "ajp");
        String pass = args.length > 2 ? args[2] : System.getenv().getOrDefault("DB_PASS", "ajp");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
            System.out.println("MySQL JDBC driver not found on classpath.");
            System.out.println("Tip: Add MySQL Connector/J (mysql-connector-j) to your classpath.");
        }

        try (Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE)) {

            System.out.println("Connected to database.");
            ensureStudentsTableExists(connection);
            ensureSampleData(connection);

            try (ResultSet rs = statement.executeQuery("SELECT id, name, age FROM students ORDER BY id")) {
                // Update first row
                if (rs.first()) {
                    String oldName = rs.getString("name");
                    rs.updateString("name", oldName + " (Updated)");
                    rs.updateRow();
                    System.out.println("Updated first row name: " + oldName + " -> " + rs.getString("name"));
                } else {
                    System.out.println("No rows found to update.");
                }

                // Insert a new row (pick an id that doesn't exist)
                int newId = nextAvailableId(connection);
                rs.moveToInsertRow();
                rs.updateInt("id", newId);
                rs.updateString("name", "New Student");
                rs.updateInt("age", 19);
                rs.insertRow();
                rs.moveToCurrentRow();
                System.out.println("Inserted new row with id=" + newId);

                // Delete last row (demo)
                if (rs.last()) {
                    int deletedId = rs.getInt("id");
                    rs.deleteRow();
                    System.out.println("Deleted last row with id=" + deletedId);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            if (e.getMessage() != null && e.getMessage().contains("No suitable driver")) {
                System.out.println("Tip: This usually means the JDBC driver jar is missing.");
            }
            System.out.println("Tip: Provide DB args: java Lab3Q02UpdatableResultSet <url> <user> <pass>");
        }

        System.out.println("done by Krish Devkota");
    }

    private static String defaultUrl() {
        String port = System.getenv().getOrDefault("AJP_MYSQL_PORT", "3310");
        return "jdbc:mysql://localhost:" + port + "/ajp";
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

    private static int nextAvailableId(Connection connection) {
        try (Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT MAX(id) AS m FROM students")) {
            if (rs.next()) {
                return rs.getInt("m") + 1;
            }
        } catch (SQLException e) {
            System.out.println("Warning: could not compute next id: " + e.getMessage());
        }
        return 100;
    }
}
