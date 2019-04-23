package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    @AfterEach
    void tearDown() {
        order = null;
    }
}
