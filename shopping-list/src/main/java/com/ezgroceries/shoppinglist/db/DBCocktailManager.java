package com.ezgroceries.shoppinglist.db;

import com.ezgroceries.shoppinglist.db.CocktailDBResponse.DrinkResource;
import com.ezgroceries.shoppinglist.internal.cocktail.CocktailManager;
import com.ezgroceries.shoppinglist.internal.cocktail.CocktailResource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("db")
public class DBCocktailManager implements CocktailManager {

    @Autowired
    CocktailDBClient cocktailDBClient;

    @Override
    public List<CocktailResource> getCocktails(String search) {
        return cocktailDBClient.searchCocktails(search).getDrinks().stream()
                .map(this::mapToCocktailResource)
                .collect(Collectors.toList());
    }

    private CocktailResource mapToCocktailResource(DrinkResource drink) {
        return new CocktailResource(
                UUID.nameUUIDFromBytes(drink.getIdDrink().getBytes()),
                drink.getStrDrink(),
                drink.getStrGlass(),
                drink.getStrInstructions(),
                drink.getStrDrinkThumb(),
                Arrays.asList(drink.getStrIngredient1(), drink.getStrIngredient2(), drink.getStrIngredient3())
        );
    }
}
