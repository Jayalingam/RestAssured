package org.basic;

import io.qameta.allure.testng.AllureTestNg;
import org.model.JsonPathConstants;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.utils.ApiUtils;
import org.utils.JsonUtils;
import org.utils.RequestPaths;

import static io.restassured.RestAssured.*;

import io.restassured.http.Method;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.Map;


public class BasicAPI {
	
	
	@Test (enabled = true)
	public void createProductFromJson() throws IOException {
		
		Map<String, Object> requestPayload =JsonUtils.readJsonAsMap("src/test/resources/json/createProduct.json");	
		
	    requestPayload.put(JsonPathConstants.ID, "301");
	    requestPayload.put(JsonPathConstants.TITLE, "Jaga's Test Product");
		
	    Response response = ApiUtils.execute(
	    		RequestPaths.ENDPOINT,
                RequestPaths.PRODUCTS,
                Method.POST,
                requestPayload
        );
	    
	    System.out.println(response.asPrettyString());
	    
	    Assert.assertEquals(response.getStatusCode(), 201);
	    Assert.assertEquals(response.jsonPath().getString("title"), "Jaga's Test Product");
	    
    }
	
	
	@Test (enabled = true)
	public void getProduct() throws IOException {
		
//		Map<String, Object> requestPayload =JsonUtils.readJsonAsMap("src/test/resources/json/createProduct.json");	
//		
//	    requestPayload.put(JsonPathConstants.ID, "301");
//	    requestPayload.put(JsonPathConstants.TITLE, "Jaga's Test ");
		
	    Response response = ApiUtils.execute(
	    		RequestPaths.ENDPOINT,
                "/products/1",
                Method.GET,
                null
        );
	    
	    System.out.println(response.asPrettyString());
	    
	    Assert.assertEquals(response.getStatusCode(), 200);
	    // Assert.assertEquals(response.jsonPath().getString("title"), "Jaga's Test Product");
	    
    }
	
	
	
	@Test (enabled = true)
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
        .when()
            .post("/products/add")
        .then()
            .log().all()
            .statusCode(201)
            .body("title", equalTo("Test Product"))
            .body("brand", equalTo("QA Brand"));
    }
	
	
	@Test (enabled = true)
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

