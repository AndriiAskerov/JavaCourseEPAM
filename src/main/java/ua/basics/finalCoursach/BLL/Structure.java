package ua.basics.finalCoursach.BLL;

import java.io.Serializable;

public class Structure implements Serializable {
    private String name;
    private String id;

    // CONSTRUCTORS --------------------------------------------------------------------------------
    public Structure(){}

    public Structure(String name, String id) {
        setName(name);
        setId(id);
    }

    // GETTERS & SETTERS ---------------------------------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
