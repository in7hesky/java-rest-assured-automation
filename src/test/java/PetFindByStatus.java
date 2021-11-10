import org.testng.annotations.Test;

public class PetFindByStatus extends BaseTest {

    //always fails
    @Test
    public void shouldReactToInvalidStatusValue() {
        String invalidPetStatus = "invalidPetStatus";

        request.when().get("/pet/findByStatus?status=" + invalidPetStatus)
                .then().statusCode(400);
    }

}
