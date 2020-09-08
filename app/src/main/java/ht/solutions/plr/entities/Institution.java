package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import ht.solutions.plr.data.Database;
import java.util.ArrayList;
import java.util.List;

public class Institution {
    long id;
    String institution;
    long onlineId;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInstitution() {
        return this.institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public long getOnlineId() {
        return this.onlineId;
    }

    public void setOnlineId(long onlineId) {
        this.onlineId = onlineId;
    }

    public static String getScript() {
        return (("CREATE TABLE Institution(" + "id PRIMARY KEY , ") + "institution TEXT , ") + "onlineId INTEGER);";
    }

    public static long Insert(Context c, Institution obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("institution", obj.getInstitution());
        value.put("onlineId", Long.valueOf(obj.getOnlineId()));
        db.getBdd().insert("Institution", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from Institution", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, Institution obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("institution", obj.getInstitution());
        value.put("onlineId", Long.valueOf(obj.getOnlineId()));
        db.getBdd().update("Institution", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("Institution", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<Institution> SelectAll(Context c) {
        String query = (("select " + "id, ") + "institution, ") + "onlineId from Institution order by institution";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Institution> liste = new ArrayList();
        while (cur.moveToNext()) {
            Institution obj = new Institution();
            obj.setId(cur.getLong(0));
            obj.setInstitution(cur.getString(1));
            obj.setOnlineId(cur.getLong(2));
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
        Cursor cur = db.getBdd().rawQuery("select count(*) from Institution where " + colonne + " = ?", new String[]{value});
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public String toString() {
        return this.institution;
    }
}
