package com.ezgroceries.shoppinglist.internal.shoppinglist;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity, UUID> {

    Optional<ShoppingListEntity> findFirstByNameIgnoreCase(String name);

}
