package de.fhopf.blog.springsecurity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EchoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void canEcho() {
        ResponseEntity<String> response = restTemplate.getForEntity("/echo?message=hello", String.class);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("hello", response.getBody());
    }

    @Test
    public void secretEchoIsProtectedWithLoginPage() {
        ResponseEntity<String> response = restTemplate.getForEntity("/secret/echo?message=dontcare", String.class);
        assertThat(response.getBody()).contains("Login with Username and Password");
    }

    @Test
    public void actuatorIsProtectedWithBasicAuth() {
        ResponseEntity<String> response = restTemplate.getForEntity("/management/health", String.class);
        assertEquals(401, response.getStatusCodeValue());
    }

}
