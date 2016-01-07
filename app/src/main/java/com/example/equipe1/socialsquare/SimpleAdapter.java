package com.example.equipe1.socialsquare;


import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.equipe1.webservice.Joueur;

public class SimpleAdapter extends BaseAdapter {

    Context context;
    List<Joueur> rowItem;

    SimpleAdapter(Context context, List<Joueur> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
    }

    @Override
    public int getCount() {
        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItem.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item, null);
        }

        Button button = (Button) convertView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Demande envoyée",Toast.LENGTH_LONG).show();
            }
        });
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        Joueur row_pos = rowItem.get(position);
        txtTitle.setText(row_pos.getName());

        return convertView;

    }

}
