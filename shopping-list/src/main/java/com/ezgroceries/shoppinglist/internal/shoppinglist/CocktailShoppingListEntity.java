package com.ezgroceries.shoppinglist.internal.shoppinglist;

import com.ezgroceries.shoppinglist.internal.cocktail.CocktailEntity;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cocktail_shopping_list")
public class CocktailShoppingListEntity {

    @EmbeddedId
    CocktailShoppingListId id;

    @OneToOne(targetEntity = CocktailEntity.class)
    @JoinColumn(name = "cocktail_id", referencedColumnName = "id", insertable=false, updatable=false)
    CocktailEntity cocktail;

    public CocktailShoppingListEntity(UUID cocktailId, UUID shoppingListId) {
        this.id = new CocktailShoppingListId(cocktailId, shoppingListId);
    }

}

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
class CocktailShoppingListId implements Serializable {
    @Column(name = "cocktail_id")
    UUID cocktailId;
    @Column(name = "shopping_list_id")
    UUID shoppingListId;
}