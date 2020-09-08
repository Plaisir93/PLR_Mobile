package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ht.solutions.plr.data.Database;

import java.util.ArrayList;
import java.util.List;

public class Suivi_Medicament {
    private String autremedicament;
    private boolean boitsesarv;
    private boolean cotrimox;
    private String datelivraison;
    private String dureelivraison;
    private String errorMessage;
    private boolean flucanozole;
    private long id;
    private long idsuivi;
    private boolean inh;
    private boolean medicamentlivre;
    private String observation;
    private String regime;
    private Suivi suivi = new Suivi();

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getDureelivraison() {
        return this.dureelivraison;
    }

    public void setDureelivraison(String value) {
        this.dureelivraison = value;
    }

    public String getDatelivraison() {
        return this.datelivraison;
    }

    public void setDatelivraison(String value) {
        this.datelivraison = value;
    }

    public String getRegime() {
        return this.regime;
    }

    public void setRegime(String value) {
        this.regime = value;
    }

    public boolean isCotrimox() {
        return this.cotrimox;
    }

    public void setCotrimox(boolean value) {
        this.cotrimox = value;
    }

    public boolean isInh() {
        return this.inh;
    }

    public void setInh(boolean value) {
        this.inh = value;
    }

    public boolean isFlucanozole() {
        return this.flucanozole;
    }

    public void setFlucanozole(boolean value) {
        this.flucanozole = value;
    }

    public String getAutremedicament() {
        return this.autremedicament;
    }

    public void setAutremedicament(String value) {
        this.autremedicament = value;
    }

    public boolean isMedicamentlivre() {
        return this.medicamentlivre;
    }

    public void setMedicamentlivre(boolean value) {
        this.medicamentlivre = value;
    }

    public String getObservation() {
        return this.observation;
    }

    public void setObservation(String value) {
        this.observation = value;
    }

    public long getIdsuivi() {
        return this.idsuivi;
    }

    public void setIdsuivi(long value) {
        this.idsuivi = value;
    }

    public boolean isBoitsesarv() {
        return this.boitsesarv;
    }

