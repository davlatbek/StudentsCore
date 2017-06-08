package appcore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CRUD OPERATIONS
 * Created by davlet on 6/8/17.
 */
public class ManagerStudent {
    List<Student> students = new ArrayList<>();

    public void create(Student student){
        students.add(student);
    }

    public List<Student> getAllStudents(){
        return students;
    }

    public void update(Student student){
        for (Student stud : students){
            if (student.getId().equals(stud.getId())){
                stud = student;
            }
        }
    }

    public void delete(Long idToDelete){
        for (Student student : students){
            if (student.getId() == idToDelete){
                students.remove(student);
            }
        }
    }

    public List<Student> filter(String name){
        return null;
    }

    public List<Student> filter(Date dateOfBirth){
        return null;
    }

    public List<Student> filter(Long groupId){
        return null;
    }

    public List<Student> filter(String name, Date dateOfBirth, Long groupId){
        return null;
    }
}
