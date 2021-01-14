package com.example.favorites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnTambahFavorite;
    ImageButton btnChangeUserName;
    ListView lvDaftarFavorite;
    TextView txNoData, txUsername;
    DaftarFavoriteAdapter adapter;
    List<Favorite> daftarFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
        loadDataFavorite();
        setupListview();
    }

    private void inisialisasiView() {
        btnTambahFavorite = findViewById(R.id.btn_add_favorite);
        btnTambahFavorite.setOnClickListener(view -> bukaFormTambahFavorite());
        btnChangeUserName = findViewById(R.id.btn_change_username);
        btnChangeUserName.setOnClickListener(view -> changeUserName());
        lvDaftarFavorite = findViewById(R.id.lv_favorite);
        txNoData = findViewById(R.id.tx_nodata);
        txUsername = findViewById(R.id.tx_user_name);
        txUsername.setText(SharedPreferenceUtility.getUserName(this));

    }

    private void setupListview() {
        adapter = new DaftarFavoriteAdapter(this, daftarFavorite);
        lvDaftarFavorite.setAdapter(adapter);
    }

    private void loadDataFavorite() {
        daftarFavorite = SharedPreferenceUtility.getAllFavorite(this);
    }
    private void refreshListView() {
        adapter.clear();
        loadDataFavorite();
        adapter.addAll(daftarFavorite);
    }

    private void bukaFormTambahFavorite() {
        Intent intent = new Intent(this, FormFavoriteActivity.class);
        startActivity(intent);
    }

    private void changeUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ganti nama");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferenceUtility.saveUserName(getApplicationContext(),input.getText().toString());
                Toast.makeText(getApplicationContext(),"Nama user berhasil disimpan",Toast.LENGTH_SHORT).show();
                txUsername.setText(SharedPreferenceUtility.getUserName(getApplicationContext()));
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }
}