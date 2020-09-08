package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ht.solutions.plr.data.Database;
import ht.solutions.plr.views.FragmentPassword;

import java.util.ArrayList;
import java.util.List;

public class User {
    private boolean connected;
    private String errorMessage;
    private long id;
    private String nom;
    private String password;
    private String prenom;
    private String pseudo;
    private String sexe;

    public boolean isConnected() {
        return this.connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String value) {
        this.pseudo = value;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String value) {
        this.password = value;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String value) {
        this.nom = value;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String value) {
        this.prenom = value;
    }

    public String getSexe() {
        return this.sexe;
    }

    public void setSexe(String value) {
        this.sexe = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public boolean isSet() {
        this.errorMessage = "";
        if (this.id == 0) {
            this.errorMessage += "id, ";
            return false;
        } else if (this.pseudo.equals("")) {
            this.errorMessage += "pseudo, ";
            return false;
        } else if (this.password.equals("")) {
            this.errorMessage += "password, ";
            return false;
        } else if (this.nom.equals("")) {
            this.errorMessage += "nom, ";
            return false;
        } else if (this.prenom.equals("")) {
            this.errorMessage += "prenom, ";
            return false;
        } else if (!this.sexe.equals("")) {
            return true;
        } else {
            this.errorMessage += "sexe, ";
            return false;
        }
    }

    public static String getScript() {
        return ((((("CREATE TABLE User(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,") + "pseudo TEXT ,") + "password TEXT ,") + "nom TEXT ,") + "prenom TEXT ,") + "sexe TEXT, connected BOOL );";
    }

    public static long Insert(Context c, User obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("pseudo", obj.getPseudo());
        value.put(FragmentPassword.TAG, obj.getPassword());
        value.put("nom", obj.getNom());
        value.put("prenom", obj.getPrenom());
        value.put("sexe", obj.getSexe());
        value.put("connected", Boolean.valueOf(obj.isConnected()));
        db.getBdd().insert("User", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from User", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, User obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("pseudo", obj.getPseudo());
        value.put(FragmentPassword.TAG, obj.getPassword());
        value.put("nom", obj.getNom());
        value.put("prenom", obj.getPrenom());
        value.put("sexe", obj.getSexe());
        value.put("connected", Boolean.valueOf(obj.isConnected()));
        db.getBdd().update("User", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void setUserConnected(Context c, User obj, boolean b) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("connected", Boolean.valueOf(false));
        db.getBdd().update("User", value, "connected = 1", null);
        value = new ContentValues();
        if (b) {
            value.put("connected", Boolean.valueOf(true));
            db.getBdd().update("User", value, "id = ?", new String[]{String.valueOf(obj.getId())});
            db.close();
        }
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("User", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<User> SelectAll(Context c) {
        String query = ((((("select " + "id, ") + "pseudo, ") + "password, ") + "nom, ") + "prenom, ") + "sexe,connected from User";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<User> liste = new ArrayList();
        while (cur.moveToNext()) {
            boolean z;
            User obj = new User();
            obj.setId(cur.getLong(0));
            obj.setPseudo(cur.getString(1));
            obj.setPassword(cur.getString(2));
            obj.setNom(cur.getString(3));
            obj.setPrenom(cur.getString(4));
            obj.setSexe(cur.getString(5));
            if (cur.getInt(6) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setConnected(z);
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static User SelectById(Context c, long id) {
        boolean z = true;
        String query = ((((("select " + "id, ") + "pseudo, ") + "password, ") + "nom, ") + "prenom, ") + "sexe,connected from User where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            User obj = new User();
            obj.setId(cur.getLong(0));
            obj.setPseudo(cur.getString(1));
            obj.setPassword(cur.getString(2));
            obj.setNom(cur.getString(3));
            obj.setPrenom(cur.getString(4));
            obj.setSexe(cur.getString(5));
            if (cur.getInt(6) != 1) {
                z = false;
            }
            obj.setConnected(z);
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<User> SelectByColumn(Context c, String colonne, String valeur) {
        String query = ((((("select " + "id, ") + "pseudo, ") + "password, ") + "nom, ") + "prenom, ") + "sexe,connected from User where " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<User> liste = new ArrayList();
        while (cur.moveToNext()) {
            boolean z;
            User obj = new User();
            obj.setId(cur.getLong(0));
            obj.setPseudo(cur.getString(1));
            obj.setPassword(cur.getString(2));
            obj.setNom(cur.getString(3));
            obj.setPrenom(cur.getString(4));
            obj.setSexe(cur.getString(5));
            if (cur.getInt(6) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setConnected(z);
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static User GetUser(Context c, String pseudo, String password) {
        boolean z = true;
        String query = ((((("select " + "id, ") + "pseudo, ") + "password, ") + "nom, ") + "prenom, ") + "sexe,connected from User where pseudo = ? and password = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{pseudo, password});
        User obj = null;
        if (cur.moveToNext()) {
            obj = new User();
            obj.setId(cur.getLong(0));
            obj.setPseudo(cur.getString(1));
            obj.setPassword(cur.getString(2));
            obj.setNom(cur.getString(3));
            obj.setPrenom(cur.getString(4));
            obj.setSexe(cur.getString(5));
            if (cur.getInt(6) != 1) {
                z = false;
            }
            obj.setConnected(z);
        }
        cur.close();
        db.close();
        return obj;
    }

    public static int Count(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        int l = 0;
        Cursor cur = db.getBdd().rawQuery("select count(*) from user where " + colonne + " = ?", new String[]{value});
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public String toString() {
        return this.pseudo;
    }
}
