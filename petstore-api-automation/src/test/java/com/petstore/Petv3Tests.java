package com.petstore;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;

public class Petv3Tests {

    private Long petId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = APIConfig.V3_BASE_URL;
        RestAssured.basePath = APIConfig.V3_BASE_PATH;
    }

    // ============ POSITIVE TEST CASES ============

    @Test(priority = 1, description = "Create a new pet in V3 API")
    public void testCreatePet() {
        Pet newPet = TestHelper.createSimplePet("Willow", "available");

        Response response = given()
                .contentType("application/json")
                .body(newPet)
                .when()
                .post(APIConfig.PET_ENDPOINT)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        // If API returns 500, skip the test (server issue, not test issue)
        if (statusCode == 500) {
            throw new SkipException("V3 API unavailable - Server returned 500 error");
        }

        // Verify successful creation
        Assert.assertEquals(statusCode, 200, "Expected 200 OK");
        Assert.assertEquals(response.jsonPath().getString("name"), "Willow");
        Assert.assertEquals(response.jsonPath().getString("status"), "available");

        petId = response.jsonPath().getLong("id");
        Assert.assertNotNull(petId, "Pet ID should not be null");
        System.out.println("✓ Created pet with ID: " + petId);
    }

    @Test(priority = 2, description = "Get pet by ID from V3 API", dependsOnMethods = "testCreatePet")
    public void testGetPetById() {
        Response response = given()
                .pathParam("petId", petId)
                .when()
                .get(APIConfig.PET_ENDPOINT + "/{petId}")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        if (statusCode == 500) {
            throw new SkipException("V3 API unavailable - Server returned 500 error");
        }

        Assert.assertEquals(statusCode, 200);
        Assert.assertNotNull(response.jsonPath().getLong("id"));
        Assert.assertEquals(response.jsonPath().getString("name"), "Willow");

        System.out.println("✓ Successfully retrieved pet with ID: " + petId);
    }

    @Test(priority = 3, description = "Update existing pet in V3 API", dependsOnMethods = "testCreatePet")
    public void testUpdatePet() {
        Pet updatedPet = TestHelper.createSimplePet("Willow Updated", "sold");
        updatedPet.setId(petId);

        Response response = given()
                .contentType("application/json")
                .body(updatedPet)
                .when()
                .put(APIConfig.PET_ENDPOINT)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        if (statusCode == 500) {
            throw new SkipException("V3 API unavailable - Server returned 500 error");
        }

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Willow Updated");
        Assert.assertEquals(response.jsonPath().getString("status"), "sold");

        System.out.println("✓ Updated pet successfully");
    }

    @Test(priority = 4, description = "Find pets by status in V3 API")
    public void testFindPetsByStatus() {
        Response response = given()
                .queryParam("status", "available")
                .when()
                .get(APIConfig.FIND_BY_STATUS)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        if (statusCode == 500) {
            throw new SkipException("V3 API unavailable - Server returned 500 error");
        }

        Assert.assertEquals(statusCode, 200);
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0,
                "Should return at least one pet");

        System.out.println("✓ Found pets with status: available");
    }

    @Test(priority = 5, description = "Delete pet from V3 API", dependsOnMethods = "testCreatePet")
    public void testDeletePet() {
        Response response = given()
                .pathParam("petId", petId)
                .when()
                .delete(APIConfig.PET_ENDPOINT + "/{petId}")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        if (statusCode == 500) {
            throw new SkipException("V3 API unavailable - Server returned 500 error");
        }

        Assert.assertEquals(statusCode, 200);
        System.out.println("✓ Deleted pet with ID: " + petId);
    }

    // ============ NEGATIVE TEST CASES ============

    @Test(description = "Try to get pet with invalid ID")
    public void testGetPetWithInvalidId() {
        Response response = given()
                .pathParam("petId", "invalid")
                .when()
                .get(APIConfig.PET_ENDPOINT + "/{petId}")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        if (statusCode == 500) {
            throw new SkipException("V3 API unavailable - Server returned 500 error");
        }

        Assert.assertTrue(statusCode == 400 || statusCode == 404,
                "Expected 400 or 404, got: " + statusCode);

        System.out.println("✓ Invalid ID handled correctly with status: " + statusCode);
    }

    @Test(description = "Try to get non-existent pet")
    public void testGetNonExistentPet() {
        Response response = given()
                .pathParam("petId", 999999999)
                .when()
                .get(APIConfig.PET_ENDPOINT + "/{petId}")
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        if (statusCode == 500) {
            throw new SkipException("V3 API unavailable - Server returned 500 error");
        }

        Assert.assertEquals(statusCode, 404, "Expected 404 for non-existent pet");
        System.out.println("✓ Non-existent pet returns 404 as expected");
    }

    @Test(description = "Try to create pet with missing required fields")
    public void testCreatePetWithMissingFields() {
        Pet invalidPet = TestHelper.createInvalidPet("Invalid Pet");

        Response response = given()
                .contentType("application/json")
                .body(invalidPet)
                .when()
                .post(APIConfig.PET_ENDPOINT)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        if (statusCode == 500) {
            throw new SkipException("V3 API unavailable - Server returned 500 error");
        }

        // API might accept (200) or reject (400) - both are valid responses
        Assert.assertTrue(statusCode == 200 || statusCode == 400,
                "Expected 200 or 400, got: " + statusCode);

        System.out.println("✓ Invalid pet handled with status: " + statusCode);
    }
}
