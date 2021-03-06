import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class PetFindByStatusTest extends BaseTest {


    @Test
    @Description("Always fails due to feature implementation in SUT")
    public void shouldReactToInvalidPetStatusValue() {
        String invalidPetStatus = "invalidPetStatus";

        request.when().get("/pet/findByStatus?status=" + invalidPetStatus)
                .then().statusCode(400);
    }

    @Test
    public void shouldGetAvailablePetsInTime() {
        request.when().get("/pet/findByStatus?status=available")
                .then().statusCode(200)
                .and().time(Matchers.lessThan(5500L), TimeUnit.MILLISECONDS);
    }

}
