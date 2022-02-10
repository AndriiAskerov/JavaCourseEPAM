package ua.basics.lesson10;

import java.text.DecimalFormat;

public class User {
    private String name;
    private String post;
    private double salary;

    // CONSTRUCTORS --------------------------------------------------------------------------------
    public User(String name, String post, double salary) {
        setName(name);
        setPost(post);
        setSalary(salary);
    }

    // GETTERS & SETTERS ---------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // OTHER METHODS -------------------------------------------------------------------------------
    @Override
    public String toString() {
        return """
                \nUser name: %s
                Post     : %s
                Salary   : %s""".formatted(getName(), getPost(), getSalary());
    }
}