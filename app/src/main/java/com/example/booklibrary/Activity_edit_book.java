package com.example.booklibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Activity_edit_book extends AppCompatActivity {

    ImageView iv_book;
    TextView tv_nameBook,tv_nameAuthor,tv_release,tv_pages,tv_category;
    Button btn_edit;
    static final String EDIT_EXTRA_NAMEBOOK="book";
    Book book;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        iv_book=findViewById(R.id.edit_book_iv_imageBook);
        tv_nameBook=findViewById(R.id.edit_book_tv_ResultNameBook);
        tv_nameAuthor=findViewById(R.id.edit_book_tv_ResultAuthorName);
        tv_release=findViewById(R.id.edit_book_tv_ResultReleaseYear);
        tv_pages=findViewById(R.id.edit_book_tv_ResultPageNumber);
        tv_category=findViewById(R.id.edit_book_tv_ResultCategory);
        btn_edit=findViewById(R.id.edit_book_btn_edit);

        dataBase=new DataBase(this);

        Intent intent =getIntent();
        book=(Book)intent.getSerializableExtra(AdapterShowBook.SHOWBOOK_EXTRA_NAME);

        Uri uri=Uri.parse(book.getImgBook());
        iv_book.setImageURI(uri);
        tv_nameBook.setText(book.getBookName()+"");
        tv_nameAuthor.setText(book.getAuthorName()+"");
        tv_release.setText(book.getReleaseYear()+"");
        tv_pages.setText(book.getPages()+"");
        tv_category.setText(book.getNumCategory()+"");

        setTitle(tv_nameBook.getText().toString());

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Activity_edit_book.this,Activity_updateBook.class);
                intent1.putExtra(EDIT_EXTRA_NAMEBOOK,book);
                startActivity(intent1);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_delete,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menue_delete:
                dataBase.deleteBook(book);
                Toast.makeText(getBaseContext(), "The book has been deleted", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getBaseContext(),Activity_library.class);
                startActivity(intent);
                break;
        }

        return true;
    }
}