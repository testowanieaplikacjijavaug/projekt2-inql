package org.inql.onlineshop.service;

import org.inql.onlineshop.domain.Order;
import javassist.NotFoundException;

import java.util.Set;

public interface OrderService {
    Set<Order> getOrders();
    Order findById(Long l) throws NotFoundException;
    Set<Order> findOrdersByClientId(Long id);
    Set<Order> findOrdersByClientEmail(String email);
    Order save(Order order);
    void deleteById(Long idToDelete);

}
