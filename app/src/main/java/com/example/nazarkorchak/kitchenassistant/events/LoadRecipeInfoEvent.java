package com.example.nazarkorchak.kitchenassistant.events;


import com.example.nazarkorchak.kitchenassistant.model.RecipeInfo;

public class LoadRecipeInfoEvent {

    public RecipeInfo recipeInfo;

    public LoadRecipeInfoEvent(RecipeInfo recipeInfo) {
        this.recipeInfo = recipeInfo;
    }
}
