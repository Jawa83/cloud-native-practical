package com.ezgroceries.shoppinglist.db;

import java.util.List;
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
    }
}
