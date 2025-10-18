package com.recipe.parsers;

import com.fasterxml.jackson.databind.JsonNode;
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

class HTMLJsoupIngredientParserImplTest {
    HTMLRecipeParser<Document, JsonNode> classUnderTest;

    @BeforeEach
    void setup()
    {
        classUnderTest = new HTMLJsoupIngredientParserImpl();
    }
    @Test
    void parseHTMLDataWhenIngredientArePresent() throws IOException {
        Document data = getHtmlDocFromFile("/htmlData/NorecipeHtmlData.html");
        JsonNode result = classUnderTest.parseHTMLData(data);
        assertNull(result);
    }

    @Test
    void parseHTMLDataWhenIngredientAreNotPresent() throws IOException {
        Document data = getHtmlDocFromFile("/htmlData/recipeHtmlData.html");
        JsonNode result = classUnderTest.parseHTMLData(data);
        assertNotNull(result);
        assertEquals(21, result.size());
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