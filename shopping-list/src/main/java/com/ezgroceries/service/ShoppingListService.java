package com.ezgroceries.service;

import com.ezgroceries.persistence.ShoppingListRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    //public ShoppingListResource create(ShoppingListResource shoppingListResource) {
    //    ...
    //}
}
