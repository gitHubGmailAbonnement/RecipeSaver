package com.recipe.services;

import com.recipe.dao.entities.Ingredient;
import com.recipe.dao.entities.Recipe;
import com.recipe.dao.entities.RecipeIngredients;
import com.recipe.dao.repositories.IngredientRepository;
import com.recipe.dao.repositories.RecipeRepository;
import com.recipe.dtos.IngredientDTO;
import com.recipe.dtos.RecipeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecipeActionsServiceImplTest {

    RecipeActionsService recipeActionsService;

    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    @BeforeEach
    void setUp()
    {
        recipeActionsService = new RecipeActionsServiceImpl(ingredientRepository, recipeRepository);
    }

    @Test
    void saveRecipe() {

        IngredientDTO tomato = IngredientDTO.builder().unity("").quantity("1").ingreident("tomato").build();
        IngredientDTO cucumber = IngredientDTO.builder().unity("").quantity("1").ingreident("cucumber").build();
        RecipeDTO recipe = new RecipeDTO();
        recipe.setIngredients(Set.of(tomato, cucumber));

        recipeActionsService.saveRecipe(recipe);
        System.out.println("YO");
    }
}