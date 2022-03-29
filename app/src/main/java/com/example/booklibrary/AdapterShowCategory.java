package com.example.booklibrary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterShowCategory extends RecyclerView.Adapter<AdapterShowCategory.ShowCategoryHolder> {

    ArrayList<Category> data;
    Context context;

    static final String DATA_INTENT_POSITION="position";
    static final String DATA_INTENT_NAME="name";

    public AdapterShowCategory(Context context, ArrayList<Category> data) {
        this.context=context;
        this.data = data;
    }

    @NonNull
    @Override
    public ShowCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_library,parent,false);
        return new ShowCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder( AdapterShowCategory.ShowCategoryHolder holder, int position) {
        Category category=data.get(position);
        holder.et_name.setText(String.valueOf(category.getCategoryName()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Activity_BookDeatils.class);
                intent.putExtra(DATA_INTENT_POSITION,position);
                intent.putExtra(DATA_INTENT_NAME,category.getCategoryName());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ShowCategoryHolder extends RecyclerView.ViewHolder{

        TextView et_name;
        CardView cardView;

        public ShowCategoryHolder(View itemView) {
            super(itemView);

            et_name=itemView.findViewById(R.id.item_library_nameCategory);
            cardView=itemView.findViewById(R.id.item_library_cv);
        }
    }
}
