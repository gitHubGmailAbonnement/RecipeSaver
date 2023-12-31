package parsers;

import Utils.HTMLDataParserHelper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

//TODO refractor method parse...trop longue
//TODO revoir le retour des null
@Component
public class HTMLJsoupIngredientParserImpl<T> implements HTMLRecipeParser<T> {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(HTMLJsoupIngredientParserImpl.class));
    @Override
    @SuppressWarnings("unchecked")
    public T parseHTMLData(T data) {
        Document rootDoc = (Document) data;
        if (!Objects.isNull(rootDoc)) {
            return (T) this.parse(rootDoc);
        }
        return null;
    }
    private Node parse(Document document)
    {
        Elements elements = document.getAllElements();
        var htmlDataIt = elements.iterator();
        int ingeredentsInLine = 0;
        int ingredientsNotInline = 0;
        LOGGER.info("Starting parsing ...");
        while (htmlDataIt.hasNext()){
            var element = htmlDataIt.next();
            Element ingredientElementRoot = null;
            String ingredientData =element.ownText();
            List<String> ingredientsData = List.of(ingredientData.split(" "));
            if(ingredientsData.size()>=2) {
                if (HTMLDataParserHelper.isNumeric(ingredientsData.get(0)) || ingredientsData.get(0).contains("/") && HTMLDataParserHelper.isNumeric(ingredientsData.get(1)) || ingredientsData.get(1).contains("/") )
                {
                    var nextIngredientElement = htmlDataIt.next();
                    String nextIngredient =nextIngredientElement.ownText();
                    System.out.println(nextIngredient);
                    List<String> nextIngredients = List.of(nextIngredient.split(" "));
                    if (HTMLDataParserHelper.isIngredientUnit(nextIngredients.get(0)))
                    {
                        ingredientElementRoot = nextIngredientElement;
                        ingredientsNotInline++;
                        LOGGER.info("Found occurence of ingredient data on multiple line. Number of occurences: "+ingredientsNotInline);

                    }
                }
                else if (HTMLDataParserHelper.isNumeric(ingredientsData.get(0)) || ingredientsData.get(0).contains("/"))
                {
                    if(HTMLDataParserHelper.isIngredientUnit(ingredientsData.get(1))) {
                        ingeredentsInLine++;
                        LOGGER.info("Found occurence of ingredient data on one line. Number of occurences: "+ingeredentsInLine);

                    }
                }

            }
            else if(ingredientsData.size() == 1 && (HTMLDataParserHelper.isNumeric(ingredientsData.get(0)) || ingredientsData.get(0).contains("/")))
            {
                var nextIngredientElement = htmlDataIt.next();
                String ingredientDataNotInline =nextIngredientElement.ownText();
                List<String> ingredientsDataNotInLine = List.of(ingredientDataNotInline.split(" "));
                if (HTMLDataParserHelper.isIngredientUnit(ingredientsDataNotInLine.get(0)))
                {

                    ingredientElementRoot = nextIngredientElement;
                    ingredientsNotInline++;
                    LOGGER.info("Found occurence of ingredient data on multiple line. Number of occurences: "+ingredientsNotInline);
                }

            }
            if(ingeredentsInLine == 2)
            {
                var parentNode = element.parentNode();
                return parentNode.parentNode();

            }
            else if(ingredientsNotInline == 2)
            {
                var parentNode = ingredientElementRoot.parentNode();
                return parentNode.parentNode();

            }

        }
        return null;
    }

}
