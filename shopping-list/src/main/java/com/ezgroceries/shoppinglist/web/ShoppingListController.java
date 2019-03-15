package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.internal.Resources;
import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListManager;
import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListRequest;
import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListResource;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopping-lists", produces = "application/json")
public class ShoppingListController {

    private final ShoppingListManager shoppingListManager;

    public ShoppingListController(ShoppingListManager shoppingListManager) {
        this.shoppingListManager = shoppingListManager;
    }

    @GetMapping
    public Resources<ShoppingListResource> getAllShoppingLists() {
        return new Resources<>(this.shoppingListManager.getAllShoppingLists());
    }

    @GetMapping(value = "/{shoppingListId}")
    public ShoppingListResource getShoppingList(@PathVariable String shoppingListId) {
        return shoppingListManager.getShoppingList(UUID.fromString(shoppingListId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResource postShoppingList(@RequestBody ShoppingListRequest shoppingList) {
        return shoppingListManager.createShoppingList(shoppingList.getName());
    }

    @PostMapping(value = "/{shoppingListId}/cocktails")
    public Resources<ShoppingListCocktail> postCocktailstoShoppingList(@RequestBody List<ShoppingListCocktail> cocktails) {
        return new Resources<>(cocktails);
    }

    @Data
    @NoArgsConstructor
    static class ShoppingListCocktail {
        private String cocktailId;

        ShoppingListCocktail(String cocktailId) {
            this.cocktailId = cocktailId;
        }
    }

}
