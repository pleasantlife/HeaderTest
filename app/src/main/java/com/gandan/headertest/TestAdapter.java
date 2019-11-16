package com.gandan.headertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {

    private Context context;
    private ArrayList<String> list;


    public TestAdapter(Context context, ArrayList<String> list){
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        String str = list.get(position);
        holder.itemTextView.setText(str);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TestHolder extends RecyclerView.ViewHolder {

        private TextView itemTextView;

        public TestHolder(@NonNull View itemView) {
            super(itemView);

            itemTextView = itemView.findViewById(R.id.itemTextView);
        }
    }
}
