package com.example.foodapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.foodapp.R;
import com.example.foodapp.adapter.FoodListAdapter;
import com.example.foodapp.databinding.ActivityListFoodsBinding;
import com.example.foodapp.domain.Foods;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListFoodsActivity extends BaseActivity {
    ActivityListFoodsBinding binding;
    private RecyclerView.Adapter adapterListFood;
    private int categoryId;
    private String categoryName;
    private String searchTxt;
    private boolean isSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListFoodsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentExtra();
        initList();

    }

    private void initList() {
        DatabaseReference myRef=database.getReference("Foods");
        binding.progressBarListFood.setVisibility(View.VISIBLE);

        ArrayList<Foods> list = new ArrayList<>();

        Query query;
        //Si preciono el buscardor entonces isSearch=True, caso contrario presionó alguna categoria
        if(isSearch){
            query = myRef.orderByChild("Title").startAt(searchTxt).endAt(searchTxt+'\uf8ff');
        }else{
            query = myRef.orderByChild("CategoryId").equalTo(categoryId);
        }

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot child : snapshot.getChildren()) {
                        list.add(child.getValue(Foods.class));
                    }
                    if(list.size()>0){
                        binding.recyclerViewListFood.setLayoutManager(new GridLayoutManager(ListFoodsActivity.this,2));
                        adapterListFood = new FoodListAdapter(list,ListFoodsActivity.this);
                        binding.recyclerViewListFood.setAdapter(adapterListFood);
                    }

                    binding.progressBarListFood.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void
    getIntentExtra() {
        categoryId=getIntent().getIntExtra("CategoryId",0);
        categoryName=getIntent().getStringExtra("CategoryName");
        searchTxt=getIntent().getStringExtra("searchTxt");
        isSearch=getIntent().getBooleanExtra("isSearch",false);

        binding.titleTxt.setText(categoryName);
        binding.backBtn.setOnClickListener(v -> finish());
    }
}