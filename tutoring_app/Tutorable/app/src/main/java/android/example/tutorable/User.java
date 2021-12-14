package android.example.tutorable;

public class User {
    private String name;
    private String email;
    private String school;
    private boolean isTutor;

    public User() {
    }

    public User(String name, String email, String school, boolean isTutor) {
        this.name = name;
        this.email = email;
        this.school = school;
        this.isTutor = isTutor;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSchool() {
        return school;
    }

    public boolean isTutor() {
        return isTutor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void isTutor(boolean isTutor) {
        this.isTutor = isTutor;
    }

    public String toString() {
        return name + " | " + email + " | " + school + (isTutor ? " | Tutor" :
                " | Student");
    }
}
