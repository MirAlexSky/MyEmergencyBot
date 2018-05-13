package com.example.mirsky.myemergencybot;

import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView Text;

        MyViewHolder(View itemView) {

            super(itemView);

            cv = itemView.findViewById(R.id.cvp);
            Text = itemView.findViewById(R.id.MainText);
        }
    }

    private Cursor cursor;
    private int columnNum;

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }


    RVAdapter(Cursor cursor, int columnNum) {
        this.columnNum = columnNum;
        this.cursor = cursor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pattern, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.Text.setText(cursor.getString(columnNum));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }
}
