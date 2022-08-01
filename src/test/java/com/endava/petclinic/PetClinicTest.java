package com.endava.petclinic;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetClinicTest {

    @Test
    public void getOwnerById() {
        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("ownerId", 1)
                .when()
                .get("/api/owners/{ownerId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(1))
                .body("firstName", is("George"))
                .body("firstName", containsString("org"))
                .body("lastName", startsWith("Fr"))
                .body("city", equalToIgnoringCase("madiSon"))
                .body("telephone", hasLength(10));
    }

    @Test
    public void postOwnersTest() {
        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org")
                .port(80)
                .basePath("/petclinic")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"address\": \"Bacului 11\",\n" +
                        "  \"city\": \"Bucharest\",\n" +
                        "  \"firstName\": \"Gabriela\",\n" +
                        "  \"id\": null,\n" +
                        "  \"lastName\": \"Radu\",\n" +
                        "  \"telephone\": \"1234567890\"\n" +
                        "}")
                .when().log().all()
                .post("/api/owners").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer ownerId = response.extract().jsonPath().getInt("id");

        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("ownerId", ownerId)
                .when()
                .get("/api/owners/{ownerId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(ownerId));

    }

    @Test
    public void postPetTest() {
        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org")
                .port(80)
                .basePath("/petclinic")
                .contentType(ContentType.JSON)
                .body(
                "{\n" +
                "  \"birthDate\": \"2022/02/20\",\n" +
                "  \"id\": null ,\n" +
                "  \"name\": \"Frisca\",\n" +
                "  \"owner\" : {\n" +
                        "   \"address\": \"Bacului 11\",\n" +
                        "   \"city\": \"Bucuresti\",\n" +
                        "   \"firstName\": \"Gabriela\",\n" +
                        "   \"id\": 108,\n" +
                        "   \"lastName\": \"Radu\",\n" +
                        "   \"pets\": [],\n" +
                        "   \"telephone\": \"1234567890\" \n" +
                        "},\n" +
                "  \"type\" : {\n" +
                        "   \"id\": 28,\n" +
                        "   \"name\": \"Bichon\" \n" +
                        "},\n" +
                "  \"visits\" :  []\n" +
                "}")
                .when().log().all()
                .post("/api/pets").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer petId = response.extract().jsonPath().getInt("id");

        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("petId", petId)
                .when()
                .get("/api/pets/{petId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(petId));
    }

    @Test
    public void getPetListTest() {
        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .when()
                .get("/api/pets")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void postVisitsTest() {
        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org")
                .port(80)
                .basePath("/petclinic")
                .contentType(ContentType.JSON)
                .body(
                "{\n" +
                    "  \"date\": \"2022/08/12\",\n" +
                    "  \"description\": \"vaxxin\",\n" +
                    "  \"id\": null ,\n" +
                    "  \"pet\": {\n" +
                        "  \"birthDate\": \"2022/02/20\",\n" +
                        "  \"id\": 119 ,\n" +
                        "  \"name\": \"Frisca\",\n" +
                        "  \"owner\" : {\n" +
                            "   \"address\": \"Bacului 11\",\n" +
                            "   \"city\": \"Bucuresti\",\n" +
                            "   \"firstName\": \"Gabriela\",\n" +
                            "   \"id\": 108,\n" +
                            "   \"lastName\": \"Radu\",\n" +
                            "   \"pets\": [],\n" +
                            "   \"telephone\": \"1234567890\" \n" +
                            "},\n" +
                        "  \"type\" : {\n" +
                            "   \"id\": 28,\n" +
                            "   \"name\": \"Bichon\" \n" +
                            "},\n" +
                        "  \"visits\" :  []\n" +
                        "}\n" +
                    "}")
                .when().log().all()
                .post("/api/visits").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer visitId = response.extract().jsonPath().getInt("id");

        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("visitId", visitId)
                .when()
                .get("/api/visits/{visitId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(visitId));
    }
}