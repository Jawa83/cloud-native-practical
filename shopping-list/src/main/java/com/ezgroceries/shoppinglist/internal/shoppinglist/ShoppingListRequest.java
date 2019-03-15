package com.ezgroceries.shoppinglist.internal.shoppinglist;

import lombok.Data;

@Data
public class ShoppingListRequest {

    private String name;

    public ShoppingListEntity toEntity() {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setName(this.getName());
        return shoppingListEntity;
    }
}
