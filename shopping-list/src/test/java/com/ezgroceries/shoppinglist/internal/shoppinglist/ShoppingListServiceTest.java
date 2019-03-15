package com.ezgroceries.shoppinglist.internal.shoppinglist;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ezgroceries.shoppinglist.exceptions.ResourceAlreadyExistsException;
import com.ezgroceries.shoppinglist.internal.cocktail.CocktailEntity;
import com.ezgroceries.shoppinglist.internal.cocktail.CocktailRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

public class ShoppingListServiceTest {

    private ShoppingListService shoppingListService;
    private ShoppingListRepository shoppingListRepository;
    private CocktailRepository cocktailRepository;

    private ShoppingListEntity dummyShoppingListEntity;
    private CocktailEntity dummyCocktailEntity;
    private CocktailShoppingListEntity dummyCocktailShoppingListEntity;

    @Before
    public void setUp() throws Exception {
        shoppingListRepository = mock(ShoppingListRepository.class);
        cocktailRepository = mock(CocktailRepository.class);
        shoppingListService = new ShoppingListService(shoppingListRepository, cocktailRepository);

        dummyCocktailShoppingListEntity = new CocktailShoppingListEntity(UUID.randomUUID(), UUID.randomUUID());

        dummyShoppingListEntity = new ShoppingListEntity();
        dummyShoppingListEntity.setName("test");

        List<CocktailShoppingListEntity> dummyCocktailShoppingList = new ArrayList<>();
        dummyCocktailShoppingList.add(dummyCocktailShoppingListEntity);
        dummyShoppingListEntity.setCocktails(dummyCocktailShoppingList);

        dummyCocktailEntity = new CocktailEntity();
        dummyCocktailEntity.setId(UUID.randomUUID());

    }

    @Test(expected = ResourceAlreadyExistsException.class)
    public void createExistingShoppingList() {
        when(shoppingListRepository.findFirstByNameIgnoreCase("test"))
                .thenReturn(Optional.of(dummyShoppingListEntity));

        shoppingListService.createShoppingList(dummyShoppingListEntity);
    }

    @Test
    public void createNewShoppingList() {
        when(shoppingListRepository.findFirstByNameIgnoreCase("test"))
                .thenReturn(Optional.empty());

        shoppingListService.createShoppingList(dummyShoppingListEntity);
        verify(shoppingListRepository).save(dummyShoppingListEntity);
    }

    @Test(expected = RuntimeException.class)
    public void addNotExistingCocktailsToShoppingList() {
        when(shoppingListRepository.findById(isA(UUID.class))).thenReturn(Optional.of(dummyShoppingListEntity));
        when(cocktailRepository.findFirstByIdDrink(isA(String.class))).thenReturn(Optional.empty());

        shoppingListService.addCocktailsToShoppingList(UUID.randomUUID(), Arrays.asList("test1", "test2"));
    }

    @Test(expected = RuntimeException.class)
    public void addCocktailsToNotExistingShoppingList() {
        when(shoppingListRepository.findById(isA(UUID.class))).thenReturn(Optional.empty());
        when(cocktailRepository.findFirstByIdDrink(isA(String.class))).thenReturn(Optional.of(dummyCocktailEntity));

        shoppingListService.addCocktailsToShoppingList(UUID.randomUUID(), Arrays.asList("test1", "test2"));
    }

    @Test
    public void addCocktailsToShoppingList() {
        when(shoppingListRepository.findById(isA(UUID.class))).thenReturn(Optional.of(dummyShoppingListEntity));
        when(cocktailRepository.findFirstByIdDrink(isA(String.class))).thenReturn(Optional.of(dummyCocktailEntity));

        shoppingListService.addCocktailsToShoppingList(UUID.randomUUID(), Arrays.asList("test1", "test2"));

        verify(shoppingListRepository).save(any(ShoppingListEntity.class));
    }

    @Test(expected = RuntimeException.class)
    public void getNotExistingShoppingList() {
        when(shoppingListRepository.findById(isA(UUID.class))).thenReturn(Optional.empty());

        shoppingListService.getShoppingList(UUID.randomUUID());
    }

    @Test
    public void getExistingShoppingList() {
        when(shoppingListRepository.findById(isA(UUID.class))).thenReturn(Optional.of(dummyShoppingListEntity));

        assertEquals(dummyShoppingListEntity, shoppingListService.getShoppingList(UUID.randomUUID()));
    }

    @Test
    public void getAllShoppingLists() {
        when(shoppingListRepository.findAll()).thenReturn(Arrays.asList(dummyShoppingListEntity, dummyShoppingListEntity));

        List<ShoppingListEntity> shoppingListList = shoppingListService.getAllShoppingLists();
        assertEquals(shoppingListList.get(0), dummyShoppingListEntity);
        assertEquals(shoppingListList.get(1), dummyShoppingListEntity);
    }
}