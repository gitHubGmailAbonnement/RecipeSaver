package com.recipe.managers;

import com.fasterxml.jackson.databind.JsonNode;
import com.recipe.dtos.IngredientDTO;
import com.recipe.fetcher.DataFetcher;
import com.recipe.parsers.HTMLRecipeParser;
import com.recipe.processors.HtmlDataProcessor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class IngredientManager implements RecipeManager {

    private final DataFetcher<Document> fetcher;

    private final HTMLRecipeParser<Document, JsonNode> ingredientParser;
    //TODO deplacer les processor dans les manageer et appeler les manager (ingrdient et step) depuis cette classe

    private final HtmlDataProcessor<JsonNode, IngredientDTO> ingredientProcessor;

    public IngredientManager(DataFetcher<Document> fetcher, HTMLRecipeParser<Document, JsonNode> ingredientParser, HtmlDataProcessor<JsonNode, IngredientDTO> ingredientProcessor) {
        this.fetcher = fetcher;
        this.ingredientParser = ingredientParser;
        this.ingredientProcessor = ingredientProcessor;
    }


    @Override
    public void manage(String url) {

        Document result = fetcher.fetchData(url);
        JsonNode parsedData = ingredientParser.parseHTMLData(result);
        List<IngredientDTO> ingredients = ingredientProcessor.processData(parsedData);
        System.out.println(ingredients.toString());

    }

}
