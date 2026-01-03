package com.recipe.dtos;

import lombok.Data;

import java.util.Set;

@Data
//TODO ajouter url
//TODO ajouter titre
public class RecipeDTO {
    Set<IngredientDTO> ingredients;
}
