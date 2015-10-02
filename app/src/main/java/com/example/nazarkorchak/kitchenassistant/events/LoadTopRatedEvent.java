package com.example.nazarkorchak.kitchenassistant.events;

import com.example.nazarkorchak.kitchenassistant.model.Recipe;

import java.util.List;

public class LoadTopRatedEvent {
    private List<Recipe> recipes;

    public LoadTopRatedEvent(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
