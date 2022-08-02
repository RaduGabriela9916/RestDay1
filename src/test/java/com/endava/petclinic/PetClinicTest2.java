package com.endava.petclinic;

import com.endava.petclinic.controllers.OwnerController;
import com.endava.petclinic.controllers.PetController;
import com.endava.petclinic.controllers.VisitsController;
import com.endava.petclinic.models.Owner;
import com.endava.petclinic.models.Pet;
import com.endava.petclinic.models.Visits;
import com.endava.petclinic.utils.EnvReader;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PetClinicTest2 {


    @Test
    public void postOwerTest(){
        HashMap<String,String> owner = new HashMap<>();
/*        owner.put("id" , null);
        owner.put("firstname", "George");
        owner.put("lastname", "Popescu");
        owner.put("address", "Tineretului");
        owner.put("city", "Bucuresti");
        owner.put("telephone", "1234567890");*/

        owner.put("address", "Tineretului");
        owner.put("city", "Bucuresti");
        owner.put("firstname", "George");
        owner.put("id" , null);
        owner.put("lastname", "Popescu");
        owner.put("telephone", "1234567890");

        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .contentType(ContentType.JSON)
                .body(owner).log().all()
                .when()
                .post("/api/owners").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer id = response.extract().jsonPath().getInt("id");

        given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("ownerId", id)
                .when()
                .post("/api/owners/{ownerId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(id));
    }

    @Test
    public void postOwnerTestWithObject(){
        Owner owner = OwnerController.genarateNewRandomOwner();

        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .contentType(ContentType.JSON)
                .when()
                .body(owner).log().all()
                .post("/api/owners")
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer ownerId = response.extract().jsonPath().getInt("id");

        ValidatableResponse getResponse = given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .contentType(ContentType.JSON)
                .pathParam("ownerId", ownerId)
                .when()
                .get("/api/owners/{ownerId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

        Owner ownerFromGetResponse = getResponse.extract().as( Owner.class );
        assertThat(ownerFromGetResponse, is( owner ));
    }

    @Test
    public void putOwnerTest(){
        Faker faker = new Faker();
        Owner owner = OwnerController.genarateNewRandomOwner();

        ValidatableResponse postResponse = given().baseUri( EnvReader.getBaseUri() )
                .basePath( EnvReader.getBasePath() )
                .port( EnvReader.getPort() )
                .contentType( ContentType.JSON )
                .body( owner )
                .when().log().all()
                .post( "/api/owners" )
                .then()
                .statusCode( HttpStatus.SC_CREATED );

        owner.setId( postResponse.extract().jsonPath().getInt( "id" ) );
        owner.setAddress( faker.address().streetAddress() );
        owner.setCity( faker.address().city() );
        owner.setTelephone( faker.number().digits( 10 ) );

        given().baseUri( EnvReader.getBaseUri() )
                .basePath( EnvReader.getBasePath() )
                .port( EnvReader.getPort() )
                .contentType( ContentType.JSON )
                .pathParam( "ownerId", owner.getId() )
                .body( owner ).log().all()
                .put("/api/owners/{ownerId}")
                .then()
                .statusCode( HttpStatus.SC_NO_CONTENT );

        ValidatableResponse getResponse = given().baseUri( EnvReader.getBaseUri() )
                .basePath( EnvReader.getBasePath() )
                .port( EnvReader.getPort() )
                .pathParam( "ownerId", owner.getId() )
                .get( "/api/owners/{ownerId}" ).prettyPeek()
                .then()
                .statusCode( HttpStatus.SC_OK );

        Owner actualOwner = getResponse.extract().as( Owner.class );

        assertThat( actualOwner, is( owner ) );
    }

    @Test
    public void postPetWithObjTest() {
        Pet pet = PetController.generateRandomPet();

        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org")
                .port(80)
                .basePath("/petclinic")
                .contentType(ContentType.JSON)
                .body(pet)
                .when().log().all()
                .post("/api/pets").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer petId = response.extract().jsonPath().getInt("id");

        ValidatableResponse getResponse = given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("petId", petId)
                .when()
                .get("/api/pets/{petId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(petId));

        Pet actualPet = getResponse.extract().as(Pet.class);

        assertThat(actualPet, is( pet ));
    }

    @Test
    public void getPetListTest() {
        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .when()
                .get("/api/pets")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
        Pet[] pets = response.extract().as(Pet[].class);
        assertThat(Arrays.stream(pets).findFirst().get().getClass(), is(Pet.class));
    }

    @Test
    public void postVisitsTest() {

        Visits visits = VisitsController.generateRandonVisit();

        /*ValidatableResponse petResponse = given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("petId", 123)
                .when()
                .get("/api/pets/{petId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(123));

        Pet pet = petResponse.extract().as(Pet.class);

        visits.setPet(pet);*/

        ValidatableResponse response = given().baseUri("http://api.petclinic.mywire.org")
                .port(80)
                .basePath("/petclinic")
                .contentType(ContentType.JSON)
                .body(visits)
                .when().log().all()
                .post("/api/visits").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);

        Integer visitId = response.extract().jsonPath().getInt("id");

        ValidatableResponse getResponse = given().baseUri("http://api.petclinic.mywire.org")
                .basePath("/petclinic")
                .port(80)
                .pathParam("visitId", visitId)
                .when()
                .get("/api/visits/{visitId}").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", is(visitId));

        Visits actualVisit = getResponse.extract().as(Visits.class);

        assertThat(actualVisit, is( visits ));
    }
}
