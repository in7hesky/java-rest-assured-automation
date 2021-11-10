import entities.Pet;
import entities.Tag;

public class GeneratorDemo {
    public static void main(String[] args) {
        Pet petMin = PetDataGenerator.getPetRequestData(PetDataGenerator.DATA_SETS.MIN);
        Pet petAvg = PetDataGenerator.getPetRequestData(PetDataGenerator.DATA_SETS.AVERAGE);
        Pet petMax = PetDataGenerator.getPetRequestData(PetDataGenerator.DATA_SETS.MAX);

        System.out.println("--MIN--");
        displayPetFields(petMin);
        System.out.println("--AVERAGE--");
        displayPetFields(petAvg);
        System.out.println("--MAX--");
        displayPetFields(petMax);
    }

    private static void displayPetFields(Pet pet) {
        System.out.println("id: " + pet.id);
        System.out.println("petName: " + pet.name);
        System.out.println("status: " + pet.status);
        System.out.println("category: " + pet.category.id + " " + pet.category.name);
        System.out.println("photoUrls: " + pet.photoUrls);
        System.out.print("Tags: ");
        for (Tag tag: pet.tags) {
            System.out.print(tag.id + " " + tag.name + " ");
        }
        System.out.println("\n");
    }
}
