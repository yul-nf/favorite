package com.example.favorites;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

public class Favorite {

    public static final String GUNUNG="GUNUNG";
    public static final String BUKIT="BUKIT";
    public static final String PANTAI="PANTAI";
    public static final String TAMAN="TAMAN";

    private String id;
    private Date tanggal;
    private String namaTempat;
    private String deskripsi;
    private String jenis;
    private String daerah;


    public Favorite() {
        this.id = UUID.randomUUID().toString();
        this.tanggal = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNamaTempat() {
        return namaTempat;
    }

    public void setNamaTempat(String namaTempat) {
        this.namaTempat = namaTempat;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getDaerah() {
        return daerah;
    }

    public void setDaerah(String daerah) {
        this.daerah = daerah;
    }


    public static Favorite fromJSONObject(JSONObject obj) {
        Favorite tr = new Favorite();
        try {
            tr.setId(obj.getString("id"));
            tr.setTanggal(new Date(obj.getLong("tanggal")));
            tr.setNamaTempat(obj.getString("namaTempat"));
            tr.setDaerah(obj.getString("daerah"));
            tr.setDeskripsi(obj.getString("deskripsi"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tr;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id",this.id);
            jsonObj.put("tanggal",this.tanggal.getTime());
            jsonObj.put("namaTempat",this.namaTempat);
            jsonObj.put("deskripsi",this.deskripsi);
            jsonObj.put("jenis",this.jenis);
            jsonObj.put("daerah",this.daerah);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}
