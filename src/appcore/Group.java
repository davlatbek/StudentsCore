package appcore;

import java.util.List;

/**
 * Created by davlet on 6/8/17.
 */
public class Group {
    private Long id;
    private List<Student> studentList;
    private Integer courseNumber;
    private Semester semester;

    public Long getNumber() {
        return id;
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

    public void setNumber(Long number) {
        this.id = number;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
