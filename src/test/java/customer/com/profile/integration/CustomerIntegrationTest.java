package customer.com.profile.integration;

import customer.com.profile.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


@SpringBootTest
@WireMockTest(httpPort = 8084)
public class CustomerIntegrationTest {


    @Test
    public void getCustomerProducer() throws IOException {

        stubFor(get(urlEqualTo("/customer_producers"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(" {\n" +
                                "        \"customerId\": 1,\n" +
                                "        \"customerName\": \"Lavanya\",\n" +
                                "        \"city\": \"berlin\",\n" +
                                "        \"noOfVehicle\": null,\n" +
                                "    }")));

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.
                getForEntity("http://localhost:8084/customer_producers", String.class);
        System.out.println(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.getBody().contains("customerName"), String.valueOf(true));
    }

    @Test
    public void getProduceCustomerProducer() throws IOException {

        stubFor(post(urlEqualTo("/customer_producer"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(" {\n" +
                                "        \"customerId\": 1,\n" +
                                "        \"customerName\": \"Martin\",\n" +
                                "        \"city\": \"dresden\",\n" +
                                "        \"noOfVehicle\": 1,\n" +
                                "    }")));

        String name = "Martin";
        String city = "dresden";

        Customer firstCustomer = Customer.builder().
                customerName(name).
                city(city).
                noOfVehicle(1).
                build();

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> response = testRestTemplate.
                postForEntity("http://localhost:8084/customer_producer", firstCustomer, String.class);
        System.out.println(response.getBody());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertTrue(response.getBody().contains("city"), String.valueOf(true));

    }
}

