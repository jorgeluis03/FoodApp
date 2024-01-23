package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setVariable();
        getWindow().setStatusBarColor(Color.parseColor("#FFE4B5")); //establecer el color de la barra de estado.
    }

    private void setVariable() {
        binding.loginBtn.setOnClickListener(view -> {
            if(auth.getCurrentUser()!=null){
                startActivity(new Intent(this,MainActivity.class));
            }else {
                startActivity(new Intent(this, LoginActivity.class));
            }
        });

        binding.signupBtn.setOnClickListener(view -> {
            startActivity(new Intent(this,SignupActivity.class));
        });
    }
}