package com.recipe.processors;

import com.recipe.dtos.Ingredient;

import java.util.List;

public interface HtmlDataProcessor <T>{

    List<Ingredient> processData(T data);
}
