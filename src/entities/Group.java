package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davlet on 6/8/17.
 */
public class Group extends Entity implements Serializable {
    private Long groupNumber;
    private Integer courseNumber;
    private transient List<Student> studentList;
    private transient Semester semester;

    public Group(Long groupNumber, Integer courseNumber, Semester semester) {
        this.groupNumber = groupNumber;
        this.courseNumber = courseNumber;
        this.semester = semester;
        this.studentList = new ArrayList<>();
    }

    public Long getGroupNumber() {
        return groupNumber;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void setGroupNumber(Long groupNumber) {
        this.groupNumber = groupNumber;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
