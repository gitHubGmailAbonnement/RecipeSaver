package parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;
//TODO reoir la leture de fichie
class HTMLJsoupIngredientParserImplTest {

    HTMLRecipeParser<Document> classUnderTest;

    @BeforeEach
    void setup()
    {
        classUnderTest = new HTMLJsoupIngredientParserImpl<>();
    }
    @Test
    void parseHTMLData() throws IOException {

        Document data = getHtmlDocFromFile("/htmlData/recipeHtmlData.html");
        Node result = classUnderTest.parseHTMLData(data);
        assertNotNull(result);

    }
    @Test
    void parseHTMLDataNoRecipe() throws IOException {

        Document data = getHtmlDocFromFile("/htmlData/recipeHtmlDataNoRecipe.html");
        Node result = classUnderTest.parseHTMLData(data);
        assertNull(result);

    }
    private Document getHtmlDocFromFile(String path) throws IOException {
        return Jsoup.parse(getStringFomFile(path));
    }

    private String getStringFomFile(String path) throws IOException {
        Class clazz = HTMLJsoupIngredientParserImplTest.class;
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