import com.github.javafaker.Faker;
import entities.Pet;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Properties;

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

    protected Pet addNewPetToStore() {
        Pet newPet = getRandomPet();

        Response response = request.body(newPet).post("/pet");
        response.then().statusCode(200);

        newPet.id = response.body().jsonPath().getLong("id");
        System.out.println(newPet.id);
        return newPet;
    }

    protected Pet getRandomPet() {
        String petName = faker.name().firstName();
        System.out.println(petName);
        return new Pet(petName, "available");
    }
}
