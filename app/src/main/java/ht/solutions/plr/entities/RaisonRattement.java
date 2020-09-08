package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import ht.solutions.plr.data.Database;
import java.util.ArrayList;
import java.util.List;

public class RaisonRattement {
    private String errorMessage;
    private long id;
    private long onlineid;
    private String raison;
    private List<Suivi> suivis = new ArrayList();

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
        } else if (this.raison.equals("")) {
            this.errorMessage += "Raison, ";
            return false;
        } else if (this.onlineid != 0) {
            return true;
        } else {
            this.errorMessage += "onlineid, ";
            return false;
        }
    }

    public static String getScript() {
        return (("CREATE TABLE RaisonRattement(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,") + "raison TEXT ,") + "onlineid INTEGER );";
    }

    public static long Insert(Context c, RaisonRattement obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("raison", obj.getRaison());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        db.getBdd().insert("RaisonRattement", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from RaisonRattement", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, RaisonRattement obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("raison", obj.getRaison());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        db.getBdd().update("RaisonRattement", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("RaisonRattement", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<RaisonRattement> SelectAll(Context c) {
        String query = (("select " + "id, ") + "raison, ") + "onlineid from RaisonRattement";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<RaisonRattement> liste = new ArrayList();
        while (cur.moveToNext()) {
            RaisonRattement obj = new RaisonRattement();
            obj.setId(cur.getLong(0));
            obj.setRaison(cur.getString(1));
            obj.setOnlineid(cur.getLong(2));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static RaisonRattement SelectById(Context c, long id) {
        String query = (("select " + "id, ") + "raison, ") + "onlineid from RaisonRattement where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            RaisonRattement obj = new RaisonRattement();
            obj.setId(cur.getLong(0));
            obj.setRaison(cur.getString(1));
            obj.setOnlineid(cur.getLong(2));
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<RaisonRattement> SelectByColumn(Context c, String colonne, String valeur) {
        String query = (("select " + "id, ") + "raison, ") + "onlineid from RaisonRattement where " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<RaisonRattement> liste = new ArrayList();
        while (cur.moveToNext()) {
            RaisonRattement obj = new RaisonRattement();
            obj.setId(cur.getLong(0));
            obj.setRaison(cur.getString(1));
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
        Cursor cur = db.getBdd().rawQuery("select count(*) from raisonrattement where " + colonne + " = ?", new String[]{value});
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
