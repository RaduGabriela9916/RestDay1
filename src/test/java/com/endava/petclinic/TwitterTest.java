package com.endava.petclinic;

import com.endava.petclinic.utils.EnvReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class TwitterTest {

    @Test
    public void postTweet(){
        Map<String, String> status = new HashMap<>();
        status.put("status", "Hallo Welt aus Deutschland!");
        given().baseUri(EnvReader.getTwitterUri())
                .basePath(EnvReader.getTwitterPath())
                .auth()
                .oauth(EnvReader.getApiKey(), EnvReader.getApiKeySecret(), EnvReader.getAccessToken(), EnvReader.getAccessTokenSecret())
                .contentType(ContentType.JSON)
                .queryParams(status)
                .post("/statuses/update.json").prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
