package com.example.nazarkorchak.kitchenassistant.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nazarkorchak.kitchenassistant.R;
import com.example.nazarkorchak.kitchenassistant.events.LoadRecipeInfoEvent;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class RecipeInfoActivity extends AppCompatActivity {

    //http://food2fork.com/api/get?key=7a082635d1596f0153b10c1ce1517bbd&rId=975e33
    private ImageView image;
    private String image_id;
    private TextView publisher;
    private TextView ingradients;
    private Toolbar toolbar;
    private List<String> ingredients = new ArrayList<String>();

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onEventMainThread(LoadRecipeInfoEvent event) {
        if (event != null) {
            image_id = event.recipeInfo.getImage_url();
            toolbar.setTitle(event.recipeInfo.getTitle());
            publisher.setText(event.recipeInfo.getPublisher());
            ingredients.addAll(event.recipeInfo.getIngredients());

            Glide.with(getApplicationContext()).load(image_id)
                    .centerCrop()
                    .into(image);

            StringBuilder builder = new StringBuilder();
            for (String ingradient : ingredients) {
                builder.append(ingradient + "\n");
            }

            ingradients.setText(builder.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = (ImageView) findViewById(R.id.image_recipe);
        publisher = (TextView) findViewById(R.id.publisher);
        ingradients = (TextView) findViewById(R.id.ingradients);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FullScreenActivity.class);
                intent.putExtra("image_id", image_id);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


}
