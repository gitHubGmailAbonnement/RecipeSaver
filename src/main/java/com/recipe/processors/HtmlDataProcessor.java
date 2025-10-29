package com.recipe.processors;

import com.recipe.dtos.IngredientDTO;

import java.util.List;

public interface HtmlDataProcessor <T, E>{

    List<E> processData(T data);
}
