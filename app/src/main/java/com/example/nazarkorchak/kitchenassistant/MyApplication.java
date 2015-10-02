package com.example.nazarkorchak.kitchenassistant;

import android.app.Application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.nazarkorchak.kitchenassistant.api.Food2ForkApi;
import de.greenrobot.event.EventBus;

import com.example.nazarkorchak.kitchenassistant.events.LoadTopRatedEvent;
import com.example.nazarkorchak.kitchenassistant.events.LoadTrendingEvent;
import com.example.nazarkorchak.kitchenassistant.events.TopRatedEvent;
import com.example.nazarkorchak.kitchenassistant.events.TrendingEvent;
import com.example.nazarkorchak.kitchenassistant.model.Recipe;
import com.example.nazarkorchak.kitchenassistant.responses.RecipeListResponse;
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

}
