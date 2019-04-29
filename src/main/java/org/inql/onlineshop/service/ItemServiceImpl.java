package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Item;
import org.inql.onlineshop.repository.ItemRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ItemServiceImpl implements ItemService {

    ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Set<Item> getItems() {
        Set<Item> itemSet = new HashSet<>();
        itemRepository.findAll().iterator().forEachRemaining(itemSet::add);
        return itemSet;
    }

    @Override
    public Item findById(Long l) throws NotFoundException {
        Optional<Item> itemOptional = itemRepository.findById(l);
        return itemOptional.orElseThrow(() -> new NotFoundException("Item not found"));
    }

    @Override
    public Item findByName(String name) throws NotFoundException {
        Optional<Item> itemOptional = itemRepository.findItemByName(name);
        return itemOptional.orElseThrow(() -> new NotFoundException("Item not found"));    }

    @Override
    public Set<Item> findByNameContaining(String keyword) {
        Set<Item> itemSet = new HashSet<>();
        itemRepository.findItemsByNameContaining(keyword).iterator().forEachRemaining(itemSet::add);
        return itemSet;
    }

    @Override
    public Set<Item> findByValue(Double value) {
        Set<Item> itemSet = new HashSet<>();
        itemRepository.findItemsByValue(value).iterator().forEachRemaining(itemSet::add);
        return itemSet;
    }

    @Override
    public Set<Item> findByValueBetween(Double lower, Double upper) {
        Set<Item> itemSet = new HashSet<>();
        itemRepository.findItemsByValueBetween(lower,upper).iterator().forEachRemaining(itemSet::add);
        return itemSet;
    }

    @Override
    public Set<Item> findByValueLessThanEqual(Double value) {
        Set<Item> itemSet = new HashSet<>();
        itemRepository.findItemsByValueLessThanEqual(value).iterator().forEachRemaining(itemSet::add);
        return itemSet;
    }

    @Override
    public Set<Item> findByValueLessThan(Double value) {
        Set<Item> itemSet = new HashSet<>();
        itemRepository.findItemsByValueLessThan(value).iterator().forEachRemaining(itemSet::add);
        return itemSet;
    }

    @Override
    public Set<Item> findByValueGreaterThanEqual(Double value) {
        Set<Item> itemSet = new HashSet<>();
        itemRepository.findItemsByValueGreaterThanEqual(value).iterator().forEachRemaining(itemSet::add);
        return itemSet;
    }

    @Override
    public Set<Item> findByValueGreaterThan(Double value) {
        Set<Item> itemSet = new HashSet<>();
        itemRepository.findItemsByValueGreaterThan(value).iterator().forEachRemaining(itemSet::add);
        return itemSet;
    }

    @Override
    public Item save(Item item) {
        itemRepository.save(item);
        return item;
    }

    @Override
    public Iterable<Item> saveAll(Iterable<Item> items) {
        itemRepository.saveAll(items);
        return items;
    }

    @Override
    public void deleteById(Long l) {
        itemRepository.deleteById(l);
    }
}
