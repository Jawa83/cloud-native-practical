package com.ezgroceries.shoppinglist.mocks;

import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListManager;
import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListResource;
import java.util.Arrays;
import java.util.UUID;

public class MockedShoppingListManager implements ShoppingListManager {

    @Override
    public ShoppingListResource getShoppingList(UUID id) {
        return new ShoppingListResource(UUID.randomUUID(), "Stephanie's Birthday",
                Arrays.asList("tequilla", "Triple sec", "Lime juice", "Salt", "Blue Curacao"));
    }

    @Override
    public ShoppingListResource createShoppingList(String name) {
        ShoppingListResource shoppingListResource = new ShoppingListResource(name);
        shoppingListResource.setShoppingListId(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"));
        return shoppingListResource;
    }

}
