package appcore;

import java.util.List;
import java.util.Map;

/**
 * Created by davlet on 6/8/17.
 */
public class AttendanceJournal {
    private Lesson lesson;
    private Long groupId;
    private List<Map<Student, Boolean>> attendance;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<Map<Student, Boolean>> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Map<Student, Boolean>> attendance) {
        this.attendance = attendance;
    }
}
