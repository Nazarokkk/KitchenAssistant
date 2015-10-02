package com.example.nazarkorchak.kitchenassistant.api;


import java.util.Map;

import com.example.nazarkorchak.kitchenassistant.responses.RecipeListResponse;
import retrofit.http.GET;
import retrofit.http.QueryMap;

public interface Food2ForkApi {

    @GET("/api/search")
    RecipeListResponse getRecipeList(@QueryMap Map<String, String> queryMap);


}
