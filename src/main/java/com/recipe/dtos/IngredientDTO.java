package com.recipe.dtos;

import lombok.*;

//TODO replace getter and setter by lombock
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private String quantity;
    private String unity;
    private String ingreident;
    private String notes;

    @Override
    public String toString()
    {
        return quantity + " " + unity + " " + ingreident;
    }

}
