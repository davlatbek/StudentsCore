package entities;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by davlet on 6/8/17.
 */
public class Lesson extends Entity implements Serializable {
    private String name;
    private Date startTime;
    private Date endTime;
    private String subject;
    private String description;
    private String lector;

    public Lesson(String name, Date startTime, Date endTime, String subject, String description, String lector) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.description = description;
        this.lector = lector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }
}
