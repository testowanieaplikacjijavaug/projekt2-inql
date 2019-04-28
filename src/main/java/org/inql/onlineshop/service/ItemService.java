package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Item;

import java.util.Set;

public interface ItemService {
    Set<Item> getItems();
    Item findById(Long l) throws NotFoundException;
    Item findByName(String name) throws NotFoundException;
    Set<Item> findByNameContaining(String keyword);
    Set<Item> findByValue(Double value);
    Set<Item> findByValueBetween(Double lower, Double upper);
    Set<Item> findByValueLessThanEqual(Double value);
    Set<Item> findByValueLessThan(Double value);
    Set<Item> findByValueGreaterThanEqual(Double value);
    Set<Item> findByValueGreaterThan(Double value);
    Item save(Item item);
    Iterable<Item> saveAll(Iterable<Item> items);
    void deleteById(Long l);
}
