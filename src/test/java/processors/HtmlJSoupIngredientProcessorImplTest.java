package processors;

import dtos.Ingredient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import parsers.HTMLJsoupIngredientParserImpl;
import parsers.HTMLRecipeParser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class HtmlJSoupIngredientProcessorImplTest {

    private HtmlDataProcessor<Node> classUnderTest = new HtmlJSoupIngredientProcessorImpl<>();
    private HTMLRecipeParser<Document> parser = new HTMLJsoupIngredientParserImpl<>();
    @Test
    void processData() throws IOException {
        Document data = getHtmlDocFromFile("/htmlData/recipeHtmlData.html");
        Node result = parser.parseHTMLData(data);
        List<?> ingredients = classUnderTest.processData(result);
        assertFalse(ingredients.isEmpty());
        assertEquals(5,ingredients.size());
        Ingredient firstIngredient = (Ingredient) ingredients.get(1);
        assertEquals("flour, plain / all-purpose",firstIngredient.getIngreident());
        assertEquals("1",firstIngredient.getQuantity());
        assertEquals("tbsp",firstIngredient.getUnity());
    }

    private Document getHtmlDocFromFile(String path) throws IOException {
        return Jsoup.parse(getStringFomFile(path));
    }

    private String getStringFomFile(String path) throws IOException {
        Class<? extends HtmlJSoupIngredientProcessorImplTest> clazz = HtmlJSoupIngredientProcessorImplTest.class;
        InputStream inputStream = clazz.getResourceAsStream(path);
        return readFromInputStream(inputStream);
    }

    private String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}