import entities.Pet;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PetCreationTest extends BaseTest {

    @ParameterizedTest (name = "create pet using {0} data set")
    @EnumSource(PetDataGenerator.DATA_SETS.class)
    public void shouldCreatePetUsingEquivSetsOfData(PetDataGenerator.DATA_SETS dataOption) {
        Pet expectedPet = PetDataGenerator.getPetRequestData(dataOption);

        request.body(expectedPet).post("/pet").then().statusCode(200);
        Pet actualPet = request.get("/pet/" + expectedPet.id).body().as(Pet.class);

        assertThat(actualPet).usingRecursiveComparison().isEqualTo(expectedPet);
    }
}
