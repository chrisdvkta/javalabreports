import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Unit1Q12ComparableDemo {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(3, "Krish", 55000));
        employees.add(new Employee(1, "Sita", 65000));
        employees.add(new Employee(2, "Ram", 45000));

        Collections.sort(employees);
        for (Employee e : employees) {
            System.out.println(e);
        }

        System.out.println("done by Krish Devkota");
    }

    static class Employee implements Comparable<Employee> {
        private final int id;
        private final String name;
        private final double salary;

        Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        @Override
        public int compareTo(Employee other) {
            return Double.compare(this.salary, other.salary);
        }

        @Override
        public String toString() {
            return "Employee{id=" + id + ", name='" + name + "', salary=" + salary + "}";
        }
    }
}

