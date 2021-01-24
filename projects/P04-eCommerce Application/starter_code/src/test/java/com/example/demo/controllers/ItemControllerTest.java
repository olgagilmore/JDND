package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
//import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
//import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {
    private ItemController itemController;

    private ItemRepository itemRepo = mock(ItemRepository.class);

    //private CartRepository cartRepo = mock(CartRepository.class);
    //private BCryptPasswordEncoder encoder = mock(BCryptPasswordEncoder.class);

    @Before
    public void setUp() {
        itemController = new ItemController();
        TestUtils.injectObject(itemController, "itemRepository", itemRepo);
    }

    @Test
    public void get_itemByName_happy_path() throws Exception {
        Item item = new Item();
        item.setName("Great Item");
        item.setPrice(BigDecimal.valueOf(5.75));

        List<Item> itemList = Arrays.asList(item);
        when(itemRepo.findByName("Great Item")).thenReturn(itemList);

        final ResponseEntity<List<Item>> response = itemController.getItemsByName("Great Item");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

        List<Item> items = response.getBody();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals("Great Item", items.get(0).getName());

        assertEquals( BigDecimal.valueOf(5.75), items.get(0).getPrice());
    }
}
