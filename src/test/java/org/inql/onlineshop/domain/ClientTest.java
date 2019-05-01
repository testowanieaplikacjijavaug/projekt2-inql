package org.inql.onlineshop.domain;

import org.junit.jupiter.api.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
public class ClientTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    Client client;

    @BeforeAll
    static void createValidator(){
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @BeforeEach
    void setUp() {
        client = new Client();
    }

    @Test
    void getIdTest() {
        Long id = 4L;
        client.setId(id);

        assertThat(client.getId()).isEqualTo(id);
    }

    @Test
    void getNameTest() {
        String name = "Mike";
        client.setName(name);

        assertThat(client.getName()).isEqualTo(name);
    }

    @Test
    void getSurnameTest() {
        String surname = "Fox";
        client.setSurname(surname);

        assertThat(client.getSurname()).isEqualTo(surname);
    }

    @Test
    void getEmailTest() {
        String email = "mike_fox@org.inql.onlineshop.domain.com";
        client.setEmail(email);

        assertThat(client.getEmail()).isEqualTo(email);
    }

    @Test
    void getOrdersTest() {
        List<Order> orders = new ArrayList<>();
        client.setOrders(orders);

        assertThat(client.getOrders()).isInstanceOf(List.class).hasOnlyElementsOfType(Order.class).isEqualTo(orders);
    }

    @Test
    void clientSuccessfulValidationTest() {
        //given
        Client client = new Client("Adam","Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        assertThat(violations).isEmpty();

    }

    @Test
    void clientInvalidIdTest(){
        Client client = new Client("Adam","Abacki","adam@abacki.pl");
        client.setId(null);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        assertThat(violations).isEmpty();
    }

    @Test
    void clientInvalidNameNullInputTest(){
        Client client = new Client(null,"Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("Name cannot be null"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("name"),
                () -> assertThat(constraintViolation.getInvalidValue()).isNull());
    }

    @Test
    void clientInvalidNameEmptyInputTest(){
        Client client = new Client("","Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("size must be between 4 and 30"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("name"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(""));
    }

    @Test
    void clientInvalidNameTooShortInputTest(){
        Client client = new Client("A","Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("size must be between 4 and 30"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("name"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo("A"));
    }

    @Test
    void clientInvalidNameTooLongTest(){
        Client client = new Client("Aosooosododsodosodsodosdosodosdosodsodsodosdosdosodaodaosdosaodasodasodasodasodsadasodsadoasodsaodasdosadosadasodoasdoawdwoadowadowaodwaod","Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("size must be between 4 and 30"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("name"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo("Aosooosododsodosodsodosdosodosdosodsodsodosdosdosodaodaosdosaodasodasodasodasodsadasodsadoasodsaodasdosadosadasodoasdoawdwoadowadowaodwaod"));
    }

    @Test
    void clientInvalidNameInvalidInputTest(){
        Client client = new Client("AnDrZ3J","Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("Invalid name provided"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("name"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo("AnDrZ3J"));
    }

    @Test
    void clientInvalidSurnameNullInputTest(){
        String surnameToTest = null;
        Client client = new Client("Adam",surnameToTest,"adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("Surname cannot be null"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("surname"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(surnameToTest));
    }

    @Test
    void clientInvalidSurnameEmptyInputTest(){
        String surnameToTest = "";
        Client client = new Client("Adam",surnameToTest,"adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("size must be between 4 and 30"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("surname"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(surnameToTest));
    }

    @Test
    void clientInvalidSurnameTooShortInputTest(){
        String surnameToTest = "A";
        Client client = new Client("Adam",surnameToTest,"adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("size must be between 4 and 30"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("surname"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(surnameToTest));
    }

    @Test
    void clientInvalidSurnameTooLongInputTest(){
        String surnameToTest = "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Client client = new Client("Adam",surnameToTest,"adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("size must be between 4 and 30"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("surname"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(surnameToTest));
    }

    @Test
    void clientInvalidSurnameInvalidInputTest(){
        String surnameToTest = "aBaCk1";
        Client client = new Client("Adam",surnameToTest,"adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("Invalid surname provided"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("surname"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(surnameToTest));
    }

    @Test
    void clientInvalidEmailNullInputTest(){
        String email = null;
        Client client = new Client("Adam","Abacki",email);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("Email cannot be null"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("email"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(email));
    }

    @Test
    void clientInvalidEmailEmptyInputTest(){
        String email = "";
        Client client = new Client("Adam","Abacki",email);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("size must be between 4 and 30"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("email"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(email));
    }

    @Test
    void clientInvalidEmailTooShortInputTest(){
        String email = "a";
        Client client = new Client("Adam","Abacki",email);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("size must be between 4 and 30"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("email"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(email));
    }

    @Test
    void clientInvalidEmailTooLongInputTest(){
        String email = "adamadamadamadamadamadamadamadma@emailssssssssssssssssssssssssss.pl";
        Client client = new Client("Adam","Abacki",email);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("size must be between 4 and 30"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("email"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(email));
    }

    @Test
    void clientInvalidEmailInvalidInputTest(){
        String email = "aBaCk1";
        Client client = new Client("Adam","Abacki",email);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        ConstraintViolation<Client> constraintViolation = violations.iterator().next();

        assertAll("Violation should match all assertions below",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(constraintViolation.getMessage()).isEqualTo("Invalid email provided"),
                () -> assertThat(constraintViolation.getPropertyPath().toString()).isEqualTo("email"),
                () -> assertThat(constraintViolation.getInvalidValue()).isEqualTo(email));
    }



    @AfterEach
    void tearDown() {
        client = null;
    }

    @AfterAll
    static void close() { validatorFactory.close();}
}
