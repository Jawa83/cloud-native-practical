package com.ezgroceries.shoppinglist.internal.shoppinglist;

import com.ezgroceries.shoppinglist.exceptions.ResourceAlreadyExistsException;
import com.ezgroceries.shoppinglist.internal.cocktail.CocktailEntity;
import com.ezgroceries.shoppinglist.internal.cocktail.CocktailRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailRepository cocktailRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailRepository cocktailRepository){
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailRepository = cocktailRepository;
    }

    public ShoppingListEntity createShoppingList(ShoppingListEntity entity) {
        Optional<ShoppingListEntity> repoEntity = shoppingListRepository.findFirstByNameIgnoreCase(entity.getName());
        if (repoEntity.isPresent()) {
            throw new ResourceAlreadyExistsException();
        }
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        return shoppingListRepository.save(entity);
    }

    public ShoppingListEntity addCocktailsToShoppingList(UUID shoppingListId, List<String> idDrinks) {
        Optional<ShoppingListEntity> shoppingListOptional = shoppingListRepository.findById(shoppingListId);
        if (!shoppingListOptional.isPresent()) {
            // TODO use custom exception
            throw new RuntimeException("shopping list does not exist");
        }
        ShoppingListEntity shoppingListEntity = shoppingListOptional.get();
        for (String idDrink : idDrinks) {
            Optional<CocktailEntity> cocktailEntity = cocktailRepository.findFirstByIdDrink(idDrink);
            if (!cocktailEntity.isPresent()) {
                // TODO use custom exception
                throw new RuntimeException("cocktail does not exist");
            }
            shoppingListEntity.getCocktails().add(new CocktailShoppingListEntity(cocktailEntity.get().getId(), shoppingListId));
        }
        shoppingListRepository.save(shoppingListEntity);
        return shoppingListRepository.save(shoppingListEntity);
    }

}
