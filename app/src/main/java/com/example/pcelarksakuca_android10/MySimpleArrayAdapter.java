package com.example.pcelarksakuca_android10;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MySimpleArrayAdapter extends ArrayAdapter<Proizvod> implements Filterable {
    private final Context context;
    //private final Proizvod[] values;
    public ArrayList<Proizvod> values;
    private ArrayList<Proizvod> originalValues;

    //public MySimpleArrayAdapter(Context context, Proizvod[] values) {
    public MySimpleArrayAdapter(Context context, ArrayList<Proizvod> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
        this.originalValues = new ArrayList<Proizvod>(values);
    }

    public void azurirajListu(ArrayList<Proizvod> p_list) {
        this.values = p_list;
        this.originalValues = p_list;
    }

    @Override
    public int getCount() {
        return values != null? values.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_proizvodi_rowlayout, parent, false);
        TextView tV_proizvodi_naziv = (TextView) rowView.findViewById(R.id.proizvodi_naziv);
        TextView tV_proizvodi_cena = (TextView) rowView.findViewById(R.id.proizvodi_cena);
        //
        ImageView imageView = (ImageView) rowView.findViewById(R.id.proizvodi_slika);
        String uri = "@drawable/proizvod"+values.get(position).proizvod_id;
        int imageResource = this.context.getResources().getIdentifier(uri, null, this.context.getPackageName());
        Drawable res = this.context.getResources().getDrawable(imageResource);
        //
        tV_proizvodi_naziv.setText(values.get(position).naziv);
        tV_proizvodi_cena.setText("CENA: "+String.valueOf(values.get(position).cena));
        imageView.setImageDrawable(res);

        return rowView;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                ArrayList<Proizvod> tempList=new ArrayList<Proizvod>();

                if(constraint != null && values!=null) {
                    for (Proizvod p : originalValues){
                        if (p.naziv.toLowerCase().contains(constraint.toString().toLowerCase()) ||
                                p.opis.toLowerCase().contains(constraint.toString().toLowerCase()) ||
                                p.nacinKoriscenja.toLowerCase().contains(constraint.toString().toLowerCase())){
                            tempList.add(p);
                        }
                    }

                    filterResults.values = tempList;
                    filterResults.count = tempList.size();
                }
                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                values = (ArrayList<Proizvod>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }
}
