package com.recipe.processors;

import com.recipe.dtos.IngredientDTO;

import java.util.List;

public interface HtmlDataProcessor <T>{

    List<IngredientDTO> processData(T data);
}
