package utils;

import entities.Category;
import entities.Pet;
import entities.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static utils.RandomString.getRandomAlphabeticString;

public class PetDataGenerator {

    public static Pet getPetRequestData (DATA_SETS dataOption) {
        Pet pet = new Pet();

        if (dataOption == DATA_SETS.AVERAGE) {
            pet.id = PetLimits.id.get("max") / 2;
            pet.name = getRandomAlphabeticString(PetLimits.name.get("max") / 2);
        } else {
            pet.id = PetLimits.id.get(dataOption.toString());
            pet.name = getRandomAlphabeticString(PetLimits.name.get(dataOption.toString()));
        }

        pet.category = buildCategory(dataOption);
        pet.photoUrls = buildPhotoUrls(dataOption);
        pet.tags = buildTags(dataOption);

        //common for all data sets
        pet.status = PetLimits.status[new Random().nextInt(PetLimits.status.length)];

        return pet;
    }

    public static List<Tag> buildTags(DATA_SETS dataOption) {
        List<Tag> tagList = new ArrayList<>();

        int tagCount, nameLength, tagId;

        tagCount = dataOption == DATA_SETS.AVERAGE ? PetLimits.tags.get("tagCount").get("max") / 2 :
                PetLimits.tags.get("tagCount").get(dataOption.toString());
        nameLength = dataOption == DATA_SETS.AVERAGE ? PetLimits.tags.get("name").get("max") / 2 :
                PetLimits.tags.get("name").get(dataOption.toString());
        tagId = dataOption == DATA_SETS.AVERAGE ? (PetLimits.tags.get("id").get("max") / 2) - (tagCount / 2):
                PetLimits.tags.get("id").get(dataOption.toString()) ;

        if (dataOption == DATA_SETS.MAX)
            tagId = (tagId + 1) - tagCount;

        for (int i = 0; i < tagCount; i++) {
            Tag tag = new Tag(tagId++, getRandomAlphabeticString(nameLength));
            tagList.add(tag);
        }

        return tagList;
    }

    public static List<String> buildPhotoUrls(DATA_SETS dataOption) {
        List<String> photoUrls = new ArrayList<>();
        int urlCount, urlLength;

        urlCount = dataOption == DATA_SETS.AVERAGE ? PetLimits.photoUrls.get("urlCount").get("max") / 2 :
                PetLimits.photoUrls.get("urlCount").get(dataOption.toString());
        urlLength = dataOption == DATA_SETS.AVERAGE ? PetLimits.photoUrls.get("length").get("max") / 2 :
                PetLimits.photoUrls.get("length").get(dataOption.toString());

        for (int i = 0; i < urlCount; i++)
            photoUrls.add(getRandomAlphabeticString(urlLength));

        return photoUrls;
    }

    public static Category buildCategory(DATA_SETS dataOption) {
        Category category = new Category();
        if (dataOption == DATA_SETS.AVERAGE) {
            category.id = PetLimits.category.get("id").get("max") / 2;
            category.name = getRandomAlphabeticString(PetLimits.category.get("name").get("max") / 2);
        } else {
            category.id = PetLimits.category.get("id").get(dataOption.toString());
            category.name = getRandomAlphabeticString(PetLimits.category.get("name").get(dataOption.toString()));
        }
        return category;
    }


    public enum DATA_SETS {
        MIN, AVERAGE, MAX;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }
}
