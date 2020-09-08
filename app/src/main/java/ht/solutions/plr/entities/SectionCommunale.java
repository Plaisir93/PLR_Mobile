package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ht.solutions.plr.data.Database;

import java.util.ArrayList;
import java.util.List;

public class SectionCommunale {
    private String errorMessage;
    private long id;
    private long idcommune;
    private String sectioncommunale;

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getSectioncommunale() {
        return this.sectioncommunale;
    }

    public void setSectioncommunale(String value) {
        this.sectioncommunale = value;
    }

    public long getIdcommune() {
        return this.idcommune;
    }

    public void setIdcommune(long value) {
        this.idcommune = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean isSet() {
        this.errorMessage = "";
        if (this.id == 0) {
            this.errorMessage += "id, ";
            return false;
        } else if (this.sectioncommunale.equals("")) {
            this.errorMessage += "Section communale, ";
            return false;
        } else if (this.idcommune != 0) {
            return true;
        } else {
            this.errorMessage += "idcommune, ";
            return false;
        }
    }

    public static String getScript() {
        return (("CREATE TABLE SectionCommunale(" + "id INTEGER PRIMARY KEY ,") + "sectioncommunale TEXT ,") + "idcommune INTEGER );";
    }

    public static long Insert(Context c, SectionCommunale obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("sectioncommunale", obj.getSectioncommunale());
        value.put("idcommune", Long.valueOf(obj.getIdcommune()));
        db.getBdd().insert("SectionCommunale", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from SectionCommunale", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, SectionCommunale obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("sectioncommunale", obj.getSectioncommunale());
        value.put("idcommune", Long.valueOf(obj.getIdcommune()));
        db.getBdd().update("SectionCommunale", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("SectionCommunale", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<SectionCommunale> SelectAll(Context c) {
        String query = (("select " + "id, ") + "sectioncommunale, ") + "idcommune from SectionCommunale";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<SectionCommunale> liste = new ArrayList();
        while (cur.moveToNext()) {
            SectionCommunale obj = new SectionCommunale();
            obj.setId(cur.getLong(0));
            obj.setSectioncommunale(cur.getString(1));
            obj.setIdcommune(cur.getLong(2));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static SectionCommunale SelectById(Context c, long id) {
        String query = (("select " + "id, ") + "sectioncommunale, ") + "idcommune from SectionCommunale where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            SectionCommunale obj = new SectionCommunale();
            obj.setId(cur.getLong(0));
            obj.setSectioncommunale(cur.getString(1));
            obj.setIdcommune(cur.getLong(2));
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<SectionCommunale> SelectByColumn(Context c, String colonne, String valeur) {
        String query = (("select " + "id, ") + "sectioncommunale, ") + "idcommune from SectionCommunale where " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<SectionCommunale> liste = new ArrayList();
        while (cur.moveToNext()) {
            SectionCommunale obj = new SectionCommunale();
            obj.setId(cur.getLong(0));
            obj.setSectioncommunale(cur.getString(1));
            obj.setIdcommune(cur.getLong(2));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public String toString() {
        return this.sectioncommunale;
    }
}
