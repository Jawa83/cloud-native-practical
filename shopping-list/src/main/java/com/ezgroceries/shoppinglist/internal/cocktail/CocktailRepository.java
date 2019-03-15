package com.ezgroceries.shoppinglist.internal.cocktail;

import feign.Param;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CocktailRepository extends CrudRepository<CocktailEntity, UUID> {

    @Query("select c from CocktailEntity c where c.idDrink IN :ids")
    List<CocktailEntity> findByIdDrinkIn(@Param("ids") List<String> ids);

    Optional<CocktailEntity> findFirstByIdDrink(String idDrink);

}
