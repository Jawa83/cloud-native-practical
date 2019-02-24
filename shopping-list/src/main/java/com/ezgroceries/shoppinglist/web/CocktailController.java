package com.ezgroceries.shoppinglist.web;

import com.ezgroceries.shoppinglist.internal.Resources;
import com.ezgroceries.shoppinglist.internal.cocktail.CocktailManager;
import com.ezgroceries.shoppinglist.internal.cocktail.CocktailResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    private CocktailManager cocktailManager;

    public CocktailController(CocktailManager cocktailManager) {
        this.cocktailManager = cocktailManager;
    }

    @GetMapping
    public Resources<CocktailResource> getCocktails() {
        return new Resources<>(cocktailManager.getCocktails());
    }

}
