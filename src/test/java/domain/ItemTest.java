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
    void getIdTest() {
        Long id = 4L;
        item.setId(id);

        assertThat(item.getId()).isEqualTo(id);
    }

    @Test
    void getNameTest() {
        String name = "Banana";
        item.setName(name);

        assertThat(item.getName()).isEqualTo(name);
    }

    @Test
    void getValueTest() {
        Double value = 22.05;
        item.setValue(value);

        assertThat(item.getValue()).isEqualTo(value);
    }

    @AfterEach
    void tearDown() {
        item = null;
    }
}
