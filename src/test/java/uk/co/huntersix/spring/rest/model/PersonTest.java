package uk.co.huntersix.spring.rest.model;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class PersonTest {
    @Test
    public void shouldAssignIdWhenCreated() {
        Person classUnderTest = new Person("John", "Smith");

        assertNotNull(classUnderTest.getId());
    }

    @Test
    public void idsShouldBeDifferent() {
        Person classUnderTest1 = new Person("John", "Smith");
        Person classUnderTest2 = new Person("Harry", "Brown");

        assertFalse(classUnderTest1.getId().equals(classUnderTest2.getId()));
    }


    @Test
    public void shouldBeDifferent() {
        Person classUnderTest1 = new Person("John", "Smith");
        Person classUnderTest2 = new Person("John", "Smith");

        assertNotSame(classUnderTest1, classUnderTest2);
    }

    @Test
    public void shouldBeSamePerson() {
        String firstName = "John";
        String lastName = "Smith";
        Person classUnderTest1 = new Person(firstName,lastName);
        assertTrue(classUnderTest1.isSamePerson(firstName,lastName));
    }


}
