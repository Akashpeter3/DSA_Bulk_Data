
import java.sql.*;
import java.util.*;

public class EmployeeDSA {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/DSA";
        String user = "root";
        String password = "123qwerty";

        List<Double> salaries = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT emp_salary FROM employee_info";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                salaries.add(rs.getDouble("emp_salary"));
            }

            System.out.println("Total salaries loaded: " + salaries.size());

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
}
