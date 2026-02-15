package com.petstore;

import java.util.Arrays;

public class TestHelper {
    // Create a simple pet for testing
    public static Pet createSimplePet(String name, String status) {
        Pet pet = new Pet();
        pet.setId(System.currentTimeMillis());
        pet.setName(name);
        pet.setStatus(status);
        pet.setPhotoUrls(Arrays.asList("photo1.jpg"));
        return pet;
    }

    // Create pet with only name (missing required fields)
    public static Pet createInvalidPet(String name) {
        Pet pet = new Pet();
        pet.setName(name);
        return pet;
    }
}
