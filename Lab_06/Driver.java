import java.util.*;

// Person Class
class Person {
    protected String name;
    protected int age;

    public Person() {}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void getDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

// Faculty extends Person
class Faculty extends Person {
    private int empID;

    public Faculty(String name, int age, int empID) {
        super(name, age);
        this.empID = empID;
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Employee ID: " + empID);
    }

    @Override
    public String toString() {
        return "Faculty Name: " + name + ", EmpID: " + empID;
    }

    public int getEmpID() {
        return empID;
    }
}

// Student extends Person
class Student extends Person {
    private int rollNo;
    private double cgpa;
    private Faculty facultyAdvisor;
    private String branch;
    private String collegeID;

    public Student() {}

    public Student(String name, int age, int rollNo, double cgpa,
                   String branch, String collegeID, Faculty facultyAdvisor) {
        super(name, age);
        this.rollNo = rollNo;
        this.cgpa = cgpa;
        this.branch = branch;
        this.collegeID = collegeID;
        this.facultyAdvisor = facultyAdvisor;
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Roll No: " + rollNo);
        System.out.println("CGPA: " + cgpa);
        System.out.println("Branch: " + branch);
        System.out.println("College ID: " + collegeID);
        if (facultyAdvisor != null) {
            System.out.println("Faculty Advisor: " + facultyAdvisor.toString());
        } else {
            System.out.println("No faculty advisor assigned.");
        }
    }

    public Faculty getAdvisor() {
        return facultyAdvisor;
    }

    public void setAdvisor(Faculty advisor) {
        this.facultyAdvisor = advisor;
    }

    public int getRollNo() {
        return rollNo;
    }
}

// Driver class with menu
public class Driver {
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Faculty> faculties = new ArrayList<>();
    static ArrayList<Person> persons = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = -1;
        do {
            System.out.println("\n> Menu:");
            System.out.println("> 1. Add Student");
            System.out.println("> 2. Delete Student");
            System.out.println("> 3. Add Faculty");
            System.out.println("> 4. Delete Faculty");
            System.out.println("> 5. Add Person");
            System.out.println("> 6. Delete Person");
            System.out.println("> 7. Get Student Details");
            System.out.println("> 8. Get Advisor Details");
            System.out.println("> 9. Get Person Details");
            System.out.println("> 0. Exit");
            System.out.print("Enter choice: ");
            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.out.println("Please enter a valid number.");
                continue;
            }
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1: addStudent(); break;
                case 2: deleteStudent(); break;
                case 3: addFaculty(); break;
                case 4: deleteFaculty(); break;
                case 5: addPerson(); break;
                case 6: deletePerson(); break;
                case 7: getStudentDetails(); break;
                case 8: getAdvisorDetails(); break;
                case 9: getPersonDetails(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    static void addStudent() {
        if (students.size() >= 5) {
            System.out.println("Max students reached.");
            return;
        }
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Age: "); int age = readInt();
        System.out.print("Roll No: "); int rollNo = readInt();
        
        for (Student s : students) if (s.getRollNo() == rollNo) {
            System.out.println("A student with that roll number already exists.");
            return;
        }
        System.out.print("CGPA: "); double cgpa = readDouble();
        sc.nextLine(); 
        System.out.print("Branch: "); String branch = sc.nextLine();
        System.out.print("College ID: "); String collegeID = sc.nextLine();

        Faculty advisor = null;
        if (!faculties.isEmpty()) {
            System.out.print("Enter EmpID of faculty advisor (or -1 for none): ");
            int emp = readInt();
            if (emp != -1) {
                for (Faculty f : faculties) {
                    if (f.getEmpID() == emp) { advisor = f; break; }
                }
                if (advisor == null) System.out.println("Faculty with that empID not found. Advisor set to none.");
            }
        } else {
            System.out.println("No faculties available; advisor set to none.");
        }

        Student s = new Student(name, age, rollNo, cgpa, branch, collegeID, advisor);
        students.add(s);
        persons.add(s);
        System.out.println("Student added.");
    }

    static void deleteStudent() {
        System.out.print("Enter Roll No of student to delete: ");
        int roll = readInt();
        Student toRemove = null;
        for (Student s : students) {
            if (s.getRollNo() == roll) { toRemove = s; break; }
        }
        if (toRemove != null) {
            students.remove(toRemove);
            persons.remove(toRemove);
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    static void addFaculty() {
        if (faculties.size() >= 2) {
            System.out.println("Max faculties reached.");
            return;
        }
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Age: "); int age = readInt();
        System.out.print("EmpID: "); int empID = readInt();
        
        for (Faculty f : faculties) if (f.getEmpID() == empID) {
            System.out.println("A faculty with that EmpID already exists.");
            return;
        }
        Faculty f = new Faculty(name, age, empID);
        faculties.add(f);
        persons.add(f);
        System.out.println("Faculty added.");
    }

    static void deleteFaculty() {
        System.out.print("Enter EmpID of faculty to delete: ");
        int emp = readInt();
        Faculty toRemove = null;
        for (Faculty f : faculties) {
            if (f.getEmpID() == emp) { toRemove = f; break; }
        }
        if (toRemove != null) {
            
            for (Student s : students) {
                if (s.getAdvisor() != null && s.getAdvisor().getEmpID() == emp) {
                    s.setAdvisor(null);
                }
            }
            faculties.remove(toRemove);
            persons.remove(toRemove);
            System.out.println("Faculty deleted and unlinked from any students.");
        } else {
            System.out.println("Faculty not found.");
        }
    }

    static void addPerson() {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Age: "); int age = readInt();
        Person p = new Person(name, age);
        persons.add(p);
        System.out.println("Person added.");
    }

    static void deletePerson() {
        System.out.print("Enter name of person to delete: ");
        String name = sc.nextLine();
        Person toRemove = null;
        for (Person p : persons) {
            if (p.name.equals(name)) { toRemove = p; break; }
        }
        if (toRemove != null) {
            
            if (toRemove instanceof Faculty) {
                int empId = ((Faculty) toRemove).getEmpID();
                
                for (Student s : students) {
                    if (s.getAdvisor() != null && s.getAdvisor().getEmpID() == empId) {
                        s.setAdvisor(null);
                    }
                }
                faculties.remove(toRemove);
            }
            
            if (toRemove instanceof Student) {
                students.remove(toRemove);
            }
            persons.remove(toRemove);
            System.out.println("Person deleted and relationships cleaned.");
        } else {
            System.out.println("Person not found.");
        }
    }

    static void getStudentDetails() {
        if (students.isEmpty()) {
            System.out.println("No students to show.");
            return;
        }
        for (Student s : students) {
            s.getDetails();
            System.out.println("----------------");
        }
    }

    static void getAdvisorDetails() {
        System.out.print("Enter Roll No of student: ");
        int roll = readInt();
        for (Student s : students) {
            if (s.getRollNo() == roll) {
                Faculty adv = s.getAdvisor();
                if (adv != null) adv.getDetails();
                else System.out.println("No advisor assigned (advisor is null or was deleted).");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    static void getPersonDetails() {
        if (persons.isEmpty()) {
            System.out.println("No persons to show.");
            return;
        }
        for (Person p : persons) {
            p.getDetails();
            System.out.println("----------------");
        }
    }

    
    static int readInt() {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Please enter a valid integer: ");
        }
        int v = sc.nextInt();
        sc.nextLine(); 
        return v;
    }

    static double readDouble() {
        while (!sc.hasNextDouble()) {
            sc.nextLine();
            System.out.print("Please enter a valid number: ");
        }
        double v = sc.nextDouble();
        return v;
    }
}
