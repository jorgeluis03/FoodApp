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
import com.example.foodapp.helper.ManagmentCart;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private Foods food;
    private int num=1;
    private ManagmentCart managmentCart;

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
        managmentCart = new ManagmentCart(DetailActivity.this);

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
        //*************** set texts *************


        binding.plusBtn.setOnClickListener(v -> {
            num = num+1;
            binding.numTxt.setText(num+"");
            binding.totalPriceTxt.setText("$"+(num*food.getPrice()));
        });

        binding.removeBtn.setOnClickListener(v -> {
            if(num>1){
                num = num-1;
                binding.numTxt.setText(num+"");
                binding.totalPriceTxt.setText("$"+(num*food.getPrice()));
            }
        });

        binding.addBtn.setOnClickListener(v -> {
            food.setNumberInCart(num);
            managmentCart.insertFood(food);
        });
    }

    private void getIntentExtras() {
        food = (Foods) getIntent().getSerializableExtra("FoodObjetc");
        
    }
}