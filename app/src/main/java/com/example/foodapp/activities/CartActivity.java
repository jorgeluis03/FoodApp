package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivityCartBinding;
import com.example.foodapp.helper.ManagmentCart;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        managmentCart= new ManagmentCart(this);
        
        setVarible();

    }

    private void setVarible() {
    }
}