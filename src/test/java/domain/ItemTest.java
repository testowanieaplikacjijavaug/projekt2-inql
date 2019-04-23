package domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
public class ItemTest {

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

    @AfterEach
    void tearDown() {
        item = null;
    }
}
