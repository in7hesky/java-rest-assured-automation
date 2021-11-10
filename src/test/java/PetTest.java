import entities.Pet;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PetTest extends BaseTest {

    public static final String PET_ENDPOINT = "/pet";

    @Test
    public void shouldAddNewPet() {
        Pet expectedPet = addNewPetToStore(0);

        Pet actualPet = request.get(
                PET_ENDPOINT + "/" + expectedPet.id).as(Pet.class);

        assertThat(actualPet).usingRecursiveComparison()
                .isEqualTo(expectedPet);
    }

    @Test
    public void shouldUpdatePetByPut() {
        String newStatus = "unavailable";
        Pet createdPet = addNewPetToStore(0);

        createdPet.status = newStatus;
        request.when().body(createdPet).put(PET_ENDPOINT)
                .then().statusCode(200);

        request.when().get(PET_ENDPOINT + "/" + createdPet.id)
                .then().statusCode(200)
                        .and().body("status", equalTo(newStatus));
    }


    @Test
    public void shouldDeleteCreatedPet() {
        Pet createdPet = addNewPetToStore(0);

        request.when().delete(PET_ENDPOINT + "/" + createdPet.id)
                .then().statusCode(200);

        request.when().get( PET_ENDPOINT + "/" + createdPet.id)
                .then().statusCode(404);
    }

    @Test
    public void shouldGetListOfAvailablePets() {
        request.when().get(PET_ENDPOINT + "/findByStatus?status=available")
                .then().statusCode(200);
    }

    @Test
    public void shouldGetAvailablePetsInTime() {
        request.when().get(PET_ENDPOINT + "/findByStatus?status=available")
                .then().statusCode(200)
                .and().time(Matchers.lessThan(5500L), TimeUnit.MILLISECONDS);
    }
}
