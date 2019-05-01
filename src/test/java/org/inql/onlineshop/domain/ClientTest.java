package org.inql.onlineshop.domain;

import org.junit.jupiter.api.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Iterator;
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
        String email = "mike_fox@domain.com";
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
    void clientInvalidNameNullInputTest(){
        Client client = new Client(null,"Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "Name cannot be null"));

    }

    @Test
    void clientInvalidNameEmptyInputTest(){
        Client client = new Client("","Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "size must be between 4 and 30"));
    }

    @Test
    void clientInvalidNameTooShortInputTest(){
        Client client = new Client("Aa","Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "size must be between 4 and 30"));
    }

    @Test
    void clientInvalidNameTooLongTest(){
        Client client = new Client("Aosooosododsodosodsodosdosodosdosodsodsodosdosdosodaodaosdosaodasodasodasodasodsadasodsadoasodsaodasdosadosadasodoasdoawdwoadowadowaodwaod","Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "size must be between 4 and 30"));
    }

    @Test
    void clientInvalidNameInvalidInputTest(){
        Client client = new Client("AnDrZ3J","Abacki","adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "Invalid name provided"));
        ConstraintViolation<Client> constraintViolation = violations.iterator().next();
    }

    @Test
    void clientInvalidSurnameNullInputTest(){
        String surnameToTest = null;
        Client client = new Client("Adam",surnameToTest,"adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "Surname cannot be null"));
    }

    @Test
    void clientInvalidSurnameEmptyInputTest(){
        String surnameToTest = "";
        Client client = new Client("Adam",surnameToTest,"adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "size must be between 4 and 30"));
    }

    @Test
    void clientInvalidSurnameTooShortInputTest(){
        String surnameToTest = "A";
        Client client = new Client("Adam",surnameToTest,"adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "size must be between 4 and 30"));
    }

    @Test
    void clientInvalidSurnameTooLongInputTest(){
        String surnameToTest = "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Client client = new Client("Adam",surnameToTest,"adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "size must be between 4 and 30"));
    }

    @Test
    void clientInvalidSurnameInvalidInputTest(){
        String surnameToTest = "aBaCk1";
        Client client = new Client("Adam",surnameToTest,"adam@abacki.pl");

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "Invalid surname provided"));
    }

    @Test
    void clientInvalidEmailNullInputTest(){
        String email = null;
        Client client = new Client("Adam","Abacki",email);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "Email cannot be null"));
    }

    @Test
    void clientInvalidEmailEmptyInputTest(){
        String email = "";
        Client client = new Client("Adam","Abacki",email);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(2),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "size must be between 4 and 30", "Invalid email provided"));
    }

    @Test
    void clientInvalidEmailTooShortInputTest(){
        String email = "a";
        Client client = new Client("Adam","Abacki",email);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(2),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "size must be between 4 and 30", "Invalid email provided"));
    }

    @Test
    void clientInvalidEmailTooLongInputTest(){
        String email = "adamadamadamadamadamadamadamadma@emailssssssssssssssssssssssssss.pl";
        Client client = new Client("Adam","Abacki",email);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "size must be between 4 and 30"));
    }

    @Test
    void clientInvalidEmailInvalidInputTest(){
        String email = "aBaCk1";
        Client client = new Client("Adam","Abacki",email);

        //when
        Set<ConstraintViolation<Client>> violations =
                validator.validate(client);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Client> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "Invalid email provided"));

    }



    @AfterEach
    void tearDown() {
        client = null;
    }

    @AfterAll
    static void close() { validatorFactory.close();}
}
