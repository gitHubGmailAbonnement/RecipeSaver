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
                if(node.isArray())
                {
                    for (JsonNode element : node)
                    {
                        if (element.has("recipeIngredient")) {

                            return element.get("recipeIngredient");
                        }
                    }

                }
                else {
                    if (node.has("recipeIngredient")) {

                        return node.get("recipeIngredient");
                    }

                }
                }

                       }

        //TODO no null
        return null ;
    }

}
