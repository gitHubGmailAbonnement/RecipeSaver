package processors;

import dtos.Ingredient;

import java.util.List;

public interface HtmlDataProcessor <T>{

    List<?> processData(T data);
}
