package org.inql.onlineshop.service;

import org.inql.onlineshop.domain.Item;
import org.inql.onlineshop.domain.Order;
import javassist.NotFoundException;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<Order> getOrders();
    Order findById(Long l) throws NotFoundException;
    Iterable<Order> findAll(Iterable<Long> orders_id);
    Set<Order> findOrdersByClientId(Long id);
    Set<Order> findOrdersByClientEmail(String email);
    Set<Order> findOrderByItem(Item item);
    Order save(Order order);
    Iterable<Order> saveAll(Iterable<Order> orders);
    void deleteById(Long idToDelete);
    boolean addItemToOrder(Item item, Order order);
    double getOrderTotalValue(Order order);

}
