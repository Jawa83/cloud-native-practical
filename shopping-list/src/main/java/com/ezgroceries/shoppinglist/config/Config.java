package com.ezgroceries.shoppinglist.config;

import com.ezgroceries.shoppinglist.internal.cocktail.CocktailManager;
import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListManager;
import com.ezgroceries.shoppinglist.mocks.MockedCocktailManager;
import com.ezgroceries.shoppinglist.mocks.MockedShoppingListManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer {

    @Bean
    public CocktailManager cocktailManager() {
        return new MockedCocktailManager();
    }

    @Bean
    public ShoppingListManager shoppingListManager() {
        return new MockedShoppingListManager();
    }

}
