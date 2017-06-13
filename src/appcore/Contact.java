package appcore;

/**
 * Created by davlet on 6/8/17.
 */
public class Contact {
    private ContactType type;
    private String value;

    public ContactType getType() {
        return type;
    }

    public Contact(ContactType type, String value) {
        this.type = type;
        this.value = value;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(this == o)) return false;
        if (this.type != ((Contact)o).getType()) return false;
        if (this.getValue() != ((Contact)o).getValue()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getValue() != null ? getValue().hashCode() : 0);
        return result;
    }
}
