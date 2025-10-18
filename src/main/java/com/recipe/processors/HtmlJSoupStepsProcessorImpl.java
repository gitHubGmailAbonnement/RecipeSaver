package com.recipe.processors;

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
public class HtmlJSoupStepsProcessorImpl implements HtmlDataProcessor<Node> {

    private final String INGRDIENT_DATA_NOT_FOUND= "NOT_FOUND";
    @Override
    public List<IngredientDTO> processData(Node data) {
        if (!Objects.isNull(data)) {
            return this.filterResult(this.process(data));
        }
        return new ArrayList<>();
    }

    private List<IngredientDTO> process(Node data)
    {
        List<IngredientDTO> ingredientDTOS = new ArrayList<>();
        for (int index = 0; index<data.childNodeSize(); index++)
        {
            Node element = data.childNode(index);
            String quantity=INGRDIENT_DATA_NOT_FOUND;
            String unity = INGRDIENT_DATA_NOT_FOUND;
            StringBuilder ingredientValue = new StringBuilder();
            for(Node node : element.childNodes())
            {
                if(node instanceof Element elm)
                {
                    String elementText = elm.tagName("span").ownText();
                    if(HTMLDataParserHelper.isIngredientUnit(elementText))
                    {
                        unity = elementText;
                    }
                    else if(HTMLDataParserHelper.isNumeric(elementText) || HTMLDataParserHelper.isNumericFranction(elementText))
                    {
                        quantity=elementText;
                    }
                    else {
                        ingredientValue.append(elementText);
                    }
                }

            }
            if(ingredientValue.length()==0)
            {
                ingredientValue.append(INGRDIENT_DATA_NOT_FOUND);
            }
            ingredientDTOS.add(new IngredientDTO(quantity, unity, ingredientValue.toString()));
        }

        return ingredientDTOS;
    }
    private List<IngredientDTO> filterResult(List<IngredientDTO> ingredientDTOS)
    {
        return ingredientDTOS
                .stream()
                .filter(ingredient -> (!Objects.equals(ingredient.getIngreident(), INGRDIENT_DATA_NOT_FOUND)))
                .collect(Collectors.toList());
    }
}
