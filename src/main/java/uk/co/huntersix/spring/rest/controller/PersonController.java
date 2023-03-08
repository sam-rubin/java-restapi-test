package uk.co.huntersix.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.co.huntersix.spring.rest.exception.NoMatchingPersonException;
import uk.co.huntersix.spring.rest.exception.PersonAlreadyExistsException;
import uk.co.huntersix.spring.rest.exception.PersonNotAddedException;
import uk.co.huntersix.spring.rest.exception.PersonNotFoundException;
import uk.co.huntersix.spring.rest.model.Person;
import uk.co.huntersix.spring.rest.referencedata.PersonDataService;

import java.util.List;

@RestController
public class PersonController {
    private PersonDataService personDataService;

    public PersonController(@Autowired PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping("/person/{lastName}/{firstName}")
    public ResponseEntity<Person> person(@PathVariable(value="lastName") String lastName,
                                        @PathVariable(value="firstName") String firstName) throws PersonNotFoundException {
        return new ResponseEntity<>(personDataService.findPerson(lastName, firstName), HttpStatus.OK);
    }

    @GetMapping("/person/{lastName}")
    public ResponseEntity<List<Person>> person(@PathVariable(value="lastName") String lastName) throws NoMatchingPersonException {
        return new ResponseEntity<>(personDataService.findPerson(lastName), HttpStatus.OK);
    }

    @PostMapping("/person")
    public ResponseEntity<Person> add(@RequestBody Person person) throws PersonAlreadyExistsException, PersonNotAddedException {

        return new ResponseEntity<>(personDataService.addPerson(person),HttpStatus.OK);
    }
}