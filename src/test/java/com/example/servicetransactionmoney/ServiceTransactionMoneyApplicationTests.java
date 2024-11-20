package com.example.servicetransactionmoney;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServiceTransactionMoneyApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private final GenericContainer<?> cash_app_Container = new GenericContainer<>("app")
            .withExposedPorts(5500);


    @Test
    void contextLoads() {
        Integer port = cash_app_Container.getMappedPort(5500);
        //
        String jsonBody = "{\n" +
                "  \"cardFromNumber\": \"1232389012254802\",\n" +
                "  \"cardFromValidTill\": \"02/26\",\n" +
                "  \"cardFromCVV\": \"636\",\n" +
                "  \"cardToNumber\": \"6543000033332225\",\n" +
                "  \"amount\": {\n" +
                "    \"value\": 100.00,\n" +
                "    \"currency\": \"RUR\"\n" +
                "  }\n" +
                "}";
        //
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        //
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
        //
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "http://localhost:" + port + "/transfer",
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        //
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