    public void setBoitsesarv(boolean value) {
        this.boitsesarv = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public Suivi getSuivi() {
        return this.suivi;
    }

    public boolean isSet() {
        this.errorMessage = "";
        if (this.id == 0) {
            this.errorMessage += "id, ";
            return false;
        } else if (this.dureelivraison.equals("")) {
            this.errorMessage += "dureelivraison, ";
            return false;
        } else if (this.datelivraison.equals("")) {
            this.errorMessage += "datelivraison, ";
            return false;
        } else if (this.regime.equals("")) {
            this.errorMessage += "regime, ";
            return false;
        } else if (this.autremedicament.equals("")) {
            this.errorMessage += "autremedicament, ";
            return false;
        } else if (this.observation.equals("")) {
            this.errorMessage += "observation, ";
            return false;
        } else if (this.idsuivi != 0) {
            return true;
        } else {
            this.errorMessage += "idSuivi, ";
            return false;
        }
    }

    public static String getScript() {
        return ((((((((((("CREATE TABLE Suivi_Medicament(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,") + "dureelivraison TEXT ,") + "datelivraison TEXT ,") + "regime TEXT ,") + "cotrimox BOOL ,") + "inh BOOL ,") + "flucanozole BOOL ,") + "autremedicament TEXT ,") + "medicamentlivre BOOL ,") + "observation TEXT ,") + "idsuivi INTEGER ,") + "boitsesarv BOOL );";
    }

    public static long Insert(Context c, Suivi_Medicament obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("dureelivraison", obj.getDureelivraison());
        value.put("datelivraison", obj.getDatelivraison());
        value.put("regime", obj.getRegime());
        value.put("cotrimox", Boolean.valueOf(obj.isCotrimox()));
        value.put("inh", Boolean.valueOf(obj.isInh()));
        value.put("flucanozole", Boolean.valueOf(obj.isFlucanozole()));
        value.put("autremedicament", obj.getAutremedicament());
        value.put("medicamentlivre", Boolean.valueOf(obj.isMedicamentlivre()));
        value.put("observation", obj.getObservation());
        value.put("idsuivi", Long.valueOf(obj.getIdsuivi()));
        value.put("boitsesarv", Boolean.valueOf(obj.isBoitsesarv()));
        db.getBdd().insert("Suivi_Medicament", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from Suivi_Medicament", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, Suivi_Medicament obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("dureelivraison", obj.getDureelivraison());
        value.put("datelivraison", obj.getDatelivraison());
        value.put("regime", obj.getRegime());
        value.put("cotrimox", Boolean.valueOf(obj.isCotrimox()));
        value.put("inh", Boolean.valueOf(obj.isInh()));
        value.put("flucanozole", Boolean.valueOf(obj.isFlucanozole()));
        value.put("autremedicament", obj.getAutremedicament());
        value.put("medicamentlivre", Boolean.valueOf(obj.isMedicamentlivre()));
        value.put("observation", obj.getObservation());
        value.put("idsuivi", Long.valueOf(obj.getIdsuivi()));
        value.put("boitsesarv", Boolean.valueOf(obj.isBoitsesarv()));
        db.getBdd().update("Suivi_Medicament", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("Suivi_Medicament", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<Suivi_Medicament> SelectAll(Context c) {
        String query = ((((((((((("select " + "id, ") + "dureelivraison, ") + "datelivraison, ") + "regime, ") + "cotrimox, ") + "inh, ") + "flucanozole, ") + "autremedicament, ") + "medicamentlivre, ") + "observation, ") + "idsuivi, ") + "boitsesarv from Suivi_Medicament";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Suivi_Medicament> liste = new ArrayList();
        while (cur.moveToNext()) {
            boolean z;
            Suivi_Medicament obj = new Suivi_Medicament();
            obj.setId(cur.getLong(0));
            obj.setDureelivraison(cur.getString(1));
            obj.setDatelivraison(cur.getString(2));
            obj.setRegime(cur.getString(3));
            obj.setCotrimox(cur.getInt(4) == 1);
            if (cur.getInt(5) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setInh(z);
            if (cur.getInt(6) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setFlucanozole(z);
            obj.setAutremedicament(cur.getString(7));
            if (cur.getInt(8) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedicamentlivre(z);
            obj.setObservation(cur.getString(9));
            obj.setIdsuivi(cur.getLong(10));
            if (cur.getInt(11) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBoitsesarv(z);
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static Suivi_Medicament SelectById(Context c, long id) {
        boolean z = true;
        String query = ((((((((((("select " + "id, ") + "dureelivraison, ") + "datelivraison, ") + "regime, ") + "cotrimox, ") + "inh, ") + "flucanozole, ") + "autremedicament, ") + "medicamentlivre, ") + "observation, ") + "idsuivi, ") + "boitsesarv from Suivi_Medicament where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            boolean z2;
            Suivi_Medicament obj = new Suivi_Medicament();
            obj.setId(cur.getLong(0));
            obj.setDureelivraison(cur.getString(1));
            obj.setDatelivraison(cur.getString(2));
            obj.setRegime(cur.getString(3));
            if (cur.getInt(4) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setCotrimox(z2);
            if (cur.getInt(5) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setInh(z2);
            if (cur.getInt(6) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setFlucanozole(z2);
            obj.setAutremedicament(cur.getString(7));
            if (cur.getInt(8) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setMedicamentlivre(z2);
            obj.setObservation(cur.getString(9));
            obj.setIdsuivi(cur.getLong(10));
            if (cur.getInt(11) != 1) {
                z = false;
            }
            obj.setBoitsesarv(z);
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<Suivi_Medicament> SelectByColumn(Context c, String colonne, String valeur) {
        String query = ((((((((((("select " + "id, ") + "dureelivraison, ") + "datelivraison, ") + "regime, ") + "cotrimox, ") + "inh, ") + "flucanozole, ") + "autremedicament, ") + "medicamentlivre, ") + "observation, ") + "idsuivi, ") + "boitsesarv from Suivi_Medicament where " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<Suivi_Medicament> liste = new ArrayList();
        while (cur.moveToNext()) {
            boolean z;
            Suivi_Medicament obj = new Suivi_Medicament();
            obj.setId(cur.getLong(0));
            obj.setDureelivraison(cur.getString(1));
            obj.setDatelivraison(cur.getString(2));
            obj.setRegime(cur.getString(3));
            obj.setCotrimox(cur.getInt(4) == 1);
            if (cur.getInt(5) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setInh(z);
            if (cur.getInt(6) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setFlucanozole(z);
            obj.setAutremedicament(cur.getString(7));
            if (cur.getInt(8) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedicamentlivre(z);
            obj.setObservation(cur.getString(9));
            obj.setIdsuivi(cur.getLong(10));
            if (cur.getInt(11) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBoitsesarv(z);
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
        Cursor cur = db.getBdd().rawQuery("select count(*) from suivi_medicament where " + colonne + " = ?", new String[]{value});
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public String toString() {
        return this.dureelivraison;
    }
}
