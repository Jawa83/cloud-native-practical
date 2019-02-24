package com.ezgroceries.shoppinglist.internal.shoppinglist;

import java.util.UUID;

public interface ShoppingListManager {

    ShoppingListResource getShoppingList(UUID id);

    ShoppingListResource createShoppingList(String name);

}
