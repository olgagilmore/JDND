package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
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

public class CartControllerTest {
    private CartController cartController;

    private UserRepository userRepo = mock(UserRepository.class);

    private CartRepository cartRepo = mock(CartRepository.class);
    private ItemRepository itemRepo = mock(ItemRepository.class);

    @Before
    public void setUp() {
        cartController = new CartController();
        TestUtils.injectObject(cartController, "userRepository", userRepo);
        TestUtils.injectObject(cartController, "cartRepository", cartRepo);
        TestUtils.injectObject(cartController, "itemRepository", itemRepo);
    }

    @Test
    public void addToCart_happy_path() throws Exception {

        ModifyCartRequest req = new ModifyCartRequest();
        req.setUsername("test");
        req.setItemId(1L);
        req.setQuantity(1);
        User user= new User();
        user.setUsername("test");

        user.setCart(new Cart());

        Item item = new Item();
        item.setName("Great Item");
        item.setPrice(BigDecimal.valueOf(5.0));

        when(userRepo.findByUsername("test")).thenReturn(user);
        when(itemRepo.findById(1L)).thenReturn(java.util.Optional.of(item));

        final ResponseEntity<Cart> response = cartController.addTocart(req);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        Cart cart = response.getBody();
        assertNotNull(cart);
        assertEquals(1, cart.getItems().size());
        //assertEquals("test", cart.getUser().getUsername());
        assertEquals("Great Item", cart.getItems().get(0).getName());
    }
}
