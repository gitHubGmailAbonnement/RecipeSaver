package com.recipe.managers;

import com.recipe.fetcher.DataFetcher;
import com.recipe.parsers.HTMLRecipeParser;
import com.recipe.processors.HtmlDataProcessor;
import com.recipe.dtos.Ingredient;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * STEPS
 * fetch data
 * parse data
 * process data
 * save data
 */
@Service
public class JsoupRecipeManagerDocumentImpl implements RecipeManager {
    @Autowired
    DataFetcher<Document> fetcher;
    @Autowired
    HTMLRecipeParser<Document, Node> parser;
    @Autowired
    HtmlDataProcessor<Node> processor;

    public JsoupRecipeManagerDocumentImpl(DataFetcher<Document> fetcher, HTMLRecipeParser<Document, Node> parser, HtmlDataProcessor<Node> processor) {
        this.fetcher = fetcher;
        this.parser = parser;
        this.processor = processor;
    }

    @Override
    public void manage(String url) {

        Document result = fetcher.fetchData(url);
        Node parsedData = parser.parseHTMLData(result);
        List<Ingredient> ingredients = processor.processData(parsedData);

    }

}
