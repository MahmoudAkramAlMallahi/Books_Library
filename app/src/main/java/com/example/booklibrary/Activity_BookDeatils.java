package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_BookDeatils extends AppCompatActivity {

    RecyclerView rv;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_deatils);

        rv=findViewById(R.id.book_deatils_rv);
        dataBase=new DataBase(this);

        Intent intent =getIntent();
        int position=intent.getIntExtra(AdapterShowCategory.DATA_INTENT_POSITION,0);
        String nameCategory=intent.getStringExtra(AdapterShowCategory.DATA_INTENT_NAME);

        int categoryId=position+1;

        setTitle(nameCategory+"");

        ArrayList<Book> books=dataBase.selectItemBook(categoryId);
        AdapterShowBook adapter = new AdapterShowBook(this, books);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}