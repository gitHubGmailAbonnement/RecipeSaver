package com.recipe.parsers;

public interface HTMLRecipeParser<T, E> {
    E parseHTMLData(T data);
}
