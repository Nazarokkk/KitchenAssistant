package com.example.nazarkorchak.kitchenassistant.responses;

import java.util.List;

import com.example.nazarkorchak.kitchenassistant.model.Recipe;

public class RecipeListResponse {
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
