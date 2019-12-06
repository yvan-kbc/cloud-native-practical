package com.ezgroceries.shoppinglist;

import com.ezgroceries.cocktail.CocktailResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Yvan Barbaix (u77439)
 * @since release/201710
 */

@RestController
@RequestMapping(produces = "application/json")
public class ShoppingListController {

    @PostMapping("/shopping-lists")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList createShoppingList(@RequestBody String name) {
        ShoppingList sl = new ShoppingList("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915", "Stephanie's birthday");
        return sl;
    }

    // -- BEGIN: local implementation for hard-coded first interface validation

    public class ShoppingList {

        private String shoppingListId;
        private String name;

        public ShoppingList(String shoppingListId, String name){
            this.shoppingListId = shoppingListId;
            this.name = name;
        }

        public String getShoppingListId() {
            return shoppingListId;
        }

        public void setShoppingListId(String shoppingListId) {
            this.shoppingListId = shoppingListId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    //--- END --------

    @PostMapping("/shopping-lists/{shoppingListId}/cocktails")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UUIDContainer> addCocktailsToShoppingList(@RequestBody String name) {
        return Arrays.asList(
                new UUIDContainer(new CocktailResource(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"),
                        "Hard-coded dummy",
                        "---",
                        "---",
                        "",
                        Arrays.asList()).getCocktailId()),
                new UUIDContainer(new CocktailResource(UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"),
                        "Hard-coded dummy",
                        "---",
                        "---",
                        "",
                        Arrays.asList()).getCocktailId()));

        //return cocktailResource.getCocktailId();

    }

    // -- BEGIN: local implementation for hard-coded first interface validation

    class UUIDContainer {
        private UUID cocktailId;
        public UUIDContainer(UUID uuid){
            this.cocktailId = uuid;
        }

        public UUID getUuid() {
            return cocktailId;
        }
    }
    //--- END --------

    @GetMapping("/shopping-lists/{shoppingListId}")
    public ShoppingListSampleResponse getShoppingList(){
        return new ShoppingListSampleResponse();
    }

    // -- BEGIN: local implementation for hard-coded first interface validation

    class ShoppingListSampleResponse {
        private UUID shoppingListId = UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f");
        private String name = "Stephanie's birthday";
        IngredientsSampleresponse ingredients = new IngredientsSampleresponse();

        public ShoppingListSampleResponse() {
        }

        public String getName() {
            return name;
        }

        public UUID getShoppingListId() {
            return shoppingListId;
        }

        public String[] getIngredients() {
            return ingredients.getIngerdients();
        }
    }

    class IngredientsSampleresponse {

        public String[] getIngerdients() {
            return new String[]{"Tequila", "Triple sec", "Lime juice", " salt", "Blue Curacao"};
        }

    }
    //--- END --------

}
