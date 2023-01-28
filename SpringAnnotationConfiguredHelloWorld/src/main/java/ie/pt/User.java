package ie.pt;

import java.util.Objects;

/**
 * User pojo mapping the fields in the users table in the database
 */
public class User {

    private int id;
    private String name;
    private String email;
    private boolean active;

    public User() {
    }

    public User(String name, String email, boolean active) {

        this(-1, name, email, active);
    }

    public User(int id, String name, String email, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
    }

    public User(User userToModify) {
        this(userToModify.getId(),
            userToModify.getName(),
            userToModify.getEmail(),
            userToModify.isActive());
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && active == user.active && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, active);
    }

    public void display() {
        System.out.println(this);
    }
}
