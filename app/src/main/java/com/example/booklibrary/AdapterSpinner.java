package com.example.booklibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class AdapterSpinner extends BaseAdapter {

    ArrayList<Category> data;
    int item;
    Context context;

    public AdapterSpinner(Context context, int item,ArrayList<Category> data) {
        this.data = data;
        this.item = item;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=view;
        if (v==null){
            v= LayoutInflater.from(context).inflate(R.layout.item_spinner,null,false);
        }
        TextView tv_name=v.findViewById(R.id.item_spinner_tv_name);
        Category category=data.get(i);
        tv_name.setText(category.getCategoryName());
        return v;
    }
}
