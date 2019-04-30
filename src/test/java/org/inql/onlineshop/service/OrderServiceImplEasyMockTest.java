package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.easymock.TestSubject;
import org.inql.onlineshop.domain.Client;
import org.inql.onlineshop.domain.Item;
import org.inql.onlineshop.domain.Order;
import org.inql.onlineshop.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.easymock.EasyMock.*;

public class OrderServiceImplEasyMockTest {

    private OrderRepository orderRepository;

    @TestSubject
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = createNiceMock(OrderRepository.class);
        orderService = new OrderServiceImpl(orderRepository);
    }

    @Test
    void findOrderByIdTest() throws NotFoundException {
        Order order = new Order();
        order.setId(1L);
        Optional<Order> orderOptional = Optional.of(order);

        expect(orderRepository.findById(anyLong())).andReturn(orderOptional);
        replay(orderRepository);

        Order orderReturned = orderService.findById(1L);
        assertThat(orderReturned).isNotNull().isInstanceOf(Order.class).isEqualTo(order);
        verify(orderRepository);
    }

    @Test
    void findOrderByIdNotFoundTest(){
        Optional<Order> orderOptional = Optional.empty();

        expect(orderRepository.findById(anyLong())).andReturn(orderOptional);
        replay(orderRepository);

        assertThatThrownBy(() -> orderService.findById(1L)).isInstanceOf(NotFoundException.class).hasMessage("Order not found");
        verify(orderRepository);
    }

    @Test
    void findOrderByIdNullInputTest() {

        expect(orderRepository.findById(null)).andThrow(new IllegalArgumentException("Null id not allowed"));
        replay(orderRepository);

        assertThatThrownBy(() -> orderService.findById(null)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Null id not allowed");
        verify(orderRepository);

    }

    @Test
    void findOrdersByClientIdTest() {
        Client client = new Client();
        client.setId(1L);
        client.setEmail("example@example.com");
        Order order = new Order();
        order.setId(1L);
        order.setClient(client);

        HashSet ordersData = new HashSet();
        ordersData.add(order);

        expect(orderRepository.findOrdersByClient_Id(1L)).andReturn(ordersData);
        replay(orderRepository);

        Set<Order> orders = orderService.findOrdersByClientId(1L);

        assertThat(orders).isNotNull().hasSize(1).containsOnly(order).isInstanceOf(Set.class);
        verify(orderRepository);
    }

    @Test
    void findOrdersByClientIdEmptyTest() {
        Client client = new Client();
        client.setId(1L);
        client.setEmail("example@example.com");
        Order order = new Order();
        order.setId(1L);
        order.setClient(client);

        HashSet ordersData = new HashSet();
        ordersData.add(order);

        expect(orderRepository.findOrdersByClient_Id(2L)).andReturn(new HashSet<>());
        replay(orderRepository);

        Set<Order> orders = orderService.findOrdersByClientId(2L);

        assertThat(orders).isEmpty();
        verify(orderRepository);
    }

    @Test
    void findOrdersByClientIdNullInputTest() {
        expect(orderRepository.findOrdersByClient_Id(null)).andThrow(new IllegalArgumentException("Null id not allowed"));
        replay(orderRepository);

        assertThatThrownBy(() -> orderService.findOrdersByClientId(null)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Null id not allowed");

        verify(orderRepository);

    }

    @Test
    void findOrdersByClientEmailTest() {
        Client client = new Client();
        client.setId(1L);
        client.setEmail("example@example.com");
        Order order = new Order();
        order.setId(1L);
        order.setClient(client);

        HashSet ordersData = new HashSet();
        ordersData.add(order);

        expect(orderRepository.findOrdersByClient_Email("example@example.com")).andReturn(ordersData);
        replay(orderRepository);

        Set<Order> orders = orderService.findOrdersByClientEmail("example@example.com");

        assertThat(orders).isNotNull().hasSize(1).containsOnly(order).isInstanceOf(Set.class);
        verify(orderRepository);
    }

    @Test
    void findOrdersByClientEmailEmptyTest() {
        Client client = new Client();
        client.setId(1L);
        client.setEmail("example@example.com");
        Order order = new Order();
        order.setId(1L);
        order.setClient(client);

        HashSet ordersData = new HashSet();
        ordersData.add(order);

        expect(orderRepository.findOrdersByClient_Email("email@email.pl")).andReturn(new HashSet<>());
        replay(orderRepository);

        Set<Order> orders = orderService.findOrdersByClientEmail("email@email.pl");

        assertThat(orders).isEmpty();
        verify(orderRepository);
    }

    @Test
    void findOrdersByClientEmailNullInputTest() {
        expect(orderRepository.findOrdersByClient_Email(null)).andThrow(new IllegalArgumentException("Null email is not allowed"));
        replay(orderRepository);

        assertThatThrownBy(() -> orderService.findOrdersByClientEmail(null)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Null email is not allowed");

        verify(orderRepository);
    }

    @Test
    void findOrdersByClientEmailEmptyInputTest() {
        expect(orderRepository.findOrdersByClient_Email("")).andReturn(new HashSet<>());
        replay(orderRepository);

        Set<Order> ordersReturned = orderService.findOrdersByClientEmail("");

        assertThat(ordersReturned).isNotNull().isEmpty();
        verify(orderRepository);
    }

    @Test
    void findOrderByItemsTest() {
        Item item = new Item();
        item.setId(1L);
        Order order = new Order();
        order.setId(1L);
        order.getItems().add(item);

        HashSet ordersData = new HashSet();
        ordersData.add(order);

        expect(orderRepository.findOrdersByItemsContains(item)).andReturn(ordersData);
        replay(orderRepository);

        Set<Order> orders = orderService.findOrderByItem(item);

        assertThat(orders).isNotNull().hasSize(1).containsOnly(order).isInstanceOf(Set.class);
        verify(orderRepository);
    }

    @Test
    void findOrderByItemsEmptyTest() {
        Item item = new Item();
        item.setId(1L);
        Order order = new Order();
        order.setId(1L);
        order.getItems().add(item);
        Item secondItem = new Item();
        secondItem.setId(2L);

        HashSet ordersData = new HashSet();
        ordersData.add(order);

        expect(orderRepository.findOrdersByItemsContains(item)).andReturn(new HashSet<>());
        replay(orderRepository);

        Set<Order> orders = orderService.findOrderByItem(item);

        assertThat(orders).isEmpty();
        verify(orderRepository);
    }

    @Test
    void findOrdersByItemsNullInputTest() {
        expect(orderRepository.findOrdersByItemsContains(null)).andThrow(new IllegalArgumentException("Null item not allowed"));
        replay(orderRepository);

        assertThatThrownBy(() -> orderService.findOrderByItem(null)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Null item not allowed");

        verify(orderRepository);


    }

    @Test
    void getOrdersTest() {
        Order order = new Order();
        HashSet ordersData = new HashSet<>();
        ordersData.add(order);

        expect(orderRepository.findAll()).andReturn(ordersData);
        replay(orderRepository);

        Set<Order> orders = orderService.getOrders();

        assertThat(orders).isNotNull().hasSize(1).containsOnly(order).isInstanceOf(Set.class);
        verify(orderRepository);
    }

    @Test
    void deleteByIdTest() {
        Long idToDelete = 2L;
        //expect
        orderService.deleteById(2L);
        expectLastCall();
        replay(orderRepository);
        orderService.deleteById(2L);
    }

    @Test
    void deleteByIdNullInputTest() {
        Long idToDelete = null;

        orderService.deleteById(idToDelete);
        expectLastCall().andThrow(new IllegalArgumentException("Null id not allowed"));
        replay(orderRepository);
        assertThatThrownBy(() -> orderService.deleteById(idToDelete)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Null id not allowed");
        verify(orderRepository);
    }

    @AfterEach
    void tearDown() {
        orderService = null;
        orderRepository = null;
    }
}
