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
public class ItemTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void createValidator(){
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }


    Item item;

    @BeforeEach
    void setUp() {
        item = new Item();
    }

    @Test
    void itemNotNullTest() {
        assertThat(item).isNotNull();
    }

    @Test
    void itemInstanceTest() {
        assertThat(item).isInstanceOf(Item.class);
    }

    @Test
    void getIdTest() {
        Long id = 4L;
        item.setId(id);

        assertThat(item.getId()).isInstanceOf(Long.class).isEqualTo(id);
    }

    @Test
    void getNameTest() {
        String name = "Banana";
        item.setName(name);

        assertThat(item.getName()).isInstanceOf(String.class).isEqualTo(name);
    }

    @Test
    void getValueTest() {
        Double value = 22.05;
        item.setValue(value);

        assertThat(item.getValue()).isInstanceOf(Double.class).isEqualTo(value);
    }

    @Test
    void itemSuccessfulValidationTest() {
        //given
        Item item = new Item("Banana",25.25);

        //when
        Set<ConstraintViolation<Item>> violations =
                validator.validate(item);

        //then
        assertThat(violations).isEmpty();

    }

    @Test
    void itemInvalidNameNullInputTest() {
        //given
        String name = null;
        Item item = new Item(name, 25.25);

        //when
        Set<ConstraintViolation<Item>> violations =
                validator.validate(item);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Item> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(2),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder(
                        "Name cannot be null.","Name cannot be blank."));

    }

    @Test
    void itemInvalidNameBlankInputTest() {
        //given
        String name = "";
        Item item = new Item(name, 25.25);

        //when
        Set<ConstraintViolation<Item>> violations =
                validator.validate(item);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Item> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(3),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder("size must be between 2 and 30",
                        "Invalid name input.","Name cannot be blank."));

    }

    @Test
    void itemInvalidNameTooShortInputTest() {
        //given
        String name = "A";
        Item item = new Item(name, 25.25);

        //when
        Set<ConstraintViolation<Item>> violations =
                validator.validate(item);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Item> violation:
             violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(2),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder("size must be between 2 and 30",
                        "Invalid name input."));
    }

    @Test
    void itemInvalidNameTooLongInputTest() {
        //given
        String name = "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Item item = new Item(name, 25.25);

        //when
        Set<ConstraintViolation<Item>> violations =
                validator.validate(item);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Item> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder("size must be between 2 and 30"));

    }

    @Test
    void itemInvalidNameInvalidInputTest() {
        //given
        String name = "bAnAnA2";
        Item item = new Item(name, 25.25);

        //when
        Set<ConstraintViolation<Item>> violations =
                validator.validate(item);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Item> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder("Invalid name input."));

    }

    @Test
    void itemInvalidValueNullInputTest() {
        //given
        Double value = null;
        Item item = new Item("Banana", value);

        //when
        Set<ConstraintViolation<Item>> violations =
                validator.validate(item);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Item> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder("Value cannot be null."));
    }

    @Test
    void itemInvalidValueLowerThanZeroTest() {
        //given
        Double value = -2D;
        Item item = new Item("Banana", value);

        //when
        Set<ConstraintViolation<Item>> violations =
                validator.validate(item);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Item> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder("Value must be higher than zero."));
    }

    @Test
    void itemInvalidValueInvalidInputTest() {
        //given
        Double value = 2.2222222222;
        Item item = new Item("Banana", value);

        //when
        Set<ConstraintViolation<Item>> violations =
                validator.validate(item);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Item> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder("Invalid value input."));
    }

    @Test
    void itemInvalidValueInvalidInputSecondAttemptTest() {
        //given
        Double value = 2222222222222222222.22;
        Item item = new Item("Banana", value);

        //when
        Set<ConstraintViolation<Item>> violations =
                validator.validate(item);

        //then

        List<String> errorCodes = new ArrayList<>();

        for (ConstraintViolation<Item> violation:
                violations) {
            errorCodes.add(violation.getMessage());

        }

        assertAll("Conditions",
                () -> assertThat(violations).hasSize(1),
                () -> assertThat(errorCodes).containsExactlyInAnyOrder("Invalid value input."));
    }

    @AfterEach
    void tearDown() {
        item = null;
    }

    @AfterAll
    static void close() { validatorFactory.close();}
}
