package com.example.foodapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.foodapp.R;
import com.example.foodapp.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends BaseActivity {
    ActivitySignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }//OnCreate**************************

    private void setVariable() {
        binding.signupBtn.setOnClickListener(view -> {
            String email=binding.userEdt.getText().toString();
            String password=binding.passEdt.getText().toString();

            if (password.length()<6){
                Toast.makeText(this, "tu contraseña deber tener 6 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(SignupActivity.this, task -> {
                        if(task.isSuccessful() ){
                            Log.i(TAG,"onComplete: ");
                            startActivity(new Intent(this, MainActivity.class));

                        }else {
                            Log.i(TAG,"onFailure: "+task.getException());
                            Toast.makeText(this, "Autenticacion fallida", Toast.LENGTH_SHORT).show();
                        }
                    });

        });
    }

}