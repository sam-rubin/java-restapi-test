package uk.co.huntersix.spring.rest.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.huntersix.spring.rest.model.Person;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.huntersix.spring.rest.Constants.PERSON_NOT_FOUND;
import static uk.co.huntersix.spring.rest.Constants.PERSON_NOT_FOUND_WITH_PROVIDED_SURNAME;
import static uk.co.huntersix.spring.rest.Constants.DUPLICATE_PERSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnPersonDetails() throws Exception {
        assertThat(
            this.restTemplate.getForObject(
                "http://localhost:" + port + "/person/smith/mary",
                String.class
            )
        ).contains("Mary");
    }

    @Test
    public void shouldReturnErrorResponseWhenPersonObjectDoesNotExists() throws Exception {
        assertThat(this.restTemplate.getForObject(
                        "http://localhost:" + port + "/person/smith/mari",
                        String.class
                )).contains(PERSON_NOT_FOUND);
    }

    @Test
    public void shouldReturnAListResponseWhenMultiplePersonWithASurnameExists() throws Exception {
        Assert.assertEquals(this.restTemplate.getForObject(
                "http://localhost:" + port + "/person/brown",
                List.class
        ).size(),3);
    }

    @Test
    public void shouldReturnAListWithSingleResponseWhenSinglePersonWithASurnameExists() throws Exception {
        Assert.assertEquals(this.restTemplate.getForObject(
                "http://localhost:" + port + "/person/smith",
                List.class
        ).size(),1);
    }

    @Test
    public void shouldReturnANoMatchingPersonWhenPersonWithASurnameDoesNotExists() throws Exception {
        assertThat(this.restTemplate.getForObject(
                "http://localhost:" + port + "/person/kevin",
                String.class
        ).contains(PERSON_NOT_FOUND_WITH_PROVIDED_SURNAME));
    }

    @Test
    public void shouldAddANewPersonDetails() throws Exception {
        assertThat(
                this.restTemplate.postForObject(
                        "http://localhost:" + port + "/person/",
                        new Person("Georgey","Powell"),
                        String.class
                )
        ).contains("Georgey");
    }

    @Test
    public void shouldReturnPersonAlreadyExistsErrorWhenADuplicatePersonObjectIsAdded() throws Exception {
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/person/",
                new Person("George","Powell"),
                String.class
        );

        assertThat(
                this.restTemplate.postForObject(
                        "http://localhost:" + port + "/person/",
                        new Person("George","Powell"),
                        String.class
                )
        ).contains(DUPLICATE_PERSON);
    }
}