package com.ezgroceries.persistence;

import com.ezgroceries.persistence.ShoppingListEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity, UUID> {

    public ShoppingListEntity findFirstById(UUID id);
    public ShoppingListEntity findFirstByName(String name);
}
