package com.recipe.processors;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.recipe.dtos.IngredientDTO;
import com.recipe.parsers.HTMLJsoupIngredientParserImpl;
import com.recipe.processors.HtmlDataProcessor;
import com.recipe.processors.HtmlJSoupIngredientProcessorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class HtmlJSoupIngredientProcessorImplTest {
    HtmlDataProcessor<JsonNode, IngredientDTO> classUnderTest;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup()
    {
        classUnderTest = new HtmlJSoupIngredientProcessorImpl();
        objectMapper = new ObjectMapper();
    }


    @Test
    void testProcessData_NullInput_ReturnsEmptyList() {
        List<IngredientDTO> result = classUnderTest.processData(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testProcessData_ValidJsonArray_ReturnsStringList() throws Exception {
        String json = "[\"1 cup sugar\", \"2 eggs\", \"1 tsp vanilla\", \"150 g of flour\", \"1 l of water\"]";
        JsonNode node = objectMapper.readTree(json);

        List<IngredientDTO> result = classUnderTest.processData(node);

        assertEquals(5, result.size());
        assertEquals("1", result.get(0).getQuantity());
        assertEquals("cup", result.get(0).getUnity());
        assertEquals("sugar", result.get(0).getIngreident());
        assertEquals("2", result.get(1).getQuantity());
        assertEquals("", result.get(1).getUnity());
        assertEquals("eggs", result.get(1).getIngreident());
        assertEquals("150", result.get(3).getQuantity());
        assertEquals("g", result.get(3).getUnity());
        assertEquals("flour", result.get(3).getIngreident());
        assertEquals("1", result.get(4).getQuantity());
        assertEquals("l", result.get(4).getUnity());
        assertEquals("water", result.get(4).getIngreident());
    }

    @Test
    void testProcessData_EmptyJsonArray_ReturnsEmptyList() throws Exception {
        JsonNode node = objectMapper.readTree("[]");

        List<IngredientDTO> result = classUnderTest.processData(node);

        assertTrue(result.isEmpty());
    }

    @Test
    void testProcessData_NotArray_ReturnsEmptyList() throws Exception {
        JsonNode node = objectMapper.readTree("\"not an array\"");

        List<IngredientDTO> result = classUnderTest.processData(node);

        assertTrue(result.isEmpty());
    }

}