package com.ezgroceries.shoppinglist.mocks;

import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListManager;
import com.ezgroceries.shoppinglist.internal.shoppinglist.ShoppingListResource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MockedShoppingListManager implements ShoppingListManager {

    @Override
    public List<ShoppingListResource> getAllShoppingLists() {

        ShoppingListResource shoppingList1 = new ShoppingListResource(
                UUID.fromString("4ba92a46-1d1b-4e52-8e38-13cd56c7224c"),
                "Stephanie's Birthday",
                Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"));

        ShoppingListResource shoppingList2 = new ShoppingListResource(
                UUID.fromString("6c7d09c2-8a25-4d54-a979-25ae779d2465"),
                "My Birthday",
                Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"));

        return Arrays.asList(shoppingList1, shoppingList2);
    }

    @Override
    public ShoppingListResource getShoppingList(UUID id) {
        return new ShoppingListResource(id, "Stephanie's Birthday",
                Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt", "Blue Curacao"));
    }

    @Override
    public ShoppingListResource createShoppingList(String name) {
        ShoppingListResource shoppingListResource = new ShoppingListResource(name);
        shoppingListResource.setShoppingListId(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"));
        return shoppingListResource;
    }

}
