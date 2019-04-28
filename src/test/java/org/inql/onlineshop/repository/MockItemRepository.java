package org.inql.onlineshop.repository;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Item;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class MockItemRepository implements ItemRepository{

    Set<Item> database;

    public MockItemRepository() {
        this.database = new HashSet<>();
    }

    @Override
    public <S extends Item> S save(S s) {
        database.add(s);
        return s;
    }

    @Override
    public <S extends Item> Iterable<S> saveAll(Iterable<S> iterable) {
        iterable.iterator().forEachRemaining(database::add);
        return iterable;
    }

    @Override
    public Optional<Item> findById(Long aLong) {
        return database
                .stream()
                .filter(item -> item.getId().equals(aLong))
                .findFirst();
    }

    @Override
    public boolean existsById(Long aLong) {
        return database
                .stream()
                .anyMatch(item -> item.getId().equals(aLong));
    }

    @Override
    public Iterable<Item> findAll() {
        return database;
    }

    @Override
    public Iterable<Item> findAllById(Iterable<Long> iterable) {
        Iterator<Long> longIterator = iterable.iterator();
        Set<Item> result = new HashSet<>();
        while(longIterator.hasNext()){
            Long currentId = longIterator.next();
            if(currentId==null) throw new IllegalArgumentException("Null ids are not allowed");
            this.findById(currentId).ifPresent(result::add);
        }
        return result;
    }

    @Override
    public long count() {
        return database.size();
    }

    @Override
    public void deleteById(Long aLong) {
        Optional<Item> itemToRemove = this.findById(aLong);
        itemToRemove.ifPresent(entity -> {
            if(!database.contains(entity)) throw new IllegalArgumentException("Item does not exist in database");
            database.remove(database);
        });
    }

    @Override
    public void delete(Item item) {
        Optional<Item> itemToRemove = Optional.of(item);
        itemToRemove.ifPresent(entity -> {
            if(!database.contains(entity)) throw new IllegalArgumentException("Item does not exist in database");
            database.remove(entity);
        });
    }

    @Override
    public void deleteAll(Iterable<? extends Item> iterable) {
        Iterator<? extends Item> itemIterator = iterable.iterator();
        while(itemIterator.hasNext()){
            Item currentItem = itemIterator.next();
            if(currentItem == null) throw new IllegalArgumentException("Null items are not allowed");
            this.delete(currentItem);
        }
    }

    @Override
    public void deleteAll() {
        database.forEach(database::remove);
    }

    @Override
    public Optional<Item> findItemByName(String name) {
        return database
                .stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();
    }

    @Override
    public Set<Item> findItemsByNameContaining(String keyword) {
        return null;
    }

    @Override
    public Set<Item> findItemsByValue(Double value) {
        return null;
    }

    @Override
    public Set<Item> findItemsByValueBetween(Double lower, Double upper) {
        return null;
    }

    @Override
    public Set<Item> findItemsByValueLessThanEqual(Double value) {
        return null;
    }

    @Override
    public Set<Item> findItemsByValueLessThan(Double value) {
        return null;
    }

    @Override
    public Set<Item> findItemsByValueGreaterThanEqual(Double value) {
        return null;
    }

    @Override
    public Set<Item> findItemsByValueGreaterThan(Double value) {
        return null;
    }
}
