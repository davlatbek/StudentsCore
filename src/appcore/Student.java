package appcore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by davlet on 6/8/17.
 */
public class Student {
    private String name;
    private String surname;
    private String middleName;
    private Date dateOfBirth;
    private Long id;
    private Long groupID;
    private List<Contact> contacts;

    public Student(String name, String surname, String middleName, Date dateOfBirth, Long groupID, List<Contact> contacts) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.groupID = groupID;
        this.id = System.currentTimeMillis();
        this.contacts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public Long getGroupID() {
        return groupID;
    }

    public void setGroupID(Long groupID) {
        this.groupID = groupID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Student)) return false;
        if (!(this.getId().equals(((Student) o).getId()))) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
