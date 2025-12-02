package org.basic;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class BasicAPI {
	
	
	public void debugReqres() {
	    Response response = given()
	            .baseUri("https://reqres.in")
	            .log().all()            // Log the request
	        .when()
	            .get("/api/users/2")
	            .then()
	            .log().all()            // Log the response
	            .extract().response();

	    System.out.println("Status: " + response.statusCode());
	}
	
	// @Test
    public void createProductTest() {
        String requestBody = "{\n" +
                "  \"title\": \"Test Product\",\n" +
                "  \"description\": \"A sample product for automation\",\n" +
                "  \"price\": 99.99,\n" +
                "  \"brand\": \"QA Brand\",\n" +
                "  \"category\": \"electronics\"\n" +
                "}";

        given()
            .baseUri("https://dummyjson.com")
            .header("Content-Type", "application/json")
            .body(requestBody)
            .log().all()
        .when()
            .post("/products/add")
        .then()
            .log().all()
            .statusCode(201)
            .body("title", equalTo("Test Product"))
            .body("brand", equalTo("QA Brand"));
    }
	
	
	@Test
	public void getProductTest() {
	    given()
	        .baseUri("https://dummyjson.com")
	        .header("Accept", "application/json")
	        .log().all()
	    .when()
	        .get("/products/1")
	    .then()
	        .log().all()
	        .statusCode(200)
	        .body("id", equalTo(1))
	        .body("title", notNullValue());
	}

}

