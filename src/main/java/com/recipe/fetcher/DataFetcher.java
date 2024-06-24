package com.recipe.fetcher;

import org.springframework.stereotype.Component;


public interface DataFetcher<T> {

T fetchData(String url);
}
