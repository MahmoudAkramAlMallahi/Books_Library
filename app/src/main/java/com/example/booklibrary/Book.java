package com.example.booklibrary;

import java.io.Serializable;

public class Book implements Serializable {

    private int id,numCategory;
    private String imgBook,bookName,authorName,releaseYear,pages;

    public Book(){

    }
    public Book(int id,String imgBook,String bookName,String authorName,String releaseYear,String pages,int numCategory){
        this.id=id;
        this.imgBook=imgBook;
        this.bookName=bookName;
        this.authorName=authorName;
        this.releaseYear=releaseYear;
        this.pages=pages;
        this.numCategory=numCategory;
    }
    public Book(String imgBook,String bookName,String authorName,String releaseYear,String pages,int numCategory){
        this.imgBook=imgBook;
        this.bookName=bookName;
        this.authorName=authorName;
        this.releaseYear=releaseYear;
        this.pages=pages;
        this.numCategory=numCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getNumCategory() {
        return numCategory;
    }

    public void setNumCategory(int numCategory) {
        this.numCategory = numCategory;
    }

    public String getImgBook() {
        return imgBook;
    }

    public void setImgBook(String imgBook) {
        this.imgBook = imgBook;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
}
