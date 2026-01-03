package com.recipe.services;

import com.recipe.dao.entities.Ingredient;
import com.recipe.dao.entities.Recipe;
import com.recipe.dao.entities.RecipeIngredients;
import com.recipe.dao.repositories.IngredientRepository;
import com.recipe.dao.repositories.RecipeRepository;
import com.recipe.dtos.RecipeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
//revoir si c bon desing d'avoir les 2 repo ici
public class RecipeActionsServiceImpl implements RecipeActionsService {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public RecipeActionsServiceImpl(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveRecipe(RecipeDTO recipeDTO) {
        Recipe recipe = Recipe.builder().url("defautlt").title("default").build();
        for (var data : recipeDTO.getIngredients())
        {
            Ingredient ingredient = Ingredient.builder().name(data.getIngreident()).build();
            RecipeIngredients ingredientDetails = RecipeIngredients.builder().id(UUID.randomUUID()).ingredient(ingredient).quantity(data.getQuantity()).unit(data.getUnity()).build();

            recipe.addRecipeIngredients(ingredientDetails);
            ingredient.addRecipeIngredients(ingredientDetails);

        }
        recipeRepository.save(recipe);

    }
}
