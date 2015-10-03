package com.example.nazarkorchak.kitchenassistant.api;


import java.util.Map;

import com.example.nazarkorchak.kitchenassistant.responses.RecipeListResponse;
import com.example.nazarkorchak.kitchenassistant.responses.RecipeResponse;

import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface Food2ForkApi {

    @GET("/api/search")
    RecipeListResponse getRecipeList(@QueryMap Map<String, String> queryMap);

    @GET("/api/get")
    RecipeResponse getRecipe(@QueryMap Map<String, String> queryMap);


}
