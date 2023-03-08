package uk.co.huntersix.spring.rest.referencedata;

import org.springframework.stereotype.Service;
import uk.co.huntersix.spring.rest.exception.NoMatchingPersonException;
import uk.co.huntersix.spring.rest.exception.PersonAlreadyExistsException;
import uk.co.huntersix.spring.rest.exception.PersonNotAddedException;
import uk.co.huntersix.spring.rest.exception.PersonNotFoundException;
import uk.co.huntersix.spring.rest.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonDataService {
    public static final List<Person> PERSON_DATA;

    static {
        PERSON_DATA = new ArrayList<>();
        PERSON_DATA.add(new Person("Mary", "Smith"));
        PERSON_DATA.add(new Person("Brian", "Archer"));
        PERSON_DATA.add(new Person("Collin", "Brown"));
        PERSON_DATA.add(new Person("Mark", "Brown"));
        PERSON_DATA.add(new Person("Tim", "Brown"));
    }


    public  Person findPerson(String lastName, String firstName) throws PersonNotFoundException{
        Optional<Person> response = PERSON_DATA.stream()
            .filter(person -> person.isSamePerson(firstName,lastName))
            .findFirst();
        if(!response.isPresent()){
            throw new PersonNotFoundException();
        }
        return response.get();
    }

    public List<Person> findPerson(String lastName) throws NoMatchingPersonException{
          List<Person> persons =  PERSON_DATA.stream()
                .filter(person -> person.isLastNameSame(lastName))
                .collect(Collectors.toList());

          if(persons.size() ==0){
              throw new NoMatchingPersonException();
          }

          return persons;
    }

    public Person addPerson(Person person) throws PersonAlreadyExistsException,PersonNotAddedException{
        Optional<Person> response = PERSON_DATA.stream()
                .filter(person1 -> person1.equals(person))
                .findFirst();
        if(response.isPresent()){
            throw new PersonAlreadyExistsException();
        }
        person.assignId();
        PERSON_DATA.add(person);
        try {
          return  findPerson(person.getLastName(), person.getFirstName());
        }catch (PersonNotFoundException exception){
            throw new PersonNotAddedException();

        }
    }
}
