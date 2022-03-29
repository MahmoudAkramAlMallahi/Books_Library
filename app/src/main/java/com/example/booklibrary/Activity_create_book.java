package com.example.booklibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_create_book extends AppCompatActivity {

    CircleImageView civ_image;
    FloatingActionButton fab_btn;
    Spinner sp_category;
    EditText et_nameBook,et_AuthorName,et_releaseYear,et_pages;
    Button btn_create;

    Uri uri;
    DataBase dataBase;

    static final int REG_CODE_PERMISSION=1;
    static final int REG_CODE_Intent=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);
        setTitle("Add New Book");

        civ_image=findViewById(R.id.createBook_civ_image);
        fab_btn=findViewById(R.id.createBook_fab_btnImage);
        sp_category=findViewById(R.id.createBook_spinner);
        et_nameBook=findViewById(R.id.createBook_et_nameBook);
        et_AuthorName=findViewById(R.id.createBook_et_author);
        et_releaseYear=findViewById(R.id.createBook_et_release);
        et_pages=findViewById(R.id.createBook_et_pages);
        btn_create=findViewById(R.id.createBook_btn_create);

        dataBase=new DataBase(this);
        ArrayList<Category> categories=dataBase.selectCategory();

        AdapterSpinner adapter=new AdapterSpinner(this,R.layout.item_spinner,categories);
        sp_category.setAdapter(adapter);

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.permissionStatus==false){
                    ActivityCompat.requestPermissions(Activity_create_book.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REG_CODE_PERMISSION);
                }else{
                    Intent intent=new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REG_CODE_Intent);
                }
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image = String.valueOf(uri);
                int spinner = sp_category.getSelectedItemPosition()+1;
                String nameBook = String.valueOf(et_nameBook.getText().toString());
                String nameAuthor = String.valueOf(et_AuthorName.getText().toString());
                String release = String.valueOf(et_releaseYear.getText().toString());
                int releaseNum=Integer.parseInt(release);
                String pages = et_pages.getText().toString();
                Book book = new Book(image, nameBook, nameAuthor, release, pages, spinner);
                if (nameBook.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Book Name", Toast.LENGTH_LONG).show();
                } else if (nameAuthor.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Author Name", Toast.LENGTH_LONG).show();
                } else if (release.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Release Year", Toast.LENGTH_LONG).show();
                } else if (pages.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Pages Number", Toast.LENGTH_LONG).show();
                } else if (uri==null){
                    Toast.makeText(getBaseContext(), "Invalid Image,Please select valid image", Toast.LENGTH_LONG).show();
                }else if (releaseNum<1000 || releaseNum >2022){
                    Toast.makeText(getBaseContext(), "Invalid Release Year,Please Enter valid Release Year", Toast.LENGTH_LONG).show();
                } else {
                    dataBase.insertBook(book);
                    Toast.makeText(Activity_create_book.this, "Create Book Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Activity_create_book.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==REG_CODE_PERMISSION && grantResults[0]==PackageManager.PERMISSION_DENIED){
            Toast.makeText(getApplicationContext(),"User Denied Storage Permission",Toast.LENGTH_LONG).show();
            MainActivity.permissionStatus=false;
        }else{
            MainActivity.permissionStatus=true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==REG_CODE_Intent && resultCode==RESULT_OK){

            uri=data.getData();
            civ_image.setImageURI(uri);
        }
    }
}