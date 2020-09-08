package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ht.solutions.plr.data.Database;

import java.util.ArrayList;
import java.util.List;

public class RaisonVisite {
    private String errorMessage;
    private long id;
    private long idsuivi;
    private long onlineid;
    private String raison;

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getRaison() {
        return this.raison;
    }

    public void setRaison(String value) {
        this.raison = value;
    }

    public long getOnlineid() {
        return this.onlineid;
    }

    public void setOnlineid(long value) {
        this.onlineid = value;
    }

    public long getIdsuivi() {
        return this.idsuivi;
    }

    public void setIdsuivi(long value) {
        this.idsuivi = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean isSet() {
        this.errorMessage = "";
        if (this.id == 0) {
            this.errorMessage += "id, ";
            return false;
        } else if (this.raison.equals("")) {
            this.errorMessage += "Raison, ";
            return false;
        } else if (this.onlineid == 0) {
            this.errorMessage += "onlineid, ";
            return false;
        } else if (this.idsuivi != 0) {
            return true;
        } else {
            this.errorMessage += "idsuivi, ";
            return false;
        }
    }

    public static String getScript() {
        return ((("CREATE TABLE RaisonVisite(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,") + "raison TEXT ,") + "onlineid INTEGER ,") + "idsuivi INTEGER );";
    }

    public static long Insert(Context c, RaisonVisite obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("raison", obj.getRaison());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        value.put("idsuivi", Long.valueOf(obj.getIdsuivi()));
        db.getBdd().insert("RaisonVisite", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from RaisonVisite", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, RaisonVisite obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("raison", obj.getRaison());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        value.put("idsuivi", Long.valueOf(obj.getIdsuivi()));
        db.getBdd().update("RaisonVisite", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("RaisonVisite", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<RaisonVisite> SelectAll(Context c) {
        String query = ((("select " + "id, ") + "raison, ") + "onlineid, ") + "idsuivi from RaisonVisite";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<RaisonVisite> liste = new ArrayList();
        while (cur.moveToNext()) {
            RaisonVisite obj = new RaisonVisite();
            obj.setId(cur.getLong(0));
            obj.setRaison(cur.getString(1));
            obj.setOnlineid(cur.getLong(2));
            obj.setIdsuivi(cur.getLong(3));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static RaisonVisite SelectById(Context c, long id) {
        String query = ((("select " + "id, ") + "raison, ") + "onlineid, ") + "idsuivi from RaisonVisite where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            RaisonVisite obj = new RaisonVisite();
            obj.setId(cur.getLong(0));
            obj.setRaison(cur.getString(1));
            obj.setOnlineid(cur.getLong(2));
            obj.setIdsuivi(cur.getLong(3));
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<RaisonVisite> SelectByColumn(Context c, String colonne, String valeur) {
        String query = ((("select " + "id, ") + "raison, ") + "onlineid, ") + "idsuivi from RaisonVisite where " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<RaisonVisite> liste = new ArrayList();
        while (cur.moveToNext()) {
            RaisonVisite obj = new RaisonVisite();
            obj.setId(cur.getLong(0));
            obj.setRaison(cur.getString(1));
            obj.setOnlineid(cur.getLong(2));
            obj.setIdsuivi(cur.getLong(3));
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
        Cursor cur = db.getBdd().rawQuery("select count(*) from raisonvisite where " + colonne + " = ?", new String[]{value});
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public String toString() {
        return this.raison;
    }
}
