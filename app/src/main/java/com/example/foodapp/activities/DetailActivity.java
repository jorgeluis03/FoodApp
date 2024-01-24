package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivityDetailBinding;
import com.example.foodapp.domain.Foods;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    private Foods food;
    private int num=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtras();
        setVariable();

        getWindow().setStatusBarColor(getResources().getColor(R.color.black));
    }

    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
        Glide.with(DetailActivity.this)
                .load(food.getImagePath())
                .into(binding.pic);

        binding.priceTxt.setText("$"+food.getPrice());
        binding.titleTxt.setText(food.getTitle());
        binding.desriptionTxt.setText(food.getDescription());
        binding.califiTxt.setText(food.getStar()+" calif.");
        binding.ratingBar.setRating((float) food.getStar());
        binding.totalPriceTxt.setText("$"+num*food.getPrice());
    }

    private void getIntentExtras() {
        food = (Foods) getIntent().getSerializableExtra("FoodObjetc");
        
    }
}