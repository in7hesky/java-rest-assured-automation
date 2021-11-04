package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    public long id;
    public long petId;
    public int quantity;

    public Order() {

    }

    public Order(long petId, int quantity) {
        this.petId = petId;
        this.quantity = quantity;
    }
}
