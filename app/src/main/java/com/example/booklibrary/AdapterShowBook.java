package com.example.booklibrary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterShowBook extends RecyclerView.Adapter<AdapterShowBook.ShowBookHolder> {

    Context context;
    ArrayList<Book> data;

    static final String SHOWBOOK_EXTRA_NAME="book";

    public AdapterShowBook(Context context, ArrayList<Book> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ShowBookHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_deatils,parent,false);
        return new ShowBookHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterShowBook.ShowBookHolder holder, int position) {
        Book book=data.get(position);

        Uri uri=Uri.parse(book.getImgBook());
        holder.iv_image.setImageURI(uri);
        holder.tv_name.setText(book.getBookName()+"");
        holder.tv_releaseYear.setText(book.getReleaseYear()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,Activity_edit_book.class);
                intent.putExtra(SHOWBOOK_EXTRA_NAME,book);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ShowBookHolder extends RecyclerView.ViewHolder{

        ImageView iv_image;
        TextView tv_name,tv_releaseYear;

        public ShowBookHolder(View itemView) {
            super(itemView);

            iv_image=itemView.findViewById(R.id.item_book_deatils_iv_imageBook);
            tv_name=itemView.findViewById(R.id.item_book_deatils_tv_nameBook);
            tv_releaseYear=itemView.findViewById(R.id.item_book_deatils_tv_releaseYear);

        }
    }
}
