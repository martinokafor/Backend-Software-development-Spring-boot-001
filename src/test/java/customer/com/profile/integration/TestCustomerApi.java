package customer.com.profile.integration;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;


public class TestCustomerApi {
    @Before
    public void setUp(){
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void TestPostCreateCustomer(){
        JSONObject header = new JSONObject();
        header.put("Content-Type","application/json");
        JSONObject body = new JSONObject();
        body.put("customerName", "jude1");
        body.put("city", "Berlin");
        Response response = RestAssured.
                given().
                headers(header).
                body(body).
                when().
                post(RestAssured.baseURI + "/customer");

        response.then().
                assertThat().statusCode(201);


    }

    @Test
    public void TestGetCustomers(){
        Response response = RestAssured.
                given().
                when().
                get(RestAssured.baseURI + "/customers");

        response.then().
                assertThat().statusCode(200).log().all();


    }
}
