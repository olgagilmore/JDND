package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderControllerTest {
    private OrderController orderController;

    private UserRepository userRepo = mock(UserRepository.class);

    private OrderRepository orderRepo = mock(OrderRepository.class);


    @Before
    public void setUp() {
        orderController = new OrderController();
        TestUtils.injectObject(orderController, "userRepository", userRepo);
        TestUtils.injectObject(orderController, "orderRepository", orderRepo);
        //TestUtils.injectObject(userController, "bCryptPasswordEncoder", encoder);
    }

    @Test
    public void submit_order_happy_path() throws Exception {


        User user= new User();
        user.setUsername("testUser");
        Cart cart = new Cart();
        user.setCart(cart);
        cart.setUser(user);

        Item item = new Item();
        item.setName("Great Item");
        item.setPrice(BigDecimal.valueOf(5.0));
        cart.addItem(item);

        when(userRepo.findByUsername("testUser")).thenReturn(user);
        //when(orderRepo.findById(1L)).thenReturn(java.util.Optional.of(item));
        final ResponseEntity<UserOrder> response = orderController.submit("testUser");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        UserOrder order = response.getBody();
        assertNotNull(order);
        assertEquals("testUser", order.getUser().getUsername());
        assertEquals(BigDecimal.valueOf(5.0), order.getTotal());
    }
}
