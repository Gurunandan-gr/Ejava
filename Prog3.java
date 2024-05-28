class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("Name: " + name);
    }
}

class Student extends Person {
    int rollNumber;

    public Student(String name, int rollNumber) {
        super(name);
        this.rollNumber = rollNumber;
    }

    public void displayRollNumber() {
        System.out.println("Roll Number: " + rollNumber);
    }
}

class CollegeStudent extends Student {
    String collegeName;

    public CollegeStudent(String name, int rollNumber, String collegeName) {
        super(name, rollNumber);
        this.collegeName = collegeName;
    }

    public void displayCollegeName() {
        System.out.println("College Name: " + collegeName);
    }
}

class SchoolStudent extends Student {
    String schoolName;

    public SchoolStudent(String name, int rollNumber, String schoolName) {
        super(name, rollNumber);
        this.schoolName = schoolName;
    }

    public void displaySchoolName() {
        System.out.println("School Name: " + schoolName);
    }
}

public class Prog3 {
    public static void main(String[] args) {
        CollegeStudent collegeStudent = new CollegeStudent("Manish", 73, "NMAMIT,NITTE");
        SchoolStudent schoolStudent = new SchoolStudent("Man", 6, "Sharada");

        System.out.println("---College Student Details---");
        collegeStudent.display();
        collegeStudent.displayRollNumber();
        collegeStudent.displayCollegeName();

        System.out.println("---School Student Details---");
        schoolStudent.display();
        schoolStudent.displayRollNumber();
        schoolStudent.displaySchoolName();
    }
}
