package org.inql.onlineshop.repository;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Item;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.StreamSupport;

public class MockItemRepository implements ItemRepository{

    Set<Item> database;

    public MockItemRepository() {
        this.database = new HashSet<>();
    }

    @Override
    public <S extends Item> S save(S s) {
        if(s == null) throw new IllegalArgumentException("Null item not allowed");
        database.add(s);
        return s;
    }

    @Override
    public <S extends Item> Iterable<S> saveAll(Iterable<S> iterable) {
        boolean nullCheck = StreamSupport.stream(iterable.spliterator(),false)
                .anyMatch(Objects::isNull);
        if(nullCheck) throw new IllegalArgumentException("Null item not allowed");
        iterable.iterator().forEachRemaining(database::add);
        return iterable;
    }

    @Override
    public Optional<Item> findById(Long aLong) {
        if(aLong == null)
            throw new IllegalArgumentException("Null id not allowed");
        return database
                .stream()
                .filter(item -> item.getId().equals(aLong))
                .findFirst();
    }

    @Override
    public boolean existsById(Long aLong) {
        if(aLong == null)
            throw new IllegalArgumentException("Null id not allowed");
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
        boolean nullCheck = StreamSupport.stream(iterable.spliterator(),false)
                .anyMatch(Objects::isNull);
        if(nullCheck) throw new IllegalArgumentException("Null id not allowed");
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
        if(aLong == null) throw new IllegalArgumentException("Null id not allowed");
        Optional<Item> itemToRemove = this.findById(aLong);
        itemToRemove.ifPresent(entity -> {
            if(!database.contains(entity)) throw new IllegalArgumentException("Item does not exist in database");
            database.remove(entity);
        });
    }

    @Override
    public void delete(Item item) {
        if(item == null) throw new IllegalArgumentException("Null item not allowed");
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
        if(name == null) throw new IllegalArgumentException("Null name not allowed");
        return database
                .stream()
                .filter(item -> item.getName().equals(name))
                .findFirst();
    }

    @Override
    public Set<Item> findItemsByNameContaining(String keyword) {
        if(keyword == null) throw new IllegalArgumentException("Null keyword not allowed");
        Set<Item> result = new HashSet<>();
        database
                .stream()
                .filter(item -> item.getName().contains(keyword))
                .forEach(result::add);
        return result;
    }

    @Override
    public Set<Item> findItemsByValue(Double value) {
        if(value == null || value<=0D) throw new IllegalArgumentException("Null or lower than zero value is not allowed");
        Set<Item> result = new HashSet<>();
        database
                .stream()
                .filter(item -> item.getValue().equals(value))
                .forEach(result::add);
        return result;
    }

    @Override
    public Set<Item> findItemsByValueBetween(Double lower, Double upper) {
        if(lower == null || lower<=0D) throw new IllegalArgumentException("Null or lower than zero value is not allowed");
        if(upper == null || upper<=0D) throw new IllegalArgumentException("Null or lower than zero value is not allowed");
        if(lower >= upper) throw new IllegalArgumentException("Lower value is higher than upper");
        Set<Item> result = new HashSet<>();
        database
                .stream()
                .filter(item -> item.getValue()>=lower && item.getValue()<=upper)
                .forEach(result::add);
        return result;
    }

    @Override
    public Set<Item> findItemsByValueLessThanEqual(Double value) {
        if(value == null || value<=0D) throw new IllegalArgumentException("Null or lower than zero value is not allowed");
        Set<Item> result = new HashSet<>();
        database
                .stream()
                .filter(item -> item.getValue()<=(value))
                .forEach(result::add);
        return result;
    }

    @Override
    public Set<Item> findItemsByValueLessThan(Double value) {
        if(value == null|| value<=0D) throw new IllegalArgumentException("Null or lower than zero value is not allowed");
        Set<Item> result = new HashSet<>();
        database
                .stream()
                .filter(item -> item.getValue()<(value))
                .forEach(result::add);
        return result;
    }

    @Override
    public Set<Item> findItemsByValueGreaterThanEqual(Double value) {
        if(value == null|| value<=0D) throw new IllegalArgumentException("Null or lower than zero value is not allowed");
        Set<Item> result = new HashSet<>();
        database
                .stream()
                .filter(item -> item.getValue()>=(value))
                .forEach(result::add);
        return result;
    }

    @Override
    public Set<Item> findItemsByValueGreaterThan(Double value) {
        if(value == null|| value<=0D) throw new IllegalArgumentException("Null or lower than zero value is not allowed");
        Set<Item> result = new HashSet<>();
        database
                .stream()
                .filter(item -> item.getValue()>(value))
                .forEach(result::add);
        return result;
    }
}
