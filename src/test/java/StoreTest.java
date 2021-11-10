import entities.Order;
import entities.Pet;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

public class StoreTest extends BaseTest{

    public static final String STORE_ENDPOINT = "/store";
    public static final String ORDER_ENDPOINT = STORE_ENDPOINT + "/order";

    @Test
    public void shouldFindPlacedOrderForValidPet() {
        Pet createdPet = addNewPetToStore(0);

        Order expectedOrder = addNewPetOrderToStore(createdPet.id, 1);
        Order actualOrder = request.get(ORDER_ENDPOINT + "/" + expectedOrder.id).as(Order.class);

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
        request.when().get(STORE_ENDPOINT + "/inventory")
                .then().statusCode(200)
                .and().body("available", Matchers.greaterThan(400));
    }

    private Order addNewPetOrderToStore(long petId, int quantity) {
        Order petOrder = new Order(petId, 1);

        Response response = request.when().body(petOrder).post(ORDER_ENDPOINT);
        response.then().statusCode(200);
        petOrder.id = response.body().jsonPath().getLong("id");
        System.out.println("order id " + petOrder.id);

        return petOrder;
    }
}
