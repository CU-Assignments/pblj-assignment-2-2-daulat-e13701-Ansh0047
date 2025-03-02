import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', designation='" + designation + "', salary=" + salary + "}";
    }
}

public class EmployeeManagement {

    private static final String FILENAME = "employees.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    displayAllEmployees();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addEmployee(Scanner scanner) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME, true)) {
            @Override
            protected void writeStreamHeader() throws IOException {
                reset(); // Avoid header corruption
            }
        }) {
            System.out.print("Enter Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Designation: ");
            String designation = scanner.nextLine();

            System.out.print("Enter Salary: ");
            double salary = scanner.nextDouble();

            Employee employee = new Employee(id, name, designation, salary);
            oos.writeObject(employee);

            System.out.println("Employee added successfully.");
        } catch (IOException e) {
            System.err.println("Error while adding employee: " + e.getMessage());
        }
    }

    private static void displayAllEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            while (true) {
                try {
                    Employee employee = (Employee) ois.readObject();
                    System.out.println(employee);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No employees found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error while displaying employees: " + e.getMessage());
        }
    }
}
