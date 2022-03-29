package com.example.booklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView cv_favorites,cv_library,cv_category,cv_createBook;

    static final int REG_CODE_Ok=1;
    static boolean permissionStatus=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cv_favorites=findViewById(R.id.main_cv_favorites);
        cv_library=findViewById(R.id.main_cv_library);
        cv_category=findViewById(R.id.main_cv_createCategory);
        cv_createBook=findViewById(R.id.main_cv_createBook);

        if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REG_CODE_Ok);

        }else {
            permissionStatus=true;
        }

        cv_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Activity_favorites.class);
                startActivity(intent);
            }
        });

        cv_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Activity_library.class);
                startActivity(intent);
            }
        });

        cv_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Activity_create_category.class);
                startActivity(intent);
            }
        });

        cv_createBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Activity_create_book.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==REG_CODE_Ok && grantResults[0]==PackageManager.PERMISSION_DENIED){
            permissionStatus=false;
        }else{
            permissionStatus=true;
        }

    }
}