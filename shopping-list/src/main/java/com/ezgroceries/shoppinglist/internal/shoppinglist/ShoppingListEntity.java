package com.ezgroceries.shoppinglist.internal.shoppinglist;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "shopping_list")
public class ShoppingListEntity {

    @Id
    private UUID id;

    private String name;

}
