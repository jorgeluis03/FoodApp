package com.example.foodapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.foodapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class BaseActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    public String TAG="uilover";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        getWindow().setStatusBarColor(getResources().getColor(R.color.white)); //establecer el color de la barra de estado.
    }
}