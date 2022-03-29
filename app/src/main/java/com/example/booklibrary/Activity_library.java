package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Activity_library extends AppCompatActivity {

    RecyclerView rv;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        setTitle("Library");

        rv=findViewById(R.id.library_rv);
        dataBase=new DataBase(this);

        ArrayList<Category> categories=dataBase.selectCategory();

        AdapterShowCategory adapter=new AdapterShowCategory(this,categories);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}