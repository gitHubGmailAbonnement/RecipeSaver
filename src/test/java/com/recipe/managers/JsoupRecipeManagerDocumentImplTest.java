package com.recipe.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.recipe.fetcher.DataFetcher;
import com.recipe.fetcher.DataFetcherDocumentImpl;
import com.recipe.fetcher.JsoupWrapper;
import com.recipe.parsers.HTMLJsoupIngredientParserImpl;
import com.recipe.parsers.HTMLRecipeParser;
import com.recipe.processors.HtmlDataProcessor;
import com.recipe.processors.HtmlJSoupIngredientProcessorImpl;
import com.recipe.processors.HtmlJSoupStepsProcessorImpl;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.Test;

class JsoupRecipeManagerDocumentImplTest {

    private  DataFetcher<Document> fetcher;

    private  HTMLRecipeParser<Document, JsonNode> parser;
    //TODO deplacer les processor dans les manageer et appeler les manager (ingrdient et step) depuis cette classe

    private  HtmlDataProcessor<Node> ingredientParser;

    private  HtmlDataProcessor<Node> stepsParser;

    private RecipeManager manager;

    @Test
    //TODO this is not a good test, need mocks
    void manage() {
        fetcher = new DataFetcherDocumentImpl(2000, new JsoupWrapper());
        parser = new HTMLJsoupIngredientParserImpl();
        ingredientParser = new HtmlJSoupIngredientProcessorImpl();
        stepsParser = new HtmlJSoupStepsProcessorImpl();
        manager = new JsoupRecipeManagerDocumentImpl(fetcher, parser, ingredientParser);
        manager.manage("https://biancazapatka.com/en/red-lentil-dahl/");

    }
}