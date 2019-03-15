package com.ezgroceries.shoppinglist.db;

import com.ezgroceries.shoppinglist.internal.cocktail.CocktailManager;
import com.ezgroceries.shoppinglist.internal.cocktail.CocktailResource;
import com.ezgroceries.shoppinglist.internal.cocktail.CocktailService;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("db")
public class DBCocktailManager implements CocktailManager {

    private final CocktailDBClient cocktailDBClient;

    private final CocktailService cocktailService;

    public DBCocktailManager(CocktailService cocktailService, CocktailDBClient cocktailDBClient) {
        this.cocktailService = cocktailService;
        this.cocktailDBClient = cocktailDBClient;
    }

    @Override
    public List<CocktailResource> getCocktails(String search) {
        return cocktailService.mergeCocktails(cocktailDBClient.searchCocktails(search).getDrinks());
    }

}
