// Person.java

import java.io.Serializable;

public class Person implements Serializable{
    private String name;
    private String email;
    private String phone;

    public Person() {
        this.name = "";
        this.email = "";
        this.phone = "";
    }

    public Person(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}