package com.example.nazarkorchak.kitchenassistant.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.nazarkorchak.kitchenassistant.R;

import de.greenrobot.event.EventBus;

import com.example.nazarkorchak.kitchenassistant.adapter.RecipesGridAdapter;
import com.example.nazarkorchak.kitchenassistant.events.LoadTopRatedEvent;
import com.example.nazarkorchak.kitchenassistant.events.TopRatedEvent;
import com.example.nazarkorchak.kitchenassistant.model.Recipe;

import java.util.ArrayList;
import java.util.List;


public class TopRatedFragment extends Fragment {

    List<Recipe> recipeList = new ArrayList<>();
    RecipesGridAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new TopRatedEvent());
    }

    public void onEventMainThread(LoadTopRatedEvent event) {
        if (event.getRecipes() != null) {
            recipeList.addAll(event.getRecipes());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragments_grid, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.my_grid_view);
        mAdapter = new RecipesGridAdapter(getActivity(), recipeList);
        gridView.setAdapter(mAdapter);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
