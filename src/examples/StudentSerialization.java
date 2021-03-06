package examples;

import entities.Group;
import entities.Semester;
import entities.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Serialize and des to file
 * Created by davlet on 6/8/17.
 */
public class StudentSerialization {
    public void serializeToFile(List<Student> students) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("./src/examples/students.out");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
        outputStream.writeObject(students);
        outputStream.flush();
        outputStream.close();
    }

    public List<Student> deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
        List<Student> students = (List<Student>) inputStream.readObject();
        return students;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        Group group = new Group(6L, 01, Semester.SPRING);
        students.add(new Student("Mark", "Brown", "Kidman",
                new Date(1994, 01, 01), 6L, group));
        students.add(new Student("Mark2", "Brown2", "Kidman2",
                new Date(1996, 02, 02), 5L, group));
        StudentSerialization studentSerialization = new StudentSerialization();
        studentSerialization.serializeToFile(students);
        List<Student> deserialized = studentSerialization.deserialize("./src/examples/students.out");
        System.out.println(deserialized.get(0).getName());
        System.out.println(deserialized.get(1).getName());
    }
}
