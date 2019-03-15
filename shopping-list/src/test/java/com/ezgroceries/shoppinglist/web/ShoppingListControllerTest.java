package com.ezgroceries.shoppinglist.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ezgroceries.shoppinglist.internal.cocktail.CocktailEntity;
import com.ezgroceries.shoppinglist.internal.shoppinglist.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListEntity;
import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListService;
import com.ezgroceries.shoppinglist.web.ShoppingListController.ShoppingListCocktail;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    @MockBean
    private ShoppingListService shoppingListService;

    private final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllShoppingLists() throws Exception {


        given(shoppingListService.getAllShoppingLists()).willReturn(mockedGetAllShoppingLists());

        mockMvc.perform(get("/shopping-lists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].shoppingListId").value("4ba92a46-1d1b-4e52-8e38-13cd56c7224c"))
                .andExpect(jsonPath("$[0].name").value("Stephanie's Birthday"))
                .andExpect(jsonPath("$[0].ingredients[0]").value("Tequila"))
                .andExpect(jsonPath("$[0].ingredients[1]").value("Triple sec"))
                .andExpect(jsonPath("$[0].ingredients[2]").value("Lime juice"))
                .andExpect(jsonPath("$[0].ingredients[3]").value("Salt"))
                .andExpect(jsonPath("$[0].ingredients[4]").value("Blue Curacao"))
                .andExpect(jsonPath("$[1].shoppingListId").value("6c7d09c2-8a25-4d54-a979-25ae779d2465"))
                .andExpect(jsonPath("$[1].name").value("My Birthday"))
                .andExpect(jsonPath("$[1].ingredients[0]").value("Tequila"))
                .andExpect(jsonPath("$[1].ingredients[1]").value("Triple sec"))
                .andExpect(jsonPath("$[1].ingredients[2]").value("Lime juice"))
                .andExpect(jsonPath("$[1].ingredients[3]").value("Salt"))
                .andExpect(jsonPath("$[1].ingredients[4]").value("Blue Curacao"))
        ;

    }

    @Test
    public void getShoppingList() throws Exception {

        given(shoppingListService.getShoppingList(any(UUID.class)))
                .willReturn(mockedGetAllShoppingLists().get(0));

        mockMvc.perform(get("/shopping-lists/4ba92a46-1d1b-4e52-8e38-13cd56c7224c"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.shoppingListId").value("4ba92a46-1d1b-4e52-8e38-13cd56c7224c"))
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

        ShoppingListEntity dummyShoppingListEntity = new ShoppingListEntity();
        dummyShoppingListEntity.setName("Stephanie's birthday");
        dummyShoppingListEntity.setId(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"));

        given(shoppingListService.createShoppingList(any(ShoppingListEntity.class)))
                .willReturn(dummyShoppingListEntity);

        mockMvc.perform(post("/shopping-lists").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(dummyShoppingListEntity)))
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


    private List<ShoppingListEntity> mockedGetAllShoppingLists() {

        ShoppingListEntity shoppingList1 = new ShoppingListEntity();
        shoppingList1.setId(UUID.fromString("4ba92a46-1d1b-4e52-8e38-13cd56c7224c"));
        shoppingList1.setName("Stephanie's Birthday");
        CocktailShoppingListEntity cocktailShoppingListEntity1 =
                new CocktailShoppingListEntity(UUID.fromString("4ba92a46-1d1b-4e52-8e38-13cd56c7224c"), UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"));
        CocktailEntity cocktailEntity1 = new CocktailEntity();
        cocktailEntity1.setIngredients(Stream.of("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao").collect(Collectors.toCollection(
                LinkedHashSet::new)));
        cocktailShoppingListEntity1.setCocktail(cocktailEntity1);
        shoppingList1.setCocktails(Arrays.asList(cocktailShoppingListEntity1));

        ShoppingListEntity shoppingList2 = new ShoppingListEntity();
        shoppingList2.setId(UUID.fromString("6c7d09c2-8a25-4d54-a979-25ae779d2465"));
        shoppingList2.setName("My Birthday");
        CocktailShoppingListEntity cocktailShoppingListEntity2 =
                new CocktailShoppingListEntity(UUID.fromString("6c7d09c2-8a25-4d54-a979-25ae779d2465"), UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"));
        CocktailEntity cocktailEntity2 = new CocktailEntity();
        cocktailEntity2.setIngredients(Stream.of("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao").collect(Collectors.toCollection(
                LinkedHashSet::new)));
        cocktailShoppingListEntity2.setCocktail(cocktailEntity1);
        shoppingList2.setCocktails(Arrays.asList(cocktailShoppingListEntity1));

        return Arrays.asList(shoppingList1, shoppingList2);
    }

}