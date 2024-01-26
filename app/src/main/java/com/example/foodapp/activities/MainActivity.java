package com.example.foodapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.foodapp.R;
import com.example.foodapp.adapter.BestFoodsAdapter;
import com.example.foodapp.adapter.CategoryAdapter;
import com.example.foodapp.databinding.ActivityMainBinding;
import com.example.foodapp.domain.Category;
import com.example.foodapp.domain.Foods;
import com.example.foodapp.domain.Location;
import com.example.foodapp.domain.Price;
import com.example.foodapp.domain.Time;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initLocation();
        initTime();
        initPrice();
        initBestFood();
        initCategory();
        setVariable();
    }//onCreate**********************

    private void setVariable() {
        binding.logoutBtn.setOnClickListener(v -> {
            auth.signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        binding.searchBtn.setOnClickListener(v -> {
            String textSearch = binding.searchEdt.getText().toString();
            if(!textSearch.isEmpty()){
                Intent intent = new Intent(MainActivity.this,ListFoodsActivity.class);
                intent.putExtra("searchTxt",textSearch);
                intent.putExtra("isSearch",true);
                startActivity(intent);
            }
        });

        binding.cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));

    }

    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");
        binding.progressBar2.setVisibility(View.VISIBLE);
        ArrayList<Category> list = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {/*para obtener los datos del caché del disco local inmediatamente.
        Esto es útil para datos que solo deben cargarse una vez y no se espera que cambien con frecuencia*/
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot child : snapshot.getChildren()) {
                        list.add(child.getValue(Category.class));
                    }
                    if (list.size()>0){
                        binding.recyclerView2.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
                        CategoryAdapter adapter = new CategoryAdapter(MainActivity.this,list);
                        binding.recyclerView2.setAdapter(adapter);
                    }
                    binding.progressBar2.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void initBestFood() {
        DatabaseReference myRef = database.getReference("Foods");
        binding.progressBarBestFood.setVisibility(View.VISIBLE);
        ArrayList<Foods> list = new ArrayList<>();
        Query query = myRef.orderByChild("BestFood").equalTo(true);

        query.addListenerForSingleValueEvent(new ValueEventListener() {/*para obtener los datos del caché del disco local inmediatamente.
        Esto es útil para datos que solo deben cargarse una vez y no se espera que cambien con frecuencia*/
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot child : snapshot.getChildren()) {
                        list.add(child.getValue(Foods.class));
                    }
                    if (list.size()>0){
                        binding.recyclerViewBestFood.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        BestFoodsAdapter adapter = new BestFoodsAdapter(MainActivity.this,list);
                        binding.recyclerViewBestFood.setAdapter(adapter);
                    }
                    binding.progressBarBestFood.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initLocation() {
        DatabaseReference myRef=database.getReference("Location");//apunta directamente a la ubicación "Location".
        ArrayList<Location> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {//obtener los datos del caché del disco local inmediatamente.
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot child : snapshot.getChildren()) {
                        list.add(child.getValue(Location.class));
                    }

                    ArrayAdapter<Location> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list); /*para configurar un Spinner en Android, que es un elemento de interfaz de usuario que permite seleccionar un elemento de una lista desplegable*/
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.locationSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initTime() {
        DatabaseReference myRef=database.getReference("Time");//apunta directamente a la ubicación "Location".
        ArrayList<Time> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {//obtener los datos del caché del disco local inmediatamente.
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot child : snapshot.getChildren()) {
                        list.add(child.getValue(Time.class));
                    }

                    ArrayAdapter<Time> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.timeSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void initPrice() {
        DatabaseReference myRef=database.getReference("Price");//apunta directamente a la ubicación "Location".
        ArrayList<Price> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {//obtener los datos del caché del disco local inmediatamente.
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot child : snapshot.getChildren()) {
                        list.add(child.getValue(Price.class));
                    }

                    ArrayAdapter<Price> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.sp_item,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.priceSp.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}