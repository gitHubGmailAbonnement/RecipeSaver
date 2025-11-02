package com.recipe.processors;

import com.fasterxml.jackson.databind.JsonNode;
import com.recipe.dtos.IngredientDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HtmlJSoupIngredientProcessorImpl implements HtmlDataProcessor<JsonNode, IngredientDTO> {


    // List of common units
    private static final List<String> UNITS = Arrays.asList(
            "cup", "cups", "tablespoon", "tablespoons", "tbsp", "teaspoon", "teaspoons", "tsp",
            "grams", "kg", "ml", "pinch", "ounce", "oz", "pint", "quart"
    );
    // Pattern to match quantity (fractions, decimals, integers)
    private static final Pattern QUANTITY_PATTERN = Pattern.compile("^(\\d+(?:\\s\\d+/\\d+)?(?:-\\d+(?:\\s\\d+/\\d+)?)?)\\s*(.*)$");
    @Override
    public List<IngredientDTO> processData(JsonNode data) {
        if (!Objects.isNull(data)) {
           return this.process(data);
        }
        return new ArrayList<>();
    }

    private List<IngredientDTO> process(JsonNode data) {
        List<IngredientDTO> result = new ArrayList<>();
         if(data.isArray())
         {
             for (JsonNode element : data)
             {
                 String rawData = element.asText();
                 var trimedData = rawData.trim();
                 String quantity = "", name = "", unit = "";
                 Matcher matcher = QUANTITY_PATTERN.matcher(trimedData);
                 if (matcher.find()) {
                     quantity = matcher.group(1).trim();
                     name = matcher.group(2).trim();
                     var list = Arrays.stream(name.split(" ")).toList();
                     if((list.get(0).toLowerCase().startsWith("g") && list.get(0).length()==1) || (list.get(0).toLowerCase().startsWith("l") && list.get(0).length()==1))
                     {
                         unit = list.get(0);
                         name = name.substring(unit.length()).trim();
                     }
                     else{
                         for ( var u : UNITS)
                         {
                             if(name.toLowerCase().startsWith(u))
                             {
                                 unit = u;
                                 name = name.substring(unit.length()).trim();
                                 break;
                             }
                         }
                     }

                     // Remove optional "of" after unit
                     if (name.toLowerCase().startsWith("of ")) {
                         name = name.substring(3).trim();
                     }
                     result.add(IngredientDTO.builder().ingreident(name).unity(unit).quantity(quantity).build());
                 }




             }
         }
         return result;
    }
}
