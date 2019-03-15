package com.ezgroceries.shoppinglist.internal.shoppinglist;

import com.ezgroceries.shoppinglist.internal.cocktail.CocktailEntity;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingListResource {

    private UUID shoppingListId;
    private String name;
    private List<String> ingredients;

    public ShoppingListResource(String name) {
        this.shoppingListId = UUID.randomUUID();
        this.name = name;
    }

    public ShoppingListResource(ShoppingListEntity entity) {
        this.shoppingListId = entity.getId();
        this.name = entity.getName();
        // TODO filter out duplicate ingredients
        this.ingredients = entity.getCocktails().stream()
                .map(CocktailShoppingListEntity::getCocktail)
                .map(CocktailEntity::getIngredients)
                .flatMap(Set::stream)
                .collect(Collectors.toList());
    }

}
