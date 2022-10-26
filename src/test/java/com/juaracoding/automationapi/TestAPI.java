package com.juaracoding.automationapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class TestAPI {
    String endpoint = "https://mern-backend-8881.herokuapp.com/products";
    @Test
    public void testGetList() {
        Response response = RestAssured.get(endpoint);
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        System.out.println(response.getTime());
        System.out.println("Test GetList");

        int actual = response.getStatusCode();
        Assert.assertEquals(actual, 200);
    }
    @Test
    public void testGetSingle() {
        given()
                .queryParam("api_key", "62e20e5bf0e24546b900e053")
                .when()
                .get("https://mern-backend-8881.herokuapp.com/products/62e20e5bf0e24546b900e053")
                .then().statusCode(200);
        System.out.println("Test Get Single");
    }
    @Test
    public void testPut() {
        JSONObject request = new JSONObject();
        request.put("name", "Kulkas");
        request.put("category", "Elektronik");
        System.out.println(request.toJSONString());

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .put("https://mern-backend-8881.herokuapp.com/products/")
                .then()
                .statusCode(404)
                .log().all();
        System.out.println("Test Put");
    }
    @Test
    public void testPatch() {
        JSONObject request = new JSONObject();
        request.put("name", "Kulkas");
        request.put("category", "Elektronik");
        System.out.println(request.toJSONString());

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .patch("https://mern-backend-8881.herokuapp.com/products/")
                .then()
                .statusCode(404)
                .log().all();
        System.out.println("Test Patch");
    }

    @Test
    public void testDelete() {

        when()
                .delete("https://mern-backend-8881.herokuapp.com/products/62e20a17f0e24546b900e037")
                .then()
                .statusCode(200)
                .log().all();
        System.out.println("Test Delete");

    }

}
