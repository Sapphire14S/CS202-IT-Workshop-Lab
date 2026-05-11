import java.util.*;
import java.util.regex.*;

// Custom Exception Classes
class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

class InvalidPINException extends Exception {
    public InvalidPINException(String message) {
        super(message);
    }
}

class InvalidRollNumberException extends Exception {
    public InvalidRollNumberException(String message) {
        super(message);
    }
}

class InvalidEmployeeIDException extends Exception {
    public InvalidEmployeeIDException(String message) {
        super(message);
    }
}

// Parent Class 
class Person {
    String firstName, lastName, PAN, addressPIN;

    public Person(String firstName, String lastName, String PAN, String addressPIN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.PAN = PAN;
        this.addressPIN = addressPIN;
    }

    // Validation for PIN
    public void validate() throws InvalidPINException, InvalidEmailException, InvalidRollNumberException, InvalidEmployeeIDException {
        if (!addressPIN.matches("\\d{6}")) {
            throw new InvalidPINException("PIN code must be a 6-digit number!");
        }
    }
}

// Derived Class: Student 
class Student extends Person {
    String emailID, rollNumber;

    public Student(String firstName, String lastName, String PAN, String addressPIN, String emailID, String rollNumber) {
        super(firstName, lastName, PAN, addressPIN);
        this.emailID = emailID;
        this.rollNumber = rollNumber;
    }

    @Override
    public void validate() throws InvalidPINException, InvalidEmailException, InvalidRollNumberException, InvalidEmployeeIDException {
        super.validate();  // Validate PIN

        // Validate email
        if (!isValidEmail(emailID)) {
            throw new InvalidEmailException("Invalid Email ID format!");
        }

        // Validate roll number
        if (!rollNumber.matches("stud\\d{5}")) {
            throw new InvalidRollNumberException("Roll number must start with 'stud' followed by 5 digits!");
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^(?=.*[!@#$&*])[A-Za-z0-9!@#$&*]+@(gmail\\.com|iiitg\\.ac\\.in)$";
        return Pattern.matches(regex, email);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | " + emailID + " | " + rollNumber;
    }
}

// Derived Class: Employee 
class Employee extends Person {
    String emailID, employeeID;

    public Employee(String firstName, String lastName, String PAN, String addressPIN, String emailID, String employeeID) {
        super(firstName, lastName, PAN, addressPIN);
        this.emailID = emailID;
        this.employeeID = employeeID;
    }

    @Override
    public void validate() throws InvalidPINException, InvalidEmailException, InvalidEmployeeIDException, InvalidRollNumberException {
        super.validate();  // Validate PIN

        // Validate email
        if (!isValidEmail(emailID)) {
            throw new InvalidEmailException("Invalid Email ID format!");
        }

        // Validate employee ID
        if (!employeeID.matches("emp\\d{3}")) {
            throw new InvalidEmployeeIDException("Employee ID must start with 'emp' followed by 3 digits!");
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^(?=.*[!@#$&*])[A-Za-z0-9!@#$&*]+@(gmail\\.com|iiitg\\.ac\\.in)$";
        return Pattern.matches(regex, email);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | " + emailID + " | " + employeeID;
    }
}

// Main Class 
public class InformationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();

        System.out.print("How many students do you want to add? ");
        int sCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < sCount; i++) {
            System.out.println("\nEnter Student " + (i + 1) + " details:");
            System.out.print("First Name: "); String fn = sc.nextLine();
            System.out.print("Last Name: "); String ln = sc.nextLine();
            System.out.print("PAN: "); String pan = sc.nextLine();
            System.out.print("Address PIN: "); String pin = sc.nextLine();
            System.out.print("Email ID: "); String email = sc.nextLine();
            System.out.print("Roll Number: "); String roll = sc.nextLine();

            Student st = new Student(fn, ln, pan, pin, email, roll);
            try {
                st.validate();
                students.add(st);
                System.out.println("Student added successfully!");
            } catch (Exception ex) {
                System.out.println("Validation Error: " + ex.getMessage());
            }
        }

        System.out.print("\nHow many employees do you want to add? ");
        int eCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < eCount; i++) {
            System.out.println("\nEnter Employee " + (i + 1) + " details:");
            System.out.print("First Name: "); String fn = sc.nextLine();
            System.out.print("Last Name: "); String ln = sc.nextLine();
            System.out.print("PAN: "); String pan = sc.nextLine();
            System.out.print("Address PIN: "); String pin = sc.nextLine();
            System.out.print("Email ID: "); String email = sc.nextLine();
            System.out.print("Employee ID: "); String empid = sc.nextLine();

            Employee emp = new Employee(fn, ln, pan, pin, email, empid);
            try {
                emp.validate();
                employees.add(emp);
                System.out.println("Employee added successfully!");
            } catch (Exception ex) {
                System.out.println("Validation Error: " + ex.getMessage());
            }
        }

        // Display all valid entries
        System.out.println("\n------------------------------");
        System.out.println("Valid Students:");
        for (Student s : students)
            System.out.println(s);

        System.out.println("\nValid Employees:");
        for (Employee e : employees)
            System.out.println(e);

        sc.close();
    }
}
