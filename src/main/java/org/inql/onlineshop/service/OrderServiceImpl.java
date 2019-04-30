package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.inql.onlineshop.domain.Item;
import org.inql.onlineshop.domain.Order;
import org.inql.onlineshop.repository.OrderRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Set<Order> getOrders() {
        Set<Order> orderSet = new HashSet<>();
        orderRepository.findAll().iterator().forEachRemaining(orderSet::add);
        return orderSet;
    }

    @Override
    public Order findById(Long l) throws NotFoundException {
        Optional<Order> orderOptional = orderRepository.findById(l);
        return orderOptional.orElseThrow(() -> new NotFoundException("Order not found"));
    }

    @Override
    public Iterable<Order> findAll(Iterable<Long> orders_id) {
        return null;
    }

    @Override
    public Set<Order> findOrdersByClientId(Long id) {
        Set<Order> orderSet = new HashSet<>();
        orderRepository.findOrdersByClient_Id(id).iterator().forEachRemaining(orderSet::add);
        return orderSet;

    }

    @Override
    public Set<Order> findOrdersByClientEmail(String email) {
        Set<Order> orderSet = new HashSet<>();
        orderRepository.findOrdersByClient_Email(email).iterator().forEachRemaining(orderSet::add);
        return orderSet;
    }

    @Override
    public Set<Order> findOrderByItem(Item item) {
        Set<Order> orderSet = new HashSet<>();
        orderRepository.findOrdersByItemsContains(item).iterator().forEachRemaining(orderSet::add);
        return orderSet;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Iterable<Order> saveAll(Iterable<Order> orders) {
        return null;
    }

    @Override
    public void deleteById(Long idToDelete) {
        orderRepository.deleteById(idToDelete);
    }
}
