package com.recipe.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

//TODO refractor method parse
//TODO revoir le retour des null
@Component
@Slf4j
@Qualifier("ingredientParser")
public class HTMLJsoupIngredientParserImpl implements HTMLRecipeParser<Document, JsonNode> {
    @Override
    public JsonNode parseHTMLData(Document data) {
        if (!Objects.isNull(data)) {
            return this.parse(data);
        }
        return null;
    }
    private JsonNode parse(Document document)
    {
        Elements scripts = document.select("script[type=application/ld+json]");
        ObjectMapper mapper = new ObjectMapper();

        for (var script : scripts) {
            String json = script.html();
            //TODO ne pas utiliser JsonNode mais plutot directement le json
            JsonNode root = null;
            try {
                root = mapper.readTree(json);
            } catch (JsonProcessingException e) {
                log.error("An error happened while parsing HTML data for website {} ", document.title());
            }

            // Some pages have an array of JSON-LD objects
            assert root != null;
            //TODO validate type is Recipe (calidate with doc of ldjson)
            for (JsonNode node : root) {
                if (node.has("@type") && node.has("recipeIngredient")) {

                    return node.get("recipeIngredient");
                }
            }
        }

//        Elements elements = document.getAllElements();
//        var htmlDataIt = elements.iterator();
//        int ingeredentsInLine = 0;
//        int ingredientsNotInline = 0;
//        LOGGER.info("Starting parsing ...");
//        while (htmlDataIt.hasNext()){
//            var element = htmlDataIt.next();
//            Element ingredientElementRoot = null;
//            String ingredientData =element.ownText();
//            List<String> ingredientsData = List.of(ingredientData.split(" "));
//            if(ingredientsData.size()>=2) {
//                if (HTMLDataParserHelper.isNumeric(ingredientsData.get(0)) || ingredientsData.get(0).contains("/") && HTMLDataParserHelper.isNumeric(ingredientsData.get(1)) || ingredientsData.get(1).contains("/") )
//                {
//                    var nextIngredientElement = htmlDataIt.next();
//                    String nextIngredient =nextIngredientElement.ownText();
//                    System.out.println(nextIngredient);
//                    List<String> nextIngredients = List.of(nextIngredient.split(" "));
//                    if (HTMLDataParserHelper.isIngredientUnit(nextIngredients.get(0)))
//                    {
//                        ingredientElementRoot = nextIngredientElement;
//                        ingredientsNotInline++;
//                        LOGGER.info("Found occurence of ingredient data on multiple line. Number of occurences: "+ingredientsNotInline);
//
//                    }
//                }
//                else if (HTMLDataParserHelper.isNumeric(ingredientsData.get(0)) || ingredientsData.get(0).contains("/"))
//                {
//                    if(HTMLDataParserHelper.isIngredientUnit(ingredientsData.get(1))) {
//                        ingeredentsInLine++;
//                        LOGGER.info("Found occurence of ingredient data on one line. Number of occurences: "+ingeredentsInLine);
//
//                    }
//                }
//
//            }
//            else if(ingredientsData.size() == 1 && (HTMLDataParserHelper.isNumeric(ingredientsData.get(0)) || ingredientsData.get(0).contains("/")))
//            {
//                var nextIngredientElement = htmlDataIt.next();
//                String ingredientDataNotInline =nextIngredientElement.ownText();
//                List<String> ingredientsDataNotInLine = List.of(ingredientDataNotInline.split(" "));
//                if (HTMLDataParserHelper.isIngredientUnit(ingredientsDataNotInLine.get(0)))
//                {
//
//                    ingredientElementRoot = nextIngredientElement;
//                    ingredientsNotInline++;
//                    LOGGER.info("Found occurence of ingredient data on multiple line. Number of occurences: "+ingredientsNotInline);
//                }
//
//            }
//            if(ingeredentsInLine == 2)
//            {
//                var parentNode = element.parentNode();
//                return parentNode.parentNode();
//
//            }
//            else if(ingredientsNotInline == 2)
//            {
//                var parentNode = ingredientElementRoot.parentNode();
//                return parentNode.parentNode();
//
//            }
//
//        }
//        return null;

        //TODO no null
        return null ;
    }

}
