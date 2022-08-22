package com.example.pcelarksakuca_android10;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KategorijeAdapter extends RecyclerView.Adapter<KategorijeAdapter.MyViewHolder> {
    private ArrayList<String> values;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView kategorija;
        MyViewHolder(View view) {
            super(view);
            kategorija = view.findViewById(R.id.kategorija);
        }
    }

    public KategorijeAdapter(ArrayList<String> values) {
        this.values = values;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_proizvodi_kategorija_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String k = values.get(position);
        //
        SpannableString content = new SpannableString(k);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        //
        //holder.kategorija.setText(k);
        holder.kategorija.setText(content);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
