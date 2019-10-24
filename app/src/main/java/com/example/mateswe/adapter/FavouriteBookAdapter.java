package com.example.mateswe.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mateswe.R;
import com.example.mateswe.activity.BookActivity;
import com.example.mateswe.entity.Book;

import java.util.List;

public class FavouriteBookAdapter extends RecyclerView.Adapter<FavouriteBookAdapter.MyViewHolder> {

    private Context inflater;
    private List<Book> favouriteBooksArrayList;
    public FavouriteBookAdapter(Context ctx, List<Book> favouriteBooksArrayList){

        inflater = ctx;
        this.favouriteBooksArrayList = favouriteBooksArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_design, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(FavouriteBookAdapter.MyViewHolder holder, final int position) {
        holder.bindView(favouriteBooksArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return favouriteBooksArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bookName, author;
        ImageView bookPhoto;
        Book book;

        public MyViewHolder(final View itemView) {
            super(itemView);

            bookName = (TextView) itemView.findViewById(R.id.tv_book_name);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            bookPhoto = (ImageView) itemView.findViewById(R.id.iv_book_photo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), BookActivity.class);
                    intent.putExtra("id",book.getId()+"");
                    itemView.getContext().startActivity(intent);
                }
            });

        }
        public void bindView(final Book book){

            this.book = book;
            if(book.getPhoto() != null) {
                Glide.with(inflater)
                        .load(Uri.parse(book.getPhoto()))
                        .into(bookPhoto);
            }
            bookName.setText(book.getBook_name());
            author.setText(book.getAuthor());

        }


    }
}
