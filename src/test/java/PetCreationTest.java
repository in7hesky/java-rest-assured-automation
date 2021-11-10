import entities.Pet;
import entities.Tag;
import org.testng.ITest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PetDataGenerator;
import utils.PetLimits;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;


public class PetCreationTest extends BaseTest implements ITest {
    private final ThreadLocal<String> testName = new ThreadLocal<>();

    @DataProvider(name = "petDP")
    public Object[][] getPetRequestData() {
        return new Object[][] {
                {PetDataGenerator.getPetRequestData(PetDataGenerator.DATA_SETS.MIN)},
                {PetDataGenerator.getPetRequestData(PetDataGenerator.DATA_SETS.AVERAGE)},
                {PetDataGenerator.getPetRequestData(PetDataGenerator.DATA_SETS.MAX)}
        };
    }

    @Test(dataProvider = "petDP")
    public void shouldCreatePetUsingEquivSetsOfData(Pet expectedPet) {
        request.body(expectedPet).post("/pet").then().statusCode(200);
        displayPetFields(expectedPet);
        Pet actualPet = request.get("/pet/" + expectedPet.id).body().as(Pet.class);

        assertThat(actualPet).usingRecursiveComparison().isEqualTo(expectedPet);
    }

    @BeforeMethod(alwaysRun = true)
    public void setTestName(Method method, Object[] row) {
        //You have the test data received through dataProvider delivered here in row
        Pet pet = (Pet) row[0];
        String dataSetMarker = "AverageDataSet";
        if (pet.id == 1) {
            dataSetMarker = "MinDataSet";
        } else if (pet.id == PetLimits.id.get("max")) {
            dataSetMarker = "MaxDataSet";
        }
        testName.set("shouldAddPetUsing" + dataSetMarker);
    }

    @Override
    public String getTestName() {
        return testName.get();
    }


    private void displayPetFields(Pet pet) {
        System.out.println("id: " + pet.id);
        System.out.println("petName: " + pet.name);
        System.out.println("status: " + pet.status);
        System.out.println("category: " + pet.category.id + " " + pet.category.name);
        System.out.println("photoUrls: " + pet.photoUrls);
        System.out.print("Tags: ");
        for (Tag tag: pet.tags) {
            System.out.print(tag.id + " " + tag.name + " ");
        }
    }
}
