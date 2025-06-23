
import java.sql.*;
import java.util.*;

public class EmployeeDSA {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/DSA";
        String user = "root";
        String password = "123qwerty";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            // Fetch emp_salary, emp_id, and emp_age columns using generic method
            List<Object> salariesObj = fetchColumnData(conn, "SELECT emp_salary FROM employee_info", "emp_salary");
            List<Object> idsObj = fetchColumnData(conn, "SELECT emp_id FROM employee_info", "emp_id");
            List<Object> agesObj = fetchColumnData(conn, "SELECT emp_age FROM employee_info", "emp_age");

            // Convert salariesObj to List<Double>
            List<Double> salaries = new ArrayList<>();
            for (Object o : salariesObj) {
                if (o instanceof Number) {
                    salaries.add(((Number) o).doubleValue());
                }
            }
            // Convert idsObj to List<Integer>
            List<Integer> id = new ArrayList<>();
            for (Object o : idsObj) {
                if (o instanceof Number) {
                    id.add(((Number) o).intValue());
                }
            }

            System.out.println("Total salaries loaded: " + salaries.size());
            id.forEach(System.out::println);

            // Measure execution time of sorting (DSA)
            long start = System.nanoTime();
            Collections.sort(salaries);
            long end = System.nanoTime();

            System.out.println("Top 5 Salaries:");
            for (int i = salaries.size() - 1; i >= salaries.size() - 5; i--) {
                System.out.println(salaries.get(i));
            }

            System.out.println("Execution Time (ms): " + (end - start) / 1_000_000);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Generic method to fetch a single column's data as a list of Objects
    public static List<Object> fetchColumnData(Connection conn, String query, String columnName) throws SQLException {
        List<Object> data = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                data.add(rs.getObject(columnName));
            }
        }
        return data;
    }
}
