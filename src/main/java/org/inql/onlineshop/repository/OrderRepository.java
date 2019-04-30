package org.inql.onlineshop.repository;

import org.inql.onlineshop.domain.Client;
import org.inql.onlineshop.domain.Item;
import org.inql.onlineshop.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Map;
import java.util.Set;

public interface OrderRepository extends CrudRepository<Order,Long> {

    Set<Order> findOrdersByClient_Id(Long id);
    Set<Order> findOrdersByClient_Email(String email);
    Set<Order> findOrdersByItemsContains(Item item);

}
