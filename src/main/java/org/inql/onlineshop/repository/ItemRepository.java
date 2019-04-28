package org.inql.onlineshop.repository;

import org.inql.onlineshop.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Optional<Item> findItemByName(String name);
    Set<Item> findItemsByNameContaining(String keyword);
    Set<Item> findItemsByValue(Double value);
    Set<Item> findItemsByValueBetween(Double lower, Double upper);
    Set<Item> findItemsByValueLessThanEqual(Double value);
    Set<Item> findItemsByValueLessThan(Double value);
    Set<Item> findItemsByValueGreaterThanEqual(Double value);
    Set<Item> findItemsByValueGreaterThan(Double value);


}
