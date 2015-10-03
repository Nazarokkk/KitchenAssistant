package com.example.nazarkorchak.kitchenassistant.responses;


import com.example.nazarkorchak.kitchenassistant.model.RecipeInfo;

public class RecipeResponse {

    private RecipeInfo recipe;

    public RecipeInfo getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeInfo recipe) {
        this.recipe = recipe;
    }
}
