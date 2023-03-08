package uk.co.huntersix.spring.rest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import uk.co.huntersix.spring.rest.Constants;
import uk.co.huntersix.spring.rest.exception.NoMatchingPersonException;
import uk.co.huntersix.spring.rest.exception.PersonAlreadyExistsException;
import uk.co.huntersix.spring.rest.exception.PersonNotFoundException;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.PersonDataService;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonDataService personDataService;

    @Test
    public void shouldReturnPersonFromService() throws Exception {
        when(personDataService.findPerson(any(), any())).thenReturn(new Person("Mary", "Smith"));
        this.mockMvc.perform(get("/person/smith/mary"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").exists())
            .andExpect(jsonPath("firstName").value("Mary"))
            .andExpect(jsonPath("lastName").value("Smith"));
    }

    @Test
    public void shouldReturnPersonNotFoundErrorWhenPersonDoesNotExists() throws Exception {
        when(personDataService.findPerson(any(), any())).thenThrow(PersonNotFoundException.class);
        this.mockMvc.perform(get("/person/smith/mari"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("status").value("NOT_FOUND"));
    }



    @Test
    public void shouldReturnPersonListFromServiceForProvidedSurname() throws Exception {
        when(personDataService.findPerson(any())).thenReturn(Arrays.asList(new Person("Tim","Brown")));
        this.mockMvc.perform(get("/person/brown"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].firstName").value("Tim"))
                .andExpect(jsonPath("$[0].lastName").value("Brown"));
    }

    @Test
    public void shouldReturnNoMatchingPersonWhenPersonWithSurnameDoesNotExists() throws Exception {
        when(personDataService.findPerson(any())).thenThrow(NoMatchingPersonException.class);
        this.mockMvc.perform(get("/person/smith"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("status").value("NOT_FOUND"));
    }

    @Test
    public void shouldAddANewPersonFromService() throws Exception {



        when(personDataService.addPerson(any())).thenReturn(new Person("George","Powell"));
        String requestJson="{\n" +
                "  \"firstName\" : \"George\",\n" +
                "  \"lastName\" : \"Powell\"\n" +
                "}";

        ResultActions resultActions = this.mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("firstName").value("George"))
                .andExpect(jsonPath("lastName").value("Powell"));


    }

    @Test
    public void shouldReturnPersonAlreadyExistsErrorWhenPersonExists() throws Exception {
        when(personDataService.addPerson(any())).thenThrow(PersonAlreadyExistsException.class);
        String requestJson="{\n" +
                "  \"firstName\" : \"George\",\n" +
                "  \"lastName\" : \"Powell\"\n" +
                "}";

        ResultActions resultActions = this.mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print())
                .andExpect(status().isBadRequest());


    }
}