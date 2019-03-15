package com.ezgroceries.shoppinglist.internal.shoppinglist;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository){
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingListEntity createShoppingList(ShoppingListEntity entity) {
        Optional<ShoppingListEntity> repoEntity = shoppingListRepository.findFirstByNameIgnoreCase(entity.getName());
        if (repoEntity.isPresent()) {
            // TODO use proper execption
            throw new RuntimeException("shopping list '" + entity.getName() + "' already exists");
        }
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        return shoppingListRepository.save(entity);
    }

}
