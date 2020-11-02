package com.example.testapp2019sliit;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.MyViewHolder> {
    private ArrayList<Game> gameArrayList;
    private Context context;
    private gameListOnClick activity;

    public GameListAdapter(ArrayList<Game> gameArrayList, Context context) {
        this.gameArrayList = gameArrayList;
        this.context = context;
        this.activity = (gameListOnClick)context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gamelist_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        holder.title.setText(gameArrayList.get(position).getGame_name());
        holder.year.setText(String.valueOf(gameArrayList.get(position).getYear()));
    }

    @Override
    public int getItemCount() {
        return gameArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, year;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.game_name);
            year = itemView.findViewById(R.id.yearD);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("DB","Clicked Item " + v.getTag().toString());
                    activity.gameListItemClick(gameArrayList.get(Integer.valueOf(v.getTag().toString())).getGame_name());
                }
            });
        }
    }

    public interface gameListOnClick {
        public void gameListItemClick(String game);
    }
}
