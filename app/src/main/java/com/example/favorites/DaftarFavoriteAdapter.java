package com.example.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DaftarFavoriteAdapter extends ArrayAdapter<Favorite> {
    Context context;

    public DaftarFavoriteAdapter(@NonNull Context context, @NonNull List<Favorite> objects) {
        super(context, R.layout.row_favorite, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txTgl;
        TextView txNamaTempat;
        TextView txDeskripsi;
        TextView txJenisTempat;
        TextView txDaerah;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Favorite tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_favorite,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txTgl = convertView.findViewById(R.id.row_tx_tgl_favorite);
            viewHolder.txNamaTempat = convertView.findViewById(R.id.row_tx_nama_tempat);

            viewHolder.txDaerah = convertView.findViewById(R.id.row_tx_daerah);
            viewHolder.txDeskripsi = convertView.findViewById(R.id.row_tx_desc_favorite);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txTgl.setText(GenericUtility.DATE_FORMAT.format(tr.getTanggal()));
        viewHolder.txNamaTempat.setText(tr.getNamaTempat());

        viewHolder.txDaerah.setText(tr.getDaerah());
        viewHolder.txDeskripsi.setText(tr.getDeskripsi());
        return convertView;
    }
}
