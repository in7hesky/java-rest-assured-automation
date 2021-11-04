import entities.Order;
import entities.Pet;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class StoreTest extends BaseTest{
    @Test
    public void shouldFindPlacedOrderForValidPet() {
        Pet createdPet = addNewPetToStore();

        Order expectedOrder = addNewPetOrderToStore(createdPet.id, 1);
        Order actualOrder = request.get("/store/order/" + expectedOrder.id).as(Order.class);

        assertThat(actualOrder).usingRecursiveComparison().isEqualTo(expectedOrder);
    }

    //won't pass
    @Test
    public void shouldNotPlaceOrderForNonExistentPet() {
        long petId = faker.number().randomNumber();

        System.out.println(petId);
        request.when().delete("/pet/" + petId)
                .then().statusCode(anyOf(is(200), is(404)));

        boolean orderCreationIsFailed = false;
        try {
            addNewPetOrderToStore(petId, 1);
        } catch (AssertionError e) {
            orderCreationIsFailed = true;
        }

        assertThat(orderCreationIsFailed).isTrue();
    }

    @Test
    public void shouldGetEnoughAvailablePetsCount() {
        request.when().get("/store/inventory")
                .then().statusCode(200)
                .and().body("available", Matchers.greaterThan(400));
    }



    private Order addNewPetOrderToStore(long petId, int quantity) {
        Order petOrder = new Order(petId, 1);

        Response response = request.when().body(petOrder).post("/store/order");
        response.then().statusCode(200);
        petOrder.id = response.body().jsonPath().getLong("id");
        System.out.println("order id " + petOrder.id);

        return petOrder;
    }
}
