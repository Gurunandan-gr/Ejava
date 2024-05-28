import java.io.*;

class Student implements Serializable {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

public class Prog29 {
    public static void main(String[] args) {
        // Serialization
        try {
            Student student = new Student("John", 20);

            // Writing object to file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.ser"));
            out.writeObject(student);
            out.close();

            System.out.println("Object serialized successfully.");
        } catch (IOException e) {
            System.out.println("Exception during serialization: " + e.getMessage());
        }

        // Deserialization
        try {
            // Reading object from file
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.ser"));
            Student student = (Student) in.readObject();
            in.close();

            System.out.println("Object deserialized successfully.");
            System.out.println("Deserialized Student: " + student);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Exception during deserialization: " + e.getMessage());
        }
    }
}
