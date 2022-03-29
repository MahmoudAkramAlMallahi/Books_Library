package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_create_category extends AppCompatActivity {

    EditText et_categoryName;
    Button btn_addCategory;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);
        setTitle("Create New Category");

        et_categoryName=findViewById(R.id.category_et);
        btn_addCategory=findViewById(R.id.category_btn_add);

        dataBase=new DataBase(this);

        btn_addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameCategory=et_categoryName.getText().toString();
                if (!(nameCategory.isEmpty())){
                    Category category=new Category();
                    category.setCategoryName(nameCategory);
                    dataBase.insertCategory(category);
                    Toast.makeText(getBaseContext(),"Category created",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Activity_create_category.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getBaseContext(),"Category name can't be empty",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}