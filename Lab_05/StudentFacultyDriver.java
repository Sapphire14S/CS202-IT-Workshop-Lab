import java.util.Scanner;

// Faculty Class
class Faculty {
    String name;
    int empID;

    Faculty(String name, int empID) {
        this.name = name;
        this.empID = empID;
    }

    public String toString() {
        return "\n Faculty Name: " + name + "\n EmpID: " + empID;
    }
}

// Student class
class Student {
    String name;
    int rollNo;
    double cgpa;
    Faculty facultyAdvisor;
    String branch;
    static String collegeID;

    static {
        collegeID = "24b100";
    }

    Student(String name, int rollNo, double cgpa, Faculty facultyAdvisor, String branch) {
        this.name = name;
        this.rollNo = rollNo;
        this.cgpa = cgpa;
        this.facultyAdvisor = facultyAdvisor;
        this.branch = branch;
    }

    void updateStudentRecord(String name, double cgpa, String branch) {
        this.name = name;
        this.cgpa = cgpa;
        this.branch = branch;
    }

    void getStudentDetails() {
        System.out.println(" Roll No: " + rollNo + 
                           "\n Name: " + name + 
                           "\n CGPA: " + cgpa +
                           "\n Branch: " + branch + 
                           "\n CollegeID: " + collegeID);
    }

    Faculty getAdvisor() {
        return facultyAdvisor;
    }
}

public class StudentFacultyDriver {
    static Student[] students = new Student[5];
    static Faculty[] faculties = new Faculty[2];
    static int studentCount = 0, facultyCount = 0;

    private static void sortStudents() {
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = i + 1; j < studentCount; j++) {
                if (students[i].rollNo > students[j].rollNo) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
    }

    public static void addFaculty(Scanner sc) {
        if (facultyCount == 2) {
            System.out.println("Maximum faculty limit reached!");
            return;
        }
        sc.nextLine(); 
        System.out.print("Enter Faculty Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Faculty EmpID: ");
        int empID = sc.nextInt();
        faculties[facultyCount++] = new Faculty(name, empID);
        System.out.println("Faculty added.");
    }

    public static void deleteFaculty(Scanner sc) {
        if (facultyCount == 0) {
            System.out.println("No faculty to delete!");
            return;
        }
        System.out.print("Enter Faculty EmpID to delete: ");
        int empID = sc.nextInt();
        for (int i = 0; i < facultyCount; i++) {
            if (faculties[i].empID == empID) {
                faculties[i] = faculties[facultyCount - 1];
                faculties[facultyCount - 1] = null;
                facultyCount--;
                System.out.println("Faculty deleted.");
                return;
            }
        }
        System.out.println("Faculty not found.");
    }

    public static void addStudent(Scanner sc) {
        if (studentCount == 5) {
            System.out.println("Maximum student limit reached!");
            return;
        }
        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Roll No: ");
        int rollNo = sc.nextInt();
        System.out.print("Enter CGPA: ");
        double cgpa = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Branch: ");
        String branch = sc.nextLine();

        if (facultyCount == 0) {
            System.out.println("Add faculty first before assigning advisor.");
            return;
        }
        System.out.println("Available Faculty Advisors:");
        for (int i = 0; i < facultyCount; i++) {
            System.out.println(i + ": " + faculties[i]);
        }
        System.out.print("Choose advisor index: ");
        int fIndex = sc.nextInt();

        students[studentCount++] = new Student(name, rollNo, cgpa, faculties[fIndex], branch);

        sortStudents();
        System.out.println("Student added.");
    }

    public static void deleteStudent(Scanner sc) {
        if (studentCount == 0) {
            System.out.println("No student to delete!");
            return;
        }
        System.out.print("Enter Roll No to delete: ");
        int rollNo = sc.nextInt();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].rollNo == rollNo) {
                students[i] = students[studentCount - 1];
                students[studentCount - 1] = null;
                studentCount--;
                sortStudents();
                System.out.println("Student deleted.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void updateStudentRecord(Scanner sc) {
        System.out.print("Enter Roll No to update: ");
        int rollNo = sc.nextInt();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].rollNo == rollNo) {
                sc.nextLine();
                System.out.print("Enter new Name: ");
                String name = sc.nextLine();
                System.out.print("Enter new CGPA: ");
                double cgpa = sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter new Branch: ");
                String branch = sc.nextLine();
                students[i].updateStudentRecord(name, cgpa, branch);
                System.out.println("Record updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void getStudentDetails(Scanner sc) {
        System.out.print("Enter Roll No: ");
        int rollNo = sc.nextInt();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].rollNo == rollNo) {
                students[i].getStudentDetails();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void getAdvisorDetails(Scanner sc) {
        System.out.print("Enter Roll No: ");
        int rollNo = sc.nextInt();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].rollNo == rollNo) {
                System.out.println("Advisor: \n" + students[i].getAdvisor());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Add Faculty");
            System.out.println("4. Delete Faculty");
            System.out.println("5. Update Student Record");
            System.out.println("6. Get Student Details");
            System.out.println("7. Get Advisor Details");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent(sc); 
                case 2 -> deleteStudent(sc); 
                case 3 -> addFaculty(sc); 
                case 4 -> deleteFaculty(sc); 
                case 5 -> updateStudentRecord(sc); 
                case 6 -> getStudentDetails(sc); 
                case 7 -> getAdvisorDetails(sc); 
                case 0 -> System.out.println("Exiting..."); 
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
        sc.close();
    }
}
