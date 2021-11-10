import com.github.javafaker.Faker;
import entities.Pet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.io.IOException;
import java.util.Properties;

@Execution(ExecutionMode.CONCURRENT)
public class BaseTest {
    public final RequestSpecification request;
    public final Faker faker = new Faker();

    public BaseTest() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            RestAssured.baseURI = props.getProperty("api.uri");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        request = RestAssured.given().contentType(ContentType.JSON);
    }

    /**
     *
     * @param petId any from 1 to 9223372036854775807 is appropriate, zero for random id
     */
    protected Pet addNewPetToStore(long petId) {
        Pet newPet = getRandomPet();
        if (petId > 0 && petId <= 9223372036854775807L)
            newPet.id = petId;

        Response response = request.body(newPet).post("/pet");
        response.then().statusCode(200);

        newPet.id = response.body().jsonPath().getLong("id");
        return newPet;
    }

    protected Pet getRandomPet() {
        String petName = faker.name().firstName();
        return new Pet(petName, "available");
    }
}
