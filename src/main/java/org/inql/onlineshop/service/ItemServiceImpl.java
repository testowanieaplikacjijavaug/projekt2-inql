package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Item;
import org.inql.onlineshop.repository.ItemRepository;

import java.util.Set;

public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Set<Item> getItems() {
        return null;
    }

    @Override
    public Item findById(Long l) throws NotFoundException {
        return null;
    }

    @Override
    public Item findByName(String name) throws NotFoundException {
        return null;
    }

    @Override
    public Set<Item> findByNameContaining(String keyword) {
        return null;
    }

    @Override
    public Set<Item> findByValue(Double value) {
        return null;
    }

    @Override
    public Set<Item> findByValueBetween(Double lower, Double upper) {
        return null;
    }

    @Override
    public Set<Item> findByValueLessThanEqual(Double value) {
        return null;
    }

    @Override
    public Set<Item> findByValueLessThan(Double value) {
        return null;
    }

    @Override
    public Set<Item> findByValueGreaterThanEqual(Double value) {
        return null;
    }

    @Override
    public Set<Item> findByValueGreaterThan(Double value) {
        return null;
    }

    @Override
    public void save(Item item) {

    }

    @Override
    public void saveAll(Iterable<Item> items) {

    }

    @Override
    public void deleteById(Long l) {

    }
}
