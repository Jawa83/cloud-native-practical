package com.ezgroceries.shoppinglist.db;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class CocktailDBResponse {

    private List<DrinkResource> drinks;

    @Data
    public static class DrinkResource {

        private String idDrink;
        private String strDrink;
        private String strGlass;
        private String strInstructions;
        private String strDrinkThumb;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;
        // TODO ...

        public Set<String> getIngredientsAsSet() {
            Set<String> ingredientSet = new LinkedHashSet<>();
            ingredientSet.add(strIngredient1);
            ingredientSet.add(strIngredient2);
            ingredientSet.add(strIngredient3);
            return ingredientSet;
        }
    }
}
