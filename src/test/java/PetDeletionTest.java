import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PetDeletionTest extends BaseTest {

    private static final String PET_ENDPOINT = "/pet";

    private long existingPetId;

    @BeforeEach
    public void createPetToDelete() {
        existingPetId = faker.number().numberBetween(3000000000000L, 9000000000000L);
        addNewPetToStore(existingPetId);
    }

    //flaky
    @Test
    public void shouldNotDeletePetTwice() {
        String targetPetPath = PET_ENDPOINT + "/" + existingPetId;

        request.when().delete(targetPetPath)
                .then().statusCode(200);

        request.when().delete(targetPetPath)
                .then().statusCode(404);
    }

    @ParameterizedTest(name = "should not delete pet using invalid id value: {0}")
    @ValueSource(strings = {"stringId", "0", "-1", "9223372036854775808", "0.3"})
    public void shouldGiveAppropriateErrorMessageWhenStringIdProvided(String wrongIdValue) {
        request.when().delete(PET_ENDPOINT + "/" + wrongIdValue)
                .then().statusCode(404);
    }

}
