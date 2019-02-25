package com.ezgroceries.shoppinglist.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListManager;
import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListRequest;
import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListResource;
import com.ezgroceries.shoppinglist.web.ShoppingListController.ShoppingListCocktail;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingListController.class)
public class ShoppingListControllerTest {

    Gson gson = new Gson();

    @MockBean
    private ShoppingListManager shoppingListManager;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getShoppingList() throws Exception {

        given(shoppingListManager.getShoppingList(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915")))
                .willReturn(new ShoppingListResource(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"), "Stephanie's Birthday",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao")));

        mockMvc.perform(get("/shopping-lists/eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.shoppingListId").value("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"))
                .andExpect(jsonPath("$.name").value("Stephanie's Birthday"))
                .andExpect(jsonPath("$.ingredients[0]").value("Tequila"))
                .andExpect(jsonPath("$.ingredients[1]").value("Triple sec"))
                .andExpect(jsonPath("$.ingredients[2]").value("Lime juice"))
                .andExpect(jsonPath("$.ingredients[3]").value("Salt"))
                .andExpect(jsonPath("$.ingredients[4]").value("Blue Curacao"))
        ;

    }

    @Test
    public void postShoppingList() throws Exception {

        ShoppingListResource dummyShoppingList = new ShoppingListResource("Stephanie's birthday");
        dummyShoppingList.setShoppingListId(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"));

        given(shoppingListManager.createShoppingList(any(String.class))).willReturn(dummyShoppingList);

        ShoppingListRequest dummyShoppingListRequest = new ShoppingListRequest();
        dummyShoppingListRequest.setName("Stephanie's birthday");

        mockMvc.perform(post("/shopping-lists").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(dummyShoppingListRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.shoppingListId").value("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"))
                .andExpect(jsonPath("$.name").value("Stephanie's birthday"))
        ;

    }

    @Test
    public void postCocktailstoShoppingList() throws Exception {

        List<ShoppingListCocktail> dummyCocktails = new ArrayList<>();
        dummyCocktails.add(new ShoppingListCocktail("23b3d85a-3928-41c0-a533-6538a71e17c4"));
        dummyCocktails.add(new ShoppingListCocktail("d615ec78-fe93-467b-8d26-5d26d8eab073"));

        mockMvc.perform(post("/shopping-lists/97c8e5bd-5353-426e-b57b-69eb2260ace3/cocktails")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(dummyCocktails)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].cocktailId").value("23b3d85a-3928-41c0-a533-6538a71e17c4"))
                .andExpect(jsonPath("$[1].cocktailId").value("d615ec78-fe93-467b-8d26-5d26d8eab073"))
        ;

    }

}