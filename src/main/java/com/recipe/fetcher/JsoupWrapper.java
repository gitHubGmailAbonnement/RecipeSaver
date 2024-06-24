package com.recipe.fetcher;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsoupWrapper {
    public Document connect(String url, int timeout) throws IOException {
        return Jsoup.connect(url).timeout(timeout).get();
    }
}
