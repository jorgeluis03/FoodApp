package com.example.foodapp.adapter;

import android.content.Context;
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
import com.example.foodapp.domain.Foods;
import com.example.foodapp.helper.ChangeNumberItemsListener;
import com.example.foodapp.helper.ManagmentCart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    ArrayList<Foods> list;
    private ManagmentCart managmentCart;
    Context context;


    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<Foods> list, Context context,ChangeNumberItemsListener changeNumberItemsListener) {
        this.list = list;
        this.context=context;
        managmentCart = new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Foods food = list.get(position);

        holder.title.setText(food.getTitle());
        holder.freeEachItem.setText("$"+(food.getNumberInCart()*food.getPrice()));
        holder.totalEachItem.setText(food.getNumberInCart()+" * $"+food.getPrice());
        holder.num.setText(food.getNumberInCart()+"");
        Glide.with(context)
                .load(food.getImagePath())
                .transform(new CenterCrop(),new RoundedCorners(30))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(v -> {
            managmentCart.plusNumberItem(list, position, () -> {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            });
        });

        holder.minusItem.setOnClickListener(v -> {
            managmentCart.minusNumberItem(list, position, () -> {
                notifyDataSetChanged();
                changeNumberItemsListener.change();
            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, freeEachItem, plusItem, minusItem,totalEachItem,num;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            freeEachItem = itemView.findViewById(R.id.freeEachItem);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartbtn);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            pic = itemView.findViewById(R.id.pic);
            num=itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
