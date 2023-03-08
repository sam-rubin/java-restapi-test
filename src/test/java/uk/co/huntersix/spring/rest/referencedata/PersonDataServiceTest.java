package uk.co.huntersix.spring.rest.referencedata;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.huntersix.spring.rest.exception.NoMatchingPersonException;
import uk.co.huntersix.spring.rest.exception.PersonAlreadyExistsException;
import uk.co.huntersix.spring.rest.exception.PersonNotAddedException;
import uk.co.huntersix.spring.rest.exception.PersonNotFoundException;
import uk.co.huntersix.spring.rest.model.Person;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PersonDataServiceTest {

    @InjectMocks
    PersonDataService personDataService;

    @Test
    public void shouldReturnAPersonObjectWhenAPersonExists() throws PersonNotFoundException{
        Person person = personDataService.findPerson("Smith","Mary");
        Assert.assertNotNull(person);
    }

    @Test(expected = PersonNotFoundException.class)
    public void shouldThrowPersonNotFoundExceptionWhenPersonDoesNotExists() throws PersonNotFoundException{
        personDataService.findPerson("Smith","Barry");
    }

    @Test
    public void shouldReturnAListOfPersonWhenAPersonWithLastNameExists() throws NoMatchingPersonException{
         List<Person> persons =   personDataService.findPerson("Brown");

         Assert.assertEquals(3,persons.size());

    }
    @Test(expected = NoMatchingPersonException.class)
    public void shouldThrowNoMatchingPersonExceptionWhenNoOneExistsWithLastName() throws NoMatchingPersonException{
        personDataService.findPerson("Kevin");
    }



    @Test
    public void shouldAddANewPersonIfThePersonDoesNotExists() throws PersonAlreadyExistsException,PersonNotAddedException {
        String firstName = "Paul";
        String lastName = "Powell";
        Person person = new Person(firstName,lastName);
        Assert.assertNotNull(personDataService.addPerson(person));
    }


    @Test(expected = PersonAlreadyExistsException.class)
    public void shouldThrowPersonAlreadyExistsExceptionWhileAddingADuplicatePerson() throws PersonAlreadyExistsException,PersonNotAddedException {
        String firstName = "Tommy";
        String lastName = "Jim";
        Person person = new Person(firstName,lastName);
        Assert.assertNotNull(personDataService.addPerson(person));
        personDataService.addPerson(person);
    }

}