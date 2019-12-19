package com.ezgroceries.persistence;

import com.ezgroceries.service.CocktailEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CocktailRepository extends CrudRepository<CocktailEntity, UUID> {
    public CocktailEntity findFirstById(UUID id);
    public CocktailEntity findFirstByName(String name);
    public CocktailEntity findFirstByIdDrink(String id_drink);
}
