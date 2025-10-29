package com.recipe.processors;

import com.fasterxml.jackson.databind.JsonNode;
import com.recipe.dtos.IngredientDTO;
import com.recipe.utils.HTMLDataParserHelper;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class HtmlJSoupIngredientProcessorImpl implements HtmlDataProcessor<JsonNode, String> {

    private final String INGRDIENT_DATA_NOT_FOUND= "NOT_FOUND";
    @Override
    public List<String> processData(JsonNode data) {
        if (!Objects.isNull(data)) {
           return this.processData(data);
        }
        return new ArrayList<>();
    }

    private List<IngredientDTO> process(JsonNode data) {

        return null;
    }
}
