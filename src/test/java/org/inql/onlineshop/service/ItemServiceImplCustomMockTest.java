package org.inql.onlineshop.service;

import com.google.common.collect.Sets;
import javassist.NotFoundException;
import org.inql.onlineshop.domain.Item;
import org.inql.onlineshop.repository.ItemRepository;
import org.inql.onlineshop.repository.MockItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ItemServiceImplCustomMockTest {

    //Mock
    ItemRepository itemRepository;

    //SUT
    ItemService itemService;

    @BeforeEach
    void setUp() {
        itemRepository = new MockItemRepository();
        itemService = new ItemServiceImpl(itemRepository);
    }

    @Test
    void findItemByIdTest() throws NotFoundException {
        Item item = new Item();
        item.setId(1L);
        itemService.save(item);

        Item itemReturned = itemService.findById(1L);

        assertThat(itemReturned).isNotNull().isInstanceOf(Item.class).isEqualTo(item);
    }

    @Test
    void findItemByIdNotFoundTest() {
        assertThatThrownBy(() -> itemService.findById(1L)).isInstanceOf(NotFoundException.class).hasMessage("Item not found");
    }

    @Test
    void findItemByNameTest() throws NotFoundException {
        Item item = new Item();
        item.setName("Banana");
        itemService.save(item);

        Item itemReturned = itemService.findByName("Banana");

        assertThat(itemReturned).isNotNull().isInstanceOf(Item.class).isEqualTo(item);
    }

    @Test
    void findItemByNameNotFoundTest() {
        assertThatThrownBy(() -> itemService.findByName("Banana")).isInstanceOf(NotFoundException.class).hasMessage("Item not found");
    }

    @Test
    void findItemByNameContainingTest() {
        Item item = new Item();
        item.setName("Banana");
        Item secondItem = new Item();
        secondItem.setName("Ball");
        HashSet items = Sets.newHashSet(item, secondItem);
        itemService.saveAll(items);

        Set<Item> itemsReturned = itemService.findByNameContaining("Ba");

        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasOnlyElementsOfType(Item.class).hasSize(2).containsExactlyInAnyOrder(item, secondItem);
    }

    @Test
    void findItemByNameContainingNotFoundTest() {
        Item item = new Item();
        item.setName("Banana");
        Item secondItem = new Item();
        secondItem.setName("Ball");
        HashSet items = Sets.newHashSet(item, secondItem);
        itemService.saveAll(items);

        Set<Item> itemsReturned = itemService.findByNameContaining("Z");

        assertThat(itemsReturned).isEmpty();
    }

    @Test
    void findItemByValueTest() {
        Item item = new Item();
        item.setValue(25.25);
        itemService.save(item);

        Set<Item> itemsReturned = itemService.findByValue(25.25);

        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasSize(1).hasOnlyElementsOfType(Item.class).containsExactly(item);
    }

    @Test
    void findItemByValueMoreValuesTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(25.25);
        secondItem.setValue(25.25);
        thirdItem.setValue(1.00);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValue(25.25);

        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasOnlyElementsOfType(Item.class).hasSize(2).containsExactlyInAnyOrder(item, secondItem);

    }

    @Test
    void findItemByValueEmptyResultTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(25.25);
        secondItem.setValue(25.25);
        thirdItem.setValue(1.00);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValue(333.11);

        assertThat(itemsReturned).isEmpty();
    }

    @Test
    void findItemByValueBetweenTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(25.25);
        secondItem.setValue(23.25);
        thirdItem.setValue(1.00);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValueBetween(23.25,25.25);

        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasOnlyElementsOfType(Item.class).hasSize(2).containsExactlyInAnyOrder(item, secondItem);
    }

    @Test
    void findItemByValueBetweenEmptyResultTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(25.25);
        secondItem.setValue(23.25);
        thirdItem.setValue(1.00);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValueBetween(300D,400D);

        assertThat(itemsReturned).isEmpty();
    }

    @Test
    void findItemByValueLowerThanEqualTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(1.00);
        secondItem.setValue(22.21);
        thirdItem.setValue(300.1);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValueLessThanEqual(22.21);
        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasOnlyElementsOfType(Item.class).hasSize(2).containsExactlyInAnyOrder(item, secondItem);
    }

    @Test
    void findItemByValueLessThanEqualEmptyResultTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(1.00);
        secondItem.setValue(22.21);
        thirdItem.setValue(300.1);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValueLessThanEqual(0.5);
        assertThat(itemsReturned).isEmpty();

    }

    @Test
    void findItemByValueLessThanTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(1.00);
        secondItem.setValue(22.20);
        thirdItem.setValue(300.1);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValueLessThan(22.21);
        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasOnlyElementsOfType(Item.class).hasSize(2).containsExactlyInAnyOrder(item, secondItem);
    }

    @Test
    void findItemsByValueLessThanEmptyResultTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(1.00);
        secondItem.setValue(22.20);
        thirdItem.setValue(300.1);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValueLessThan(0.5);
        assertThat(itemsReturned).isEmpty();
    }

    @Test
    void findItemsByValueGreaterThanEqualTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(1111.00);
        secondItem.setValue(2222.20);
        thirdItem.setValue(300.1);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValueGreaterThanEqual(1111.00);
        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasOnlyElementsOfType(Item.class).hasSize(2).containsExactlyInAnyOrder(item, secondItem);
    }

    @Test
    void findItemsByValueGreaterThanEqualEmptyResultTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(1111.00);
        secondItem.setValue(2222.20);
        thirdItem.setValue(300.1);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValueGreaterThanEqual(11113.00);
        assertThat(itemsReturned).isEmpty();
    }

    @Test
    void findItemsByValueGreaterThanTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(1111.00);
        secondItem.setValue(2222.20);
        thirdItem.setValue(300.1);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValueGreaterThan(1110.00);
        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasOnlyElementsOfType(Item.class).hasSize(2).containsExactlyInAnyOrder(item, secondItem);
    }

    @Test
    void findItemsByValueGreaterThanEmptyResultTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(1111.00);
        secondItem.setValue(2222.20);
        thirdItem.setValue(300.1);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.findByValueGreaterThan(11113.00);
        assertThat(itemsReturned).isEmpty();
    }

    @Test
    void getItemsTest() {
        Item item = new Item();
        item.setName("Banana");
        Item secondItem = new Item();
        secondItem.setName("Ball");
        HashSet items = Sets.newHashSet(item, secondItem);
        itemService.saveAll(items);

        Set<Item> itemsReturned = itemService.getItems();

        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasOnlyElementsOfType(Item.class).hasSize(2).containsExactlyInAnyOrder(item, secondItem);
    }

    @Test
    void saveItemTest() {
        Item item = new Item();
        item.setId(1L);
        itemService.save(item);

        Set<Item> itemsReturned = itemService.getItems();

        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasOnlyElementsOfType(Item.class).hasSize(1).containsExactly(item);
    }

    @Test
    void saveAllItemsTest() {
        Item item = new Item();
        Item secondItem = new Item();
        Item thirdItem = new Item();
        item.setValue(1111.00);
        secondItem.setValue(2222.20);
        thirdItem.setValue(300.1);
        HashSet items = Sets.newHashSet(item, secondItem, thirdItem);
        itemService.saveAll(items);
        Set<Item> itemsReturned = itemService.getItems();
        assertThat(itemsReturned).isNotNull().isNotEmpty().isInstanceOf(Set.class).hasOnlyElementsOfType(Item.class).hasSize(3).containsExactlyInAnyOrder(item, secondItem,thirdItem);
    }

    @AfterEach
    void tearDown() {
        itemRepository = null;
        itemService = null;
    }
}
