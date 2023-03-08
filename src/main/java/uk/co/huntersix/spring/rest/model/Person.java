package uk.co.huntersix.spring.rest.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Person {
    private  static final AtomicLong counter = new AtomicLong();

    private Long id;
    private String firstName;
    private String lastName;

    private Person() {
        // empty
    }

    public Person(String firstName, String lastName) {
        this.id = counter.incrementAndGet();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void assignId(){
        this.id = counter.incrementAndGet();
    }

    public Long getId() {
        return id;
    }

    public boolean isSamePerson(String firstName, String lastName){
        return this.firstName.equalsIgnoreCase(firstName) && this.lastName.equalsIgnoreCase(lastName);
    }

    public boolean isLastNameSame(String lastName){
        return this.lastName.equalsIgnoreCase(lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return firstName.equalsIgnoreCase(person.firstName)  && lastName.equalsIgnoreCase(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
