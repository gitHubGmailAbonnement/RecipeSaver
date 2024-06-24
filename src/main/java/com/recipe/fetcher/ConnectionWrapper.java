package com.recipe.fetcher;

import java.io.IOException;

public interface ConnectionWrapper<T> {
    T connect(String url, int timeout) throws IOException;
}
