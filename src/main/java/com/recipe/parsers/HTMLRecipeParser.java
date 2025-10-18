package com.recipe.parsers;

public interface HTMLRecipeParser<T, E> {
    //TODO use optionnal to avoid returning null
    E parseHTMLData(T data);
}
