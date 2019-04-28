package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Order;
import org.inql.onlineshop.repository.OrderRepository;

import java.util.Set;

public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Set<Order> getOrders() {
        return null;
    }

    @Override
    public Order findById(Long l) throws NotFoundException {
        return null;
    }

    @Override
    public Set<Order> findOrdersByClientId(Long id) {
        return null;
    }

    @Override
    public Set<Order> findOrdersByClientEmail(String email) {
        return null;
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public void deleteById(Long idToDelete) {

    }
}
