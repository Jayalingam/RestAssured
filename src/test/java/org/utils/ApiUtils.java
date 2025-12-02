package org.utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtils {

    public static Response execute(
            String hostUrl,
            String path,
            Method post,
            Object payload) {

        RequestSpecification requestSpecification =
                RestAssured.given()
                		//.log().all()
                        .header("Content-Type", "application/json"); 
        
        if (payload != null) {
            requestSpecification.body(payload);
        }

        Response response = requestSpecification
                .request(post, hostUrl + path) 
                .then()
                .extract()
                .response();

        return response;
    }
    
    RequestSpecification requestSpecification =
            RestAssured.given()
                    .filter(new AllureRestAssured())   // ✅ Captures req/resp in report
                    .header("Content-Type", "application/json")
                    .log().all();

    
}
