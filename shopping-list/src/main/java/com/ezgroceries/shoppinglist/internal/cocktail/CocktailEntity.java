package com.ezgroceries.shoppinglist.internal.cocktail;

import com.ezgroceries.shoppinglist.internal.utils.StringSetConverter;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    private UUID id;

    private String idDrink;

    private String name;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

}
