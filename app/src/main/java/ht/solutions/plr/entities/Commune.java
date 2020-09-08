package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ht.solutions.plr.data.Database;

import java.util.ArrayList;
import java.util.List;

public class Commune {
    private String commune;
    private long id;
    private long iddepartement;
    private double latitude;
    private double longitude;

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public long getIddepartement() {
        return this.iddepartement;
    }

    public void setIddepartement(long i) {
        this.iddepartement = i;
    }

    public String getCommune() {
        return this.commune;
    }

    public void setCommune(String value) {
        this.commune = value;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double value) {
        this.longitude = value;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double value) {
        this.latitude = value;
    }

    public static String getScript() {
        return (((("CREATE TABLE Commune(" + "id INTEGER PRIMARY KEY ,") + "iddepartement INTEGER,") + "commune TEXT ,") + "longitude REAL ,") + "latitude REAL );";
    }

    public static long Insert(Context c, Commune obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("iddepartement", Long.valueOf(obj.getIddepartement()));
        value.put("commune", obj.getCommune());
        value.put("longitude", Double.valueOf(obj.getLongitude()));
        value.put("latitude", Double.valueOf(obj.getLatitude()));
        db.getBdd().insert("Commune", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from Commune", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, Commune obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("iddepartement", Long.valueOf(obj.getIddepartement()));
        value.put("commune", obj.getCommune());
        value.put("longitude", Double.valueOf(obj.getLongitude()));
        value.put("latitude", Double.valueOf(obj.getLatitude()));
        db.getBdd().update("tbl_famille", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("Commune", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<Commune> SelectAll(Context c) {
        String query = (((("select " + "id, ") + "commune, ") + "longitude, ") + "iddepartement, ") + "latitude from Commune order by commune";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Commune> liste = new ArrayList();
        while (cur.moveToNext()) {
            Commune obj = new Commune();
            obj.setId(cur.getLong(0));
            obj.setCommune(cur.getString(1));
            obj.setLongitude(cur.getDouble(2));
            obj.setIddepartement(cur.getLong(3));
            obj.setLatitude(cur.getDouble(4));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static Commune SelectById(Context c, long id) {
        String query = (((("select " + "id, ") + "commune, ") + "longitude, ") + "iddepartement, ") + "latitude from Commune where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            Commune obj = new Commune();
            obj.setId(cur.getLong(0));
            obj.setCommune(cur.getString(1));
            obj.setLongitude(cur.getDouble(2));
            obj.setIddepartement(cur.getLong(3));
            obj.setLatitude(cur.getDouble(4));
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<Commune> SelectByColumn(Context c, String colonne, String valeur) {
        String query = (((("select " + "id, ") + "commune, ") + "longitude, ") + "iddepartement, ") + "latitude from Commune where " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<Commune> liste = new ArrayList();
        while (cur.moveToNext()) {
            Commune obj = new Commune();
            obj.setId(cur.getLong(0));
            obj.setCommune(cur.getString(1));
            obj.setLongitude(cur.getDouble(2));
            obj.setIddepartement(cur.getLong(3));
            obj.setLatitude(cur.getDouble(4));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public String toString() {
        return this.commune;
    }
}
