package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    public String name;
    public String status;
    public long id;

    public Pet() {

    }

    public Pet(String name, String status) {
        this.name = name;
        this.status = status;
    }
}
