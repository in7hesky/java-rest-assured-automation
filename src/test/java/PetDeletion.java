import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

public class PetDeletion extends BaseTest {

    private static final String PET_ENDPOINT = "/pet";

    private long existingPetId;

    @BeforeMethod
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

    @Test (dataProvider = "wrongIdFormatProvider")
    public void shouldGiveAppropriateErrorMessageWhenStringIdProvided(String wrongIdValue) {
        request.when().delete(PET_ENDPOINT + "/" + wrongIdValue)
                .then().statusCode(404);
    }


    @DataProvider (name = "wrongIdFormatProvider")
    protected Object[][] getWrongIdFormat () {
        return new Object[][] {{"stringId"}, {"0"}, {"-1"}, {"9223372036854775808"}, {"0.3"}};
    }
}
