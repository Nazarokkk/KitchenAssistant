package com.example.nazarkorchak.kitchenassistant;

import android.app.Application;
import android.util.Log;

import com.example.nazarkorchak.kitchenassistant.api.Food2ForkApi;
import com.example.nazarkorchak.kitchenassistant.events.LoadRecipeInfoEvent;
import com.example.nazarkorchak.kitchenassistant.events.LoadTopRatedEvent;
import com.example.nazarkorchak.kitchenassistant.events.LoadTrendingEvent;
import com.example.nazarkorchak.kitchenassistant.events.RecipeClickEvent;
import com.example.nazarkorchak.kitchenassistant.events.SendSearchQueryEvent;
import com.example.nazarkorchak.kitchenassistant.events.TopRatedEvent;
import com.example.nazarkorchak.kitchenassistant.events.TrendingEvent;
import com.example.nazarkorchak.kitchenassistant.model.Recipe;
import com.example.nazarkorchak.kitchenassistant.model.RecipeInfo;
import com.example.nazarkorchak.kitchenassistant.responses.RecipeListResponse;
import com.example.nazarkorchak.kitchenassistant.responses.RecipeResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;


public class MyApplication extends Application {

    private Food2ForkApi myApi;

    @Override
    public void onCreate() {
        super.onCreate();

        EventBus.getDefault().register(this);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://food2fork.com/")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        myApi = restAdapter.create(Food2ForkApi.class);
    }

    public void onEventAsync(TopRatedEvent event) {

        Map<String, String> mMap = new HashMap<>();


        mMap.put("key", Constants.getKey());
        mMap.put("sort", "r");

        RecipeListResponse recipeListResponse = myApi.getRecipeList(mMap);
        List<Recipe> recipeList = recipeListResponse.getRecipes();

        EventBus.getDefault().post(new LoadTopRatedEvent(recipeList));

    }

    public void onEventAsync(TrendingEvent event) {

        Map<String, String> mMap = new HashMap<>();

        mMap.put("key", Constants.getKey());
        mMap.put("sort", "t");

        RecipeListResponse recipeListResponse = myApi.getRecipeList(mMap);
        List<Recipe> recipeList = recipeListResponse.getRecipes();

        EventBus.getDefault().post(new LoadTrendingEvent(recipeList));

    }

    public void onEventAsync(SendSearchQueryEvent event) {

        Map<String, String> mMap = new HashMap<>();

        mMap.put("key", Constants.getKey());
        mMap.put("q", event.getQuery());

        RecipeListResponse recipeListResponse = myApi.getRecipeList(mMap);
        List<Recipe> recipeList = recipeListResponse.getRecipes();

        EventBus.getDefault().post(new LoadTrendingEvent(recipeList));
    }


    public void onEventAsync(RecipeClickEvent event) {

        Map<String, String> mMap = new HashMap<>();

        mMap.put("key", Constants.getKey());
        mMap.put("rId", event.getId());

        RecipeResponse recipeResponse = myApi.getRecipe(mMap);
        RecipeInfo recipeInfo = recipeResponse.getRecipe();

        EventBus.getDefault().post(new LoadRecipeInfoEvent(recipeInfo));
    }

}
