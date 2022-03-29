package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Activity_updateBook extends AppCompatActivity {

    CircleImageView civ_image;
    FloatingActionButton fab_btn;
    EditText et_nameBook,et_AuthorName,et_releaseYear,et_pages,et_category;
    Button btn_update;

    Uri uri;
    DataBase dataBase;
    Book book;
    String nameOriginal;

    static final int REG_CODE_Intent=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        setTitle("Edit Book");

        civ_image=findViewById(R.id.updateBook_civ_image);
        fab_btn=findViewById(R.id.updateBook_fab_btnImage);
        et_nameBook=findViewById(R.id.updateBook_et_nameBook);
        et_AuthorName=findViewById(R.id.updateBook_et_author);
        et_releaseYear=findViewById(R.id.updateBook_et_release);
        et_pages=findViewById(R.id.updateBook_et_pages);
        et_category=findViewById(R.id.updateBook_et_category);
        btn_update=findViewById(R.id.updateBook_btn_create);



        dataBase=new DataBase(this);
        Intent intent1=getIntent();
        book=(Book) intent1.getSerializableExtra(Activity_edit_book.EDIT_EXTRA_NAMEBOOK);

        Uri uri1=Uri.parse(book.getImgBook());
        civ_image.setImageURI(uri1);
        et_nameBook.setText(book.getBookName() );
        et_AuthorName.setText(book.getAuthorName());
        et_releaseYear.setText(book.getReleaseYear());
        et_pages.setText(book.getPages());
        et_category.setText(book.getNumCategory()+"");
        nameOriginal=book.getBookName();

        uri=Uri.parse(book.getImgBook());

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REG_CODE_Intent);

            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String image1 = String.valueOf(uri);
                int category1=Integer.parseInt(et_category.getText().toString());
                String nameBook = et_nameBook.getText().toString();
                String nameAuthor = et_AuthorName.getText().toString();
                String release1 = et_releaseYear.getText().toString();
                String pages1 = et_pages.getText().toString();
//                Book book = new Book(image, nameBook, nameAuthor, release, pages,category );
                if (nameBook.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Book Name", Toast.LENGTH_LONG).show();
                } else if (nameAuthor.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Author Name", Toast.LENGTH_LONG).show();
                } else if (release1.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Release Year", Toast.LENGTH_LONG).show();
                } else if (pages1.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid Pages Number", Toast.LENGTH_LONG).show();
                } else if (uri==null){
                    Toast.makeText(getBaseContext(), "Invalid Image,Please select valid image", Toast.LENGTH_LONG).show();
                }else {
                    dataBase.updateBook(nameOriginal,image1,nameBook,nameAuthor,release1,pages1,category1);
                    Intent intent=new Intent(Activity_updateBook.this,Activity_library.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
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