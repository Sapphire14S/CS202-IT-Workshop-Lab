// import java.util.*;

class Faculty {
    String name;
    int empID;

    Faculty(String name, int empID) {
        this.name = name;
        this.empID = empID;
    }

    Faculty (Faculty f) {
        this.name = f.name;
        this.empID = f.empID;
    }
    public String toString() {
        return "Faculty Name: " + name + "\nEmpID: " + empID;
    }
}

class Student {
    String name;
    int rollNo;
    double cgpa;
    Faculty facultyAdvisor;
    String branch;
    String collegeID;

    Student(String name, int rollNo, double cgpa, Faculty facultyAdvisor, String branch, String collegeID) {
        this.name = name;
        this.rollNo = rollNo;
        this.cgpa = cgpa;
        this.facultyAdvisor = facultyAdvisor;
        this.branch = branch;
        this.collegeID = collegeID;
    }

    Student(Student s) {
        this.name = s.name;
        this.rollNo = s.rollNo;
        this.cgpa = s.cgpa;
        this.facultyAdvisor = s.facultyAdvisor; 
        this.branch = s.branch;
        this.collegeID = s.collegeID;
    }

    public void getStudentDetails() {
        System.out.println ("Name: " + name + 
                            "\nRoll No: " + rollNo +
                            "\nCGPA: " + cgpa + 
                            "\nBranch: " + branch + 
                            "\nCollegeID: " + collegeID);
        System.out.println ("Faculty Advisor: " + facultyAdvisor);
    }
}

public class ShallowCopy {
    public static void main(String[] args) {
        System.out.println("\n>>______SHALLOW COPY____________________________\n");

        Faculty f1 = new Faculty("Dr. Aman Sharma", 101);
        Student s1 = new Student("Anurag Ahuja", 45, 9.3, f1, "CSE", "IIITG2025");

        // Create Shallow Copy
        Student s2 = new Student(s1);

        System.out.println(">> Before change in Faculty:");
        System.out.println("Student 1:");
        s1.getStudentDetails();
        System.out.println("\n");
        System.out.println("Student 2:");
        s2.getStudentDetails();

        // Modifying Faculty object 
        f1.name = "Dr. Viraj Verma";
        f1.empID = 202;

        System.out.println("\n>>_____________________________________________\n");

        System.out.println("\n>> After change in Faculty (Shallow Copy):");
        System.out.println("Student 1:");
        s1.getStudentDetails();
        System.out.println("\n");
        System.out.println("Student 2:");
        s2.getStudentDetails();

        System.out.println("\n>>_____________________________________________\n");
    }
}