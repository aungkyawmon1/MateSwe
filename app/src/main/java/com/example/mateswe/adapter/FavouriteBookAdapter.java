package com.example.mateswe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mateswe.R;
import com.example.mateswe.model.FavouriteBook;

import java.util.ArrayList;

public class FavouriteBookAdapter extends RecyclerView.Adapter<FavouriteBookAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<FavouriteBook> favouriteBooksArrayList;

    public FavouriteBookAdapter(Context ctx, ArrayList<FavouriteBook> favouriteBooksArrayList){

        inflater = LayoutInflater.from(ctx);
        this.favouriteBooksArrayList = favouriteBooksArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.book_design, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(FavouriteBookAdapter.MyViewHolder holder, int position) {

        holder.bookPhoto.setImageResource(favouriteBooksArrayList.get(position).getImage());
        holder.bookName.setText(favouriteBooksArrayList.get(position).getBookName());
        holder.author.setText(favouriteBooksArrayList.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return favouriteBooksArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView bookName, author;
        ImageView bookPhoto;

        public MyViewHolder(View itemView) {
            super(itemView);

            bookName = (TextView) itemView.findViewById(R.id.tv_book_name);
            author = (TextView) itemView.findViewById(R.id.tv_author);
            bookPhoto = (ImageView) itemView.findViewById(R.id.iv_book_photo);
        }

    }
}
