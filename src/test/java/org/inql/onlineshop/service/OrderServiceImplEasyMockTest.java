package org.inql.onlineshop.service;

import javassist.NotFoundException;
import org.assertj.core.data.Offset;
import org.easymock.TestSubject;
import org.inql.onlineshop.domain.Client;
import org.inql.onlineshop.domain.Item;
import org.inql.onlineshop.domain.Order;
import org.inql.onlineshop.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertAll;

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
    void findAllTest() {
        Order order = new Order();
        order.setId(1L);
        Order secondOrder = new Order();
        secondOrder.setId(2L);

        HashSet<Order> ordersData = new HashSet<>(Arrays.asList(order,secondOrder));

        List<Long> idsToFind = Arrays.asList(1L,2L);

        expect(orderRepository.findAllById(idsToFind)).andReturn(ordersData);
        replay(orderRepository);

        Iterable<Order> ordersReturned = orderService.findAll(idsToFind);

        assertThat(ordersReturned).isNotNull().isNotEmpty().hasOnlyElementsOfType(Order.class).hasSize(2).containsExactlyInAnyOrder(order,secondOrder);
        verify(orderRepository);

    }

    @Test
    void findAllPartResultTest() {
        Order order = new Order();
        order.setId(1L);
        Order secondOrder = new Order();
        secondOrder.setId(2L);

        HashSet<Order> ordersData = new HashSet<>(Arrays.asList(order,secondOrder));

        List<Long> idsToFind = Arrays.asList(1L);

        expect(orderRepository.findAllById(idsToFind)).andReturn(new HashSet<>(Collections.singletonList(order)));
        replay(orderRepository);

        Iterable<Order> ordersReturned = orderService.findAll(idsToFind);

        assertThat(ordersReturned).isNotNull().isNotEmpty().hasOnlyElementsOfType(Order.class).hasSize(1).containsExactlyInAnyOrder(order);
        verify(orderRepository);
    }

    @Test
    void findAllNullInputTest() {
        List<Long> idsToFind = Arrays.asList(1L,null);

        expect(orderRepository.findAllById(idsToFind)).andThrow(new IllegalArgumentException("Null id not allowed"));
        replay(orderRepository);

        assertThatThrownBy(() -> orderService.findAll(idsToFind))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Null id not allowed");
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
    void saveOrderTest() {
        Order order = new Order();
        order.setId(1L);

        expect(orderRepository.save(order)).andReturn(order);
        replay(orderRepository);

        Order savedOrder = orderService.save(order);

        assertThat(savedOrder).isNotNull().isInstanceOf(Order.class).isEqualTo(order);
        verify(orderRepository);
    }

    @Test
    void saveOrderNullTest() {
        Order order = null;

        expect(orderRepository.save(order)).andThrow(new IllegalArgumentException("Null order not allowed"));
        replay(orderRepository);

        assertThatThrownBy(() -> orderService.save(order)).isInstanceOf(IllegalArgumentException.class).hasMessage("Null order not allowed");
    }

    @Test
    void saveAllTest() {
        Order order = new Order();
        order.setId(1L);
        Order secondOrder = new Order();
        secondOrder.setId(2L);

        HashSet<Order> ordersData = new HashSet<>(Arrays.asList(order,secondOrder));

        expect(orderRepository.saveAll(ordersData)).andReturn(new HashSet<>(Arrays.asList(order,secondOrder)));
        replay(orderRepository);

        Iterable<Order> ordersSaved = orderService.saveAll(ordersData);

        assertThat(ordersSaved).isNotNull().containsExactlyInAnyOrder(order,secondOrder);
        verify(orderRepository);
    }

    @Test
    void saveAllWithNullEntityTest() {
        Order order = new Order();
        order.setId(1L);
        Order secondOrder = null;
        HashSet<Order> ordersData = new HashSet<>(Arrays.asList(order,secondOrder));

        expect(orderRepository.saveAll(ordersData)).andThrow(new IllegalArgumentException("One of orders is null"));
        replay(orderRepository);

        assertThatThrownBy(() -> orderService.saveAll(ordersData)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("One of orders is null");

    }

    @Test
    void addItemToOrderTest() throws NotFoundException {
        Order order = new Order();
        order.setId(1L);

        Item item = new Item();
        item.setId(1L);

        expect(orderRepository.save(order)).andReturn(order);
        expect(orderRepository.findById(1L)).andReturn(Optional.of(order));
        replay(orderRepository);

        boolean result = orderService.addItemToOrder(item,order);
        Order returnedOrder = orderService.findById(1L);

        assertAll("Checking properties of returned values",
                () -> assertThat(result).isTrue(),
                () -> assertThat(returnedOrder).isInstanceOf(Order.class).isNotNull()
        .isEqualTo(order),
                () -> assertThat(returnedOrder.getItems().get(0)).isInstanceOf(Item.class)
        .isNotNull().isEqualTo(item));

        verify(orderRepository);
    }

    @Test
    void addItemToOrderNullItemTest() {
        Order order = new Order();
        order.setId(1L);

        Item item = null;

        boolean result = orderService.addItemToOrder(item,order);

        assertThat(result).isFalse();
    }

    @Test
    void addItemToOrderNullOrderTest() {
        Order order = null;
        Item item = new Item();
        item.setId(1L);

        boolean result = orderService.addItemToOrder(item,order);

        assertThat(result).isFalse();
    }

    @Test
    void getOrderTotalValueTest() throws NotFoundException {
        Order order = new Order();
        order.setId(1L);
        Item item = new Item("Banana",25D);
        Item secondItem = new Item("Ball",1.11D);

        Order orderWithItemsAdded = new Order();
        orderWithItemsAdded.getItems().add(item);
        orderWithItemsAdded.getItems().add(secondItem);

        expect(orderRepository.findById(1L)).andReturn(Optional.of(orderWithItemsAdded));
        replay(orderRepository);

        orderService.addItemToOrder(item,order);
        orderService.addItemToOrder(secondItem,order);

        Order returnedOrder = orderService.findById(1L);
        double result = orderService.getOrderTotalValue(returnedOrder);

        assertAll("Checking properties of returned values",
                () -> assertThat(result).isInstanceOf(Double.class).isEqualTo(26.11D, Offset.offset(0D)),
                () -> assertThat(returnedOrder).isInstanceOf(Order.class).isEqualTo(orderWithItemsAdded).isNotNull());

        verify(orderRepository);
    }

    @Test
    void getOrderTotalValueNoItemsTest() throws NotFoundException {
        Order order = new Order();
        order.setId(1L);

        expect(orderRepository.findById(1L)).andReturn(Optional.of(order));
        replay(orderRepository);

        Order returnedOrder = orderService.findById(1L);
        double result = orderService.getOrderTotalValue(returnedOrder);

        assertAll("Checking properties of returned values",
                () -> assertThat(result).isInstanceOf(Double.class).isEqualTo(0D, Offset.offset(0D)),
                () -> assertThat(returnedOrder).isInstanceOf(Order.class).isEqualTo(order).isNotNull());

        verify(orderRepository);
    }

    @Test
    void getOrderTotalValueNullInputTest() {
        Order order = null;

        double result = orderService.getOrderTotalValue(order);

        assertThat(result).isEqualTo(0D,Offset.offset(0D));

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
