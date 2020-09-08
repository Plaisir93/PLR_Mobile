package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ht.solutions.plr.data.Database;

import java.util.ArrayList;
import java.util.List;

public class ChaineConnection {
    String adresseip;
    int id;
    String webservicespath;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresseip() {
        return this.adresseip;
    }

    public void setAdresseip(String adresseip) {
        this.adresseip = adresseip;
    }

    public String getWebservicespath() {
        return this.webservicespath;
    }

    public void setWebservicespath(String webservicespath) {
        this.webservicespath = webservicespath;
    }

    public static String getScript() {
        return (("CREATE TABLE chaineconnection(" + "id INTEGER PRIMARY KEY AUTOINCREMENT, ") + "adresseip TEXT , ") + "webservicespath TEXT);";
    }

    public static long Insert(Context c, ChaineConnection obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("adresseip", obj.getAdresseip());
        value.put("webservicespath", obj.getWebservicespath());
        db.getBdd().insert("chaineconnection", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from chaineconnection", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, ChaineConnection obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Integer.valueOf(obj.getId()));
        value.put("adresseip", obj.getAdresseip());
        value.put("webservicespath", obj.getWebservicespath());
        int x = db.getBdd().update("chaineconnection", value, "id = ?", new String[]{String.valueOf(1)});
        db.close();
    }

    public static void Delete(Context c) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("chaineconnection", "id > ?", new String[]{String.valueOf(0)});
        db.close();
    }

    public static List<ChaineConnection> SelectAll(Context c) {
        String query = (("select " + "id, ") + "adresseip, ") + "webservicespath from chaineconnection order by id desc";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<ChaineConnection> liste = new ArrayList();
        while (cur.moveToNext()) {
            ChaineConnection obj = new ChaineConnection();
            obj.setId(cur.getInt(0));
            obj.setAdresseip(cur.getString(1));
            obj.setWebservicespath(cur.getString(2));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public String toString() {
        return this.adresseip;
    }
}
