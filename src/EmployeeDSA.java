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
            List<Employee> employees = fetchEmployees(conn, "SELECT * FROM employee_info WHERE emp_age > 30");
            // Convert salariesObj to List<Double>
//            List<Double> salaries = new ArrayList<>();
//            for (Object o : salariesObj) {
//                if (o instanceof Number) {
//                    salaries.add(((Number) o).doubleValue());
//                }
//            }
//            // Convert idsObj to List<Integer>
//            List<Integer> id = new ArrayList<>();
//            for (Object o : idsObj) {
//                if (o instanceof Number) {
//                    id.add(((Number) o).intValue());
//                }
//            }

//            System.out.println("Total salaries loaded: " + salaries.size());
//            id.forEach(System.out::println);

            // Measure execution time of sorting (DSA)
//            long start = System.nanoTime();
//            Collections.sort(salaries);
//            long end = System.nanoTime();

          //  System.out.println("Top 5 Salaries:");
            //for (int i = salaries.size() - 1; i >= salaries.size() - 5; i--) {
              //  System.out.println(salaries.get(i));
          //  }

          //  System.out.println("Execution Time (ms): " + (end - start) / 1_000_000);

            // Fetch all employees where age > 30
            System.out.println("Filtered Employee List:");
            //========================= DSA LOGIC STARTS HERE =========================


            employees.forEach(System.out::println);


            //========================= DSA LOGIC ENDS HERE =========================

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





    public static List<Employee> fetchEmployees(Connection conn, String query) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.emp_id = rs.getInt("emp_id");
                emp.emp_name = rs.getString("emp_name");
                emp.emp_age = rs.getInt("emp_age");
                emp.emp_salary = rs.getDouble("emp_salary");
                emp.emp_contact = rs.getString("emp_contact");
                emp.emp_address = rs.getString("emp_address");
                emp.emp_country = rs.getString("emp_country");
                employees.add(emp);
            }
        }
        return employees;
    }

    // POJO class to hold employee data
    public static class Employee {
        int emp_id;
        String emp_name;
        int emp_age;
        double emp_salary;
        String emp_contact;
        String emp_address;
        String emp_country;

        @Override
        public String toString() {
            return "Employee{" +
                    "emp_id=" + emp_id +
                    ", emp_name='" + emp_name + '\'' +
                    ", emp_age=" + emp_age +
                    ", emp_salary=" + emp_salary +
                    ", emp_contact='" + emp_contact + '\'' +
                    ", emp_address='" + emp_address + '\'' +
                    ", emp_country='" + emp_country + '\'' +
                    '}';
        }
    }
}
