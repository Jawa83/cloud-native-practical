package com.ezgroceries.shoppinglist.internal.shoppinglist;

import java.util.List;
import java.util.UUID;

public interface ShoppingListManager {

    List<ShoppingListResource> getAllShoppingLists();

    ShoppingListResource getShoppingList(UUID id);

    ShoppingListResource createShoppingList(String name);

}
