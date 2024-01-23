package com.example.foodapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();

    }

    private void setVariable() {
        binding.loginBtn.setOnClickListener(view -> {
            String email=binding.userEdt.getText().toString();
            String password=binding.passEdt.getText().toString();

            if (!email.isEmpty() && !password.isEmpty()){
                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this, task -> {
                            if(task.isSuccessful()){
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }else {
                                Toast.makeText(this, "Autenticación fallida", Toast.LENGTH_SHORT).show();
                            }
                        });
            }else {
                Toast.makeText(this, "por favor llene su correo y contraseña", Toast.LENGTH_SHORT).show();
            }



        });
    }
}