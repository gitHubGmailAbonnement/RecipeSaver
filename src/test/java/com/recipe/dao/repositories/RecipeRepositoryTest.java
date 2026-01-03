package com.recipe.dao.repositories;

import com.recipe.dao.entities.Ingredient;
import com.recipe.dao.entities.Recipe;
import com.recipe.dao.entities.RecipeIngredients;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    void saverRecipe()
    {
        var tomato = Ingredient.builder().name("tomato").build();
        var laitue = Ingredient.builder().name("salad").build();

        var tomateIngredient = RecipeIngredients.builder().ingredient(tomato).unit("").quantity("1").build();
        var laitueIngredient = RecipeIngredients.builder().ingredient(laitue).unit("").quantity("1").build();

//        tomato.addRecipeIngredients(tomateIngredient);
//        laitue.addRecipeIngredients(laitueIngredient);

        ingredientRepository.saveAll(List.of(tomato, laitue));



        Recipe salad = Recipe.builder().title("salad").url("https://salad.com").build();
        salad.addRecipeIngredients(tomateIngredient);
        salad.addRecipeIngredients(laitueIngredient);

        recipeRepository.save(salad);
        System.out.println("YO");

    }

    @Test
    void saverRecipetest2()
    {
//        List<Ingredient> ingredients = List.of(Ingredient.builder().unity("cup").quantity("1").name("flour").build(),
//                Ingredient.builder().unity("g").quantity("100").name("sugar").build(),
//                Ingredient.builder().unity("").quantity("3").name("apples").build());
//        //Recipe recipe = Recipe.builder().url("https://recipe.com").name("recipe").ingredients(new ArrayList<>()).build();
//        Recipe result = recipeRepository.save(Recipe.builder().url("https://recipe.com").name("recipe").ingredients(ingredients).build());
//        //result.getIngredients().addAll(ingredients);
//        //recipeRepository.save(recipe);
    }
}