package com.ezgroceries.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ezgroceries.external.CocktailDBClient;
import com.ezgroceries.external.CocktailDBResponse;
import com.ezgroceries.external.CocktailResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yvan Barbaix (u77439)
 * @since release/201710
 */
@RestController
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    private final CocktailDBClient cocktailDBClient;

    @Autowired
    public CocktailController(CocktailDBClient cocktailDBClient) {
        this.cocktailDBClient = cocktailDBClient;
    }


    @GetMapping
    public List<CocktailResource> searchCocktail(@RequestParam String search) {
        CocktailDBResponse response = this.cocktailDBClient.searchCocktails(search);
        System.out.println(response);
        List<CocktailResource> returnList = new ArrayList<>();
        for (CocktailDBResponse.DrinkResource drink:response.getDrinks()){
            CocktailResource cr = new CocktailResource(
                    UUID.randomUUID(),
                    drink.getStrDrink(),
                    drink.getStrGlass(),
                    drink.getStrInstructions(),
                    drink.getStrDrinkThumb(),
                    ingredientslist(drink));
            returnList.add(cr);
        }
        return returnList;
    }
    private List<String> ingredientslist(CocktailDBResponse.DrinkResource drink) {
        List<String> strings = Arrays.asList(
                drink.getStrIngredient1(),
                drink.getStrIngredient2(),
                drink.getStrIngredient3(),
                drink.getStrIngredient4(),
                drink.getStrIngredient5(),
                drink.getStrIngredient6(),
                drink.getStrIngredient7(),
                drink.getStrIngredient8(),
                drink.getStrIngredient9(),
                drink.getStrIngredient10(),
                drink.getStrIngredient11(),
                drink.getStrIngredient12(),
                drink.getStrIngredient13(),
                drink.getStrIngredient14(),
                drink.getStrIngredient15());
        List<String> filtered = strings.stream().filter(string -> string != null && !string.isEmpty()).collect(Collectors.toList());
        return filtered;
    }
}
