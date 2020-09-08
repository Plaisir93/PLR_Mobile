package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ht.solutions.plr.data.Database;

import java.util.ArrayList;
import java.util.List;

public class Sensibilisation {
    private List<Attribut> attributs = new ArrayList();
    private String errorMessage;
    private long id;
    private long onlineid;

    public long getId() {
        return this.id;
    }

    public void setAttributs(List<Attribut> attributs) {
        this.attributs = attributs;
    }

    public void setId(long value) {
        this.id = value;
    }

    public long getOnlineid() {
        return this.onlineid;
    }

    public void setOnlineid(long value) {
        this.onlineid = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public List<Attribut> getAttributs() {
        return this.attributs;
    }

    public static String getScript() {
        return ("CREATE TABLE Sensibilisation(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,") + "onlineid INTEGER );";
    }

    public static long Insert(Context c, Sensibilisation obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        db.getBdd().insert("Sensibilisation", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from Sensibilisation", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, Sensibilisation obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        db.getBdd().update("Sensibilisation", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("Sensibilisation", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<Sensibilisation> SelectAll(Context c) {
        String query = ("select " + "id, ") + "onlineid from Sensibilisation";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Sensibilisation> liste = new ArrayList();
        while (cur.moveToNext()) {
            Sensibilisation obj = new Sensibilisation();
            obj.setId(cur.getLong(0));
            obj.setOnlineid(cur.getLong(1));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static Sensibilisation SelectById(Context c, long id) {
        String query = ("select " + "id, ") + "onlineid from Sensibilisation where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            Sensibilisation obj = new Sensibilisation();
            obj.setId(cur.getLong(0));
            obj.setOnlineid(cur.getLong(1));
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<Sensibilisation> SelectByColumn(Context c, String colonne, String valeur) {
        String query = ("select " + "id, ") + "onlineid from Sensibilisation where " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<Sensibilisation> liste = new ArrayList();
        while (cur.moveToNext()) {
            Sensibilisation obj = new Sensibilisation();
            obj.setId(cur.getLong(0));
            obj.setOnlineid(cur.getLong(1));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static int Count(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        int l = 0;
        Cursor cur = db.getBdd().rawQuery("select count(*) from sensibilisation where " + colonne + " = ?", new String[]{value});
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }
}
