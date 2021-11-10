package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    public long id;
    public Category category = new Category();
    public String name;
    public List<String> photoUrls = new ArrayList<>();
    public List<Tag> tags = new ArrayList<>();
    public String status;


    public Pet() {
    }

    public Pet(String name, String status) {
        this.name = name;
        this.status = status;
    }
}
