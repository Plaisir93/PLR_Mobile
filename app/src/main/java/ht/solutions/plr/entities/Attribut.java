package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ht.solutions.plr.data.Database;

import java.util.ArrayList;
import java.util.List;

public class Attribut {
    private String attribut;
    private String errorMessage;
    private long id;
    private long idreference;
    private long idsensibilisation;

    public Attribut(){}
    public Attribut(long idref, String a, long idsens) {
        this.idreference = idref;
        this.attribut = a;
        this.idsensibilisation = idsens;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public long getIdreference() {
        return this.idreference;
    }

    public void setIdreference(long value) {
        this.idreference = value;
    }

    public String getAttribut() {
        return this.attribut;
    }

    public void setAttribut(String value) {
        this.attribut = value;
    }

    public long getIdsensibilisation() {
        return this.idsensibilisation;
    }

    public void setIdsensibilisation(long value) {
        this.idsensibilisation = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public static String getScript() {
        return ((("CREATE TABLE Attribut(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,") + "idreference INTEGER ,") + "attribut TEXT ,") + "idsensibilisation INTEGER );";
    }

    public static long Insert(Context c, Attribut obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("idreference", Long.valueOf(obj.getIdreference()));
        value.put("attribut", obj.getAttribut());
        value.put("idsensibilisation", Long.valueOf(obj.getIdsensibilisation()));
        db.getBdd().insert("Attribut", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from Attribut", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, Attribut obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("idreference", Long.valueOf(obj.getIdreference()));
        value.put("attribut", obj.getAttribut());
        value.put("idsensibilisation", Long.valueOf(obj.getIdsensibilisation()));
        db.getBdd().update("Attribut", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("Attribut", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<Attribut> SelectAll(Context c) {
        String query = ((("select " + "id, ") + "idreference, ") + "attribut, ") + "idsensibilisation from Attribut";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Attribut> liste = new ArrayList();
        while (cur.moveToNext()) {
            Attribut obj = new Attribut();
            obj.setId(cur.getLong(0));
            obj.setIdreference(cur.getLong(1));
            obj.setAttribut(cur.getString(2));
            obj.setIdsensibilisation(cur.getLong(3));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static Attribut SelectById(Context c, long id) {
        String query = ((("select " + "id, ") + "idreference, ") + "attribut, ") + "idsensibilisation from Attribut where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            Attribut obj = new Attribut();
            obj.setId(cur.getLong(0));
            obj.setIdreference(cur.getLong(1));
            obj.setAttribut(cur.getString(2));
            obj.setIdsensibilisation(cur.getLong(3));
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<Attribut> SelectByColumn(Context c, String colonne, String valeur) {
        String query = ((("select " + "id, ") + "idreference, ") + "attribut, ") + "idsensibilisation from Attribut where " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<Attribut> liste = new ArrayList();
        while (cur.moveToNext()) {
            Attribut obj = new Attribut();
            obj.setId(cur.getLong(0));
            obj.setIdreference(cur.getLong(1));
            obj.setAttribut(cur.getString(2));
            obj.setIdsensibilisation(cur.getLong(3));
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
        Cursor cur = db.getBdd().rawQuery("select count(*) from attribut where " + colonne + " = ?", new String[]{value});
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }
}
