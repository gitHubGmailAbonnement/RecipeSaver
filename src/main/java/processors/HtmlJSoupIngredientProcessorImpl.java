package processors;

import Utils.HTMLDataParserHelper;
import dtos.Ingredient;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HtmlJSoupIngredientProcessorImpl<T> implements HtmlDataProcessor<T> {

    private final String INGRDIENT_DATA_NOT_FOUND= "NOT_FOUND";
    @Override
    public List<Ingredient> processData(T data) {
        Node ingredient = (Node) data;
        if (!Objects.isNull(ingredient)) {
            return this.filterResult(this.process(ingredient));
        }
        return new ArrayList<>();
    }

    private List<Ingredient> process(Node data)
    {
        List<Ingredient> ingredients = new ArrayList<>();
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
            ingredients.add(new Ingredient(quantity, unity, ingredientValue.toString()));
        }

        return ingredients;
    }
    private List<Ingredient> filterResult(List<Ingredient> ingredients)
    {
        return ingredients
                .stream()
                .filter(ingredient -> (!Objects.equals(ingredient.getIngreident(), INGRDIENT_DATA_NOT_FOUND)))
                .collect(Collectors.toList());
    }
}
