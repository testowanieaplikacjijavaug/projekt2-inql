package org.inql.onlineshop.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class OrderTest {

    Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void getIdTest() {
        Long id = 4L;
        order.setId(id);

        assertThat(order.getId()).isInstanceOf(Long.class).isEqualTo(id);
    }

    @Test
    void getClientTest() {
        Client client = new Client();
        order.setClient(client);

        assertThat(order.getClient()).isInstanceOf(Client.class).isEqualTo(client);
    }

    @Test
    void getItemsTest() {
        List<Item> items = new ArrayList<>();
        order.setItems(items);

        assertThat(order.getItems()).isInstanceOf(List.class).hasOnlyElementsOfType(Item.class).isEqualTo(items);
    }

    @AfterEach
    void tearDown() {
        order = null;
    }
}
