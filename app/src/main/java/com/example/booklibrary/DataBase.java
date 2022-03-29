package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    static final String DB_NAME="library";
    static final int VERSION=1;

    static final String TABLE_CATEGORY="category";
    static final String CLM_CATEGORYID="id";
    static final String CLM_CATEGORYNAME="name";

    static final String TABLE_BOOk="book";
    static final String CLM_BOOKID="id";
    static final String CLM_IMGBOOK="image";
    static final String CLM_BOOKNAME="name";
    static final String CLM_AUTHORNAME="authorName";
    static final String CLM_RELEASEYEAR="releaseYear";
    static final String CLM_PAGES="pagesNumber";
    static final String CLM_NUMBERCATEGORY="category";



    public DataBase(Context context){
        super(context,DB_NAME,null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table "+TABLE_CATEGORY+" ( "+
                CLM_CATEGORYID+" integer primary key autoincrement , "+
                CLM_CATEGORYNAME+" text )");

        sqLiteDatabase.execSQL("Create table "+TABLE_BOOk+" ( "+
                CLM_BOOKID+" integer primary key autoincrement , "+
                CLM_IMGBOOK+" text , "+CLM_BOOKNAME+" text not null , "+CLM_AUTHORNAME+" text not null , "+
                CLM_RELEASEYEAR+" text not null , "+CLM_PAGES+" text not null , "+
                CLM_NUMBERCATEGORY+" integer not null , " +
                " FOREIGN KEY ("+CLM_NUMBERCATEGORY+") REFERENCES "+TABLE_CATEGORY+"("+CLM_CATEGORYID+" ) )");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //method insert of table book
    public boolean insertBook(Book book){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CLM_IMGBOOK,book.getImgBook());
        contentValues.put(CLM_BOOKNAME,book.getBookName());
        contentValues.put(CLM_AUTHORNAME,book.getAuthorName());
        contentValues.put(CLM_RELEASEYEAR,book.getReleaseYear());
        contentValues.put(CLM_PAGES,book.getPages());
        contentValues.put(CLM_NUMBERCATEGORY,book.getNumCategory());
        long result=db.insert(TABLE_BOOk,null,contentValues);

        return result !=-1;
    }
    //method insert of table category
    public boolean insertCategory(Category category){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CLM_CATEGORYNAME,category.getCategoryName());
        long result=db.insert(TABLE_CATEGORY,null,contentValues);

        return result !=-1;
    }
    //method update of table book
    public boolean updateBook(String nameOriginal,String uri,String name,String author,String release,String pages,int category){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CLM_IMGBOOK,uri);
        contentValues.put(CLM_BOOKNAME,name);
        contentValues.put(CLM_AUTHORNAME,author);
        contentValues.put(CLM_RELEASEYEAR,release);
        contentValues.put(CLM_PAGES,pages);
        contentValues.put(CLM_NUMBERCATEGORY,category);

        String[] args={nameOriginal};
        int result=db.update(TABLE_BOOk,contentValues,CLM_BOOKNAME+"=?",args);

        return result >0;
    }
    //method delete of table book
    public boolean deleteBook(Book book){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CLM_IMGBOOK,book.getImgBook());
        contentValues.put(CLM_BOOKNAME,book.getBookName());
        contentValues.put(CLM_AUTHORNAME,book.getAuthorName());
        contentValues.put(CLM_RELEASEYEAR,book.getReleaseYear());
        contentValues.put(CLM_PAGES,book.getPages());
        contentValues.put(CLM_NUMBERCATEGORY,book.getNumCategory());

        String[] args={String.valueOf(book.getBookName())};
        int result=db.delete(TABLE_BOOk,CLM_BOOKNAME+"=?",args);

        return result >0;
    }
    //method select of table book
    public ArrayList<Book> selectBook(){
        String select="select * from "+TABLE_BOOk;
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Book> listBook=new ArrayList<Book>();
        Cursor cursor=db.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(CLM_BOOKID));
                String image=cursor.getString(cursor.getColumnIndexOrThrow(CLM_IMGBOOK));
                String name=cursor.getString(cursor.getColumnIndexOrThrow(CLM_BOOKNAME));
                String author =cursor.getString(cursor.getColumnIndexOrThrow(CLM_AUTHORNAME));
                String release=cursor.getString(cursor.getColumnIndexOrThrow(CLM_RELEASEYEAR));
                String pages =cursor.getString(cursor.getColumnIndexOrThrow(CLM_PAGES));
                int categoryNum=cursor.getInt(cursor.getColumnIndexOrThrow(CLM_NUMBERCATEGORY));
                Book book=new Book(id,image,name,author,release,pages,categoryNum);
                listBook.add(book);
            }while (cursor.moveToNext());
            db.close();
        }
        return listBook;
    }
    //method select of table category
    public ArrayList<Category> selectCategory(){
        String select="select * from "+TABLE_CATEGORY;
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Category> listCategory=new ArrayList<Category>();
        Cursor cursor=db.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(CLM_CATEGORYID));
                String name=cursor.getString(cursor.getColumnIndexOrThrow(CLM_CATEGORYNAME));
                Category category=new Category(name);
                listCategory.add(category);
            }while (cursor.moveToNext());
            db.close();
        }
        return listCategory;
    }

    //method select of table book
    public ArrayList<Book> selectItemBook(int bookId){
        String select="select * from "+TABLE_BOOk+" WHERE "+CLM_NUMBERCATEGORY+"=?";
        SQLiteDatabase db=getReadableDatabase();
        ArrayList<Book> listBook=new ArrayList<Book>();
        String[] args={String.valueOf(bookId)};
        Cursor cursor=db.rawQuery(select,args);
        if (cursor.moveToFirst()){
            do{
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(CLM_BOOKID));
                String image=cursor.getString(cursor.getColumnIndexOrThrow(CLM_IMGBOOK));
                String name=cursor.getString(cursor.getColumnIndexOrThrow(CLM_BOOKNAME));
                String author =cursor.getString(cursor.getColumnIndexOrThrow(CLM_AUTHORNAME));
                String release=cursor.getString(cursor.getColumnIndexOrThrow(CLM_RELEASEYEAR));
                String pages =cursor.getString(cursor.getColumnIndexOrThrow(CLM_PAGES));
                int categoryNum=cursor.getInt(cursor.getColumnIndexOrThrow(CLM_NUMBERCATEGORY));
                Book book=new Book(id,image,name,author,release,pages,categoryNum);
                listBook.add(book);
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return listBook;
    }

}
