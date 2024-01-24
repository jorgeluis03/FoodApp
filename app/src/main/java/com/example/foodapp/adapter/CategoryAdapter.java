package com.example.foodapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.foodapp.R;
import com.example.foodapp.activities.ListFoodsActivity;
import com.example.foodapp.domain.Category;
import com.example.foodapp.domain.Foods;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(Context context, ArrayList<Category> items) {
        this.context = context;
        this.items=items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = items.get(position);
        holder.titleTxt.setText(category.getName());

        switch (position){
            case 0:{
                holder.pic.setBackgroundResource(R.drawable.category_background);
                break;
            }
            case 1:{
                holder.pic.setBackgroundResource(R.drawable.category1_background);
                break;
            }
            case 2:{
                holder.pic.setBackgroundResource(R.drawable.category2_background);
                break;
            }
            case 3:{
                holder.pic.setBackgroundResource(R.drawable.category3_background);
                break;
            }
            case 4:{
                holder.pic.setBackgroundResource(R.drawable.category4_background);
                break;
            }
            case 5:{
                holder.pic.setBackgroundResource(R.drawable.category5_background);
                break;
            }
            case 6:{
                holder.pic.setBackgroundResource(R.drawable.category6_background);
                break;
            }
            case 7:{
                holder.pic.setBackgroundResource(R.drawable.category7_background);
                break;
            }
        }

        int drawableResourseId=context.getResources().getIdentifier(category.getImagePath() /*se utiliza para obtener el identificador del recurso drawable asociado a una imagen en función de su nombre o ruta.*/
                ,"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourseId)
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ListFoodsActivity.class);
            intent.putExtra("CategoryId",category.getId());
            intent.putExtra("CategoryName",category.getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.categoryNameTxt);
            pic=itemView.findViewById(R.id.imgCategory);
        }
    }
}
