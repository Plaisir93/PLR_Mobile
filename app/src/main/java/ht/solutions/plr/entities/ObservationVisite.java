package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ht.solutions.plr.data.Database;

import java.util.ArrayList;
import java.util.List;

public class ObservationVisite {
    private String errorMessage;
    private long id;
    private String observation;
    private long onlineid;
    private List<Suivi> suivis = new ArrayList();

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getObservation() {
        return this.observation;
    }

    public void setObservation(String value) {
        this.observation = value;
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

    public List<Suivi> getSuivis() {
        return this.suivis;
    }

    public boolean isSet() {
        this.errorMessage = "";
        if (this.id == 0) {
            this.errorMessage += "id, ";
            return false;
        } else if (this.observation.equals("")) {
            this.errorMessage += "Observation, ";
            return false;
        } else if (this.onlineid != 0) {
            return true;
        } else {
            this.errorMessage += "onlineid, ";
            return false;
        }
    }

    public static String getScript() {
        return (("CREATE TABLE ObservationVisite(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,") + "observation TEXT ,") + "onlineid INTEGER );";
    }

    public static long Insert(Context c, ObservationVisite obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("observation", obj.getObservation());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        db.getBdd().insert("ObservationVisite", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from ObservationVisite", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, ObservationVisite obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("observation", obj.getObservation());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        db.getBdd().update("ObservationVisite", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("ObservationVisite", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<ObservationVisite> SelectAll(Context c) {
        String query = (("select " + "id, ") + "observation, ") + "onlineid from ObservationVisite";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<ObservationVisite> liste = new ArrayList();
        while (cur.moveToNext()) {
            ObservationVisite obj = new ObservationVisite();
            obj.setId(cur.getLong(0));
            obj.setObservation(cur.getString(1));
            obj.setOnlineid(cur.getLong(2));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static ObservationVisite SelectById(Context c, long id) {
        String query = (("select " + "id, ") + "observation, ") + "onlineid from ObservationVisite where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            ObservationVisite obj = new ObservationVisite();
            obj.setId(cur.getLong(0));
            obj.setObservation(cur.getString(1));
            obj.setOnlineid(cur.getLong(2));
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<ObservationVisite> SelectByColumn(Context c, String colonne, String valeur) {
        String query = (("select " + "id, ") + "observation, ") + "onlineid from ObservationVisite where " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<ObservationVisite> liste = new ArrayList();
        while (cur.moveToNext()) {
            ObservationVisite obj = new ObservationVisite();
            obj.setId(cur.getLong(0));
            obj.setObservation(cur.getString(1));
            obj.setOnlineid(cur.getLong(2));
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
        Cursor cur = db.getBdd().rawQuery("select count(*) from observationvisite where " + colonne + " = ?", new String[]{value});
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public String toString() {
        return this.observation;
    }
}
