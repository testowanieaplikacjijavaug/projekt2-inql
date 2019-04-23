package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
public class ClientTest {

    Client client;

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

    @AfterEach
    void tearDown() {
        client = null;
    }
}
