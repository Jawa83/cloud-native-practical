package com.ezgroceries.shoppinglist.internal.shoppinglist;

import java.util.List;
import java.util.UUID;
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
    }

}
