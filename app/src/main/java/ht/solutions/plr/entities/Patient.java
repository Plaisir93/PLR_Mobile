package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import ht.solutions.plr.data.Database;
import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String adressedomicilaire = "";
    private String adresseducontact = "";
    private int age;
    private String datenaissance = "";
    private String errorMessage;
    private long id;
    private long idsectioncommunalelieunaissance;
    private long idsectioncommunalelieuresidence;
    private double latitude;
    private double longitude;
    private String nom = "";
    private String nommere = "";
    private String occupation = "";
    private long onlineid;
    private String personnedecontact = "";
    private String prenom = "";
    private String sexe = "";
    private String statut = "";
    private List<Suivi> suivis = new ArrayList();
    private String telephone = "";
    private String telephoneducontact = "";

    private List<FingerPatient> fingerList = new ArrayList<FingerPatient>();


    public List<FingerPatient> getFingerList() {
        return fingerList;
    }

    public void setFingerList(List<FingerPatient> fingerList) {
        this.fingerList = fingerList;
    }

    public long getId() {
        return this.id;
    }

    public void setAdresseDomiciliaire(String value) {
        this.adressedomicilaire = value;
    }

    public String getAdresseDomiciliaire() {
        return this.adressedomicilaire;
    }

    public void setId(long value) {
        this.id = value;
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

    public int getAge() {
        try {
            this.age = 1 - Integer.parseInt(this.datenaissance.replace("12:00:00 AM", "").split("/")[2]);
        } catch (Exception e) {
            Log.v("date de naissance", this.datenaissance);
        }
        return this.age;
    }

    public void setAge(int value) {
        this.age = value;
    }

    public String getDatenaissance() {
        return this.datenaissance;
    }

    public void setDatenaissance(String value) {
        this.datenaissance = value;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setStatut(String value) {
        this.statut = value;
    }

    public long getOnlineid() {
        return this.onlineid;
    }

    public void setOnlineid(long value) {
        this.onlineid = value;
    }

    public String getSexe() {
        return this.sexe;
    }

    public void setSexe(String value) {
        this.sexe = value;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String value) {
        this.telephone = value;
    }

    public String getAdressedomicilaire() {
        return this.adressedomicilaire;
    }

    public void setAdressedomicilaire(String value) {
        this.adressedomicilaire = value;
    }

    public long getIdsectioncommunalelieuresidence() {
        return this.idsectioncommunalelieuresidence;
    }

    public void setIdsectioncommunalelieuresidence(long value) {
        this.idsectioncommunalelieuresidence = value;
    }

    public long getIdsectioncommunalelieunaissance() {
        return this.idsectioncommunalelieunaissance;
    }

    public void setIdsectioncommunalelieunaissance(long value) {
        this.idsectioncommunalelieunaissance = value;
    }

    public String getPersonnedecontact() {
        return this.personnedecontact;
    }

    public void setPersonnedecontact(String value) {
        this.personnedecontact = value;
    }

    public String getTelephoneducontact() {
        return this.telephoneducontact;
    }

    public void setTelephoneducontact(String value) {
        this.telephoneducontact = value;
    }

    public String getAdresseducontact() {
        return this.adresseducontact;
    }

    public void setAdresseducontact(String value) {
        this.adresseducontact = value;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String value) {
        this.occupation = value;
    }

    public String getNommere() {
        return this.nommere;
    }

    public void setNommere(String value) {
        this.nommere = value;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double value) {
        this.longitude = value;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double value) {
        this.latitude = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public List<Suivi> getSuivis() {
        return this.suivis;
    }

    public boolean isSet() {
        return true;
    }

    public static String getScript() {
        return (((((((((((((((((("CREATE TABLE Patient(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,") + "nom TEXT ,") + "prenom TEXT ,") + "age INTEGER ,") + "datenaissance TEXT ,") + "statut TEXT ,") + "onlineid INTEGER ,") + "sexe TEXT ,") + "telephone TEXT ,") + "adressedomicilaire TEXT ,") + "idsectioncommunalelieuresidence INTEGER ,") + "idsectioncommunalelieunaissance INTEGER ,") + "personnedecontact TEXT ,") + "telephoneducontact TEXT ,") + "adresseducontact TEXT ,") + "occupation TEXT ,") + "nommere TEXT ,") + "longitude REAL ,") + "latitude REAL );";
    }

    public static long Insert(Context c, Patient obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("nom", obj.getNom());
        value.put("prenom", obj.getPrenom());
        value.put("age", Integer.valueOf(obj.getAge()));
        value.put("datenaissance", obj.getDatenaissance());
        value.put("statut", obj.getStatut());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        value.put("sexe", obj.getSexe());
        value.put("telephone", obj.getTelephone());
        value.put("adressedomicilaire", obj.getAdressedomicilaire());
        value.put("idsectioncommunalelieuresidence", Long.valueOf(obj.getIdsectioncommunalelieuresidence()));
        value.put("idsectioncommunalelieunaissance", Long.valueOf(obj.getIdsectioncommunalelieunaissance()));
        value.put("personnedecontact", obj.getPersonnedecontact());
        value.put("telephoneducontact", obj.getTelephoneducontact());
        value.put("adresseducontact", obj.getAdresseducontact());
        value.put("occupation", obj.getOccupation());
        value.put("nommere", obj.getNommere());
        value.put("longitude", Double.valueOf(obj.getLongitude()));
        value.put("latitude", Double.valueOf(obj.getLatitude()));
        db.getBdd().insert("Patient", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from Patient", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, Patient obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("nom", obj.getNom());
        value.put("prenom", obj.getPrenom());
        value.put("age", Integer.valueOf(obj.getAge()));
        value.put("datenaissance", obj.getDatenaissance());
        value.put("statut", obj.getStatut());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        value.put("sexe", obj.getSexe());
        value.put("telephone", obj.getTelephone());
        value.put("adressedomicilaire", obj.getAdressedomicilaire());
        value.put("idsectioncommunalelieuresidence", Long.valueOf(obj.getIdsectioncommunalelieuresidence()));
        value.put("idsectioncommunalelieunaissance", Long.valueOf(obj.getIdsectioncommunalelieunaissance()));
        value.put("personnedecontact", obj.getPersonnedecontact());
        value.put("telephoneducontact", obj.getTelephoneducontact());
        value.put("adresseducontact", obj.getAdresseducontact());
        value.put("occupation", obj.getOccupation());
        value.put("nommere", obj.getNommere());
        value.put("longitude", Double.valueOf(obj.getLongitude()));
        value.put("latitude", Double.valueOf(obj.getLatitude()));
        db.getBdd().update("Patient", value, "onlineid = ?", new String[]{String.valueOf(obj.getOnlineid())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("Patient", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<Patient> SelectAll(Context c) {
        String query = (((((((((((((((((("select " + "id, ") + "nom, ") + "prenom, ") + "age, ") + "datenaissance, ") + "statut, ") + "onlineid, ") + "sexe, ") + "telephone, ") + "adressedomicilaire, ") + "idsectioncommunalelieuresidence, ") + "idsectioncommunalelieunaissance, ") + "personnedecontact, ") + "telephoneducontact, ") + "adresseducontact, ") + "occupation, ") + "nommere, ") + "longitude, ") + "latitude from Patient";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Patient> liste = new ArrayList();
        while (cur.moveToNext()) {
            Patient obj = new Patient();
            obj.setId(cur.getLong(0));
            obj.setNom(cur.getString(1));
            obj.setPrenom(cur.getString(2));
            obj.setAge(cur.getInt(3));
            obj.setDatenaissance(cur.getString(4));
            obj.setStatut(cur.getString(5));
            obj.setOnlineid(cur.getLong(6));
            obj.setSexe(cur.getString(7));
            obj.setTelephone(cur.getString(8));
            obj.setAdressedomicilaire(cur.getString(9));
            obj.setIdsectioncommunalelieuresidence(cur.getLong(10));
            obj.setIdsectioncommunalelieunaissance(cur.getLong(11));
            obj.setPersonnedecontact(cur.getString(12));
            obj.setTelephoneducontact(cur.getString(13));
            obj.setAdresseducontact(cur.getString(14));
            obj.setOccupation(cur.getString(15));
            obj.setNommere(cur.getString(16));
            obj.setLongitude(cur.getDouble(17));
            obj.setLatitude(cur.getDouble(18));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static Patient SelectById(Context c, long id) {
        String query = (((((((((((((((((("select " + "id, ") + "nom, ") + "prenom, ") + "age, ") + "datenaissance, ") + "statut, ") + "onlineid, ") + "sexe, ") + "telephone, ") + "adressedomicilaire, ") + "idsectioncommunalelieuresidence, ") + "idsectioncommunalelieunaissance, ") + "personnedecontact, ") + "telephoneducontact, ") + "adresseducontact, ") + "occupation, ") + "nommere, ") + "longitude, ") + "latitude from Patient where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            Patient obj = new Patient();
            obj.setId(cur.getLong(0));
            obj.setNom(cur.getString(1));
            obj.setPrenom(cur.getString(2));
            obj.setAge(cur.getInt(3));
            obj.setDatenaissance(cur.getString(4));
            obj.setStatut(cur.getString(5));
            obj.setOnlineid(cur.getLong(6));
            obj.setSexe(cur.getString(7));
            obj.setTelephone(cur.getString(8));
            obj.setAdressedomicilaire(cur.getString(9));
            obj.setIdsectioncommunalelieuresidence(cur.getLong(10));
            obj.setIdsectioncommunalelieunaissance(cur.getLong(11));
            obj.setPersonnedecontact(cur.getString(12));
            obj.setTelephoneducontact(cur.getString(13));
            obj.setAdresseducontact(cur.getString(14));
            obj.setOccupation(cur.getString(15));
            obj.setNommere(cur.getString(16));
            obj.setLongitude(cur.getDouble(17));
            obj.setLatitude(cur.getDouble(18));
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<Patient> SelectByColumn(Context c, String colonne, String valeur) {
        String query = (((((((((((((((((("select " + "id, ") + "nom, ") + "prenom, ") + "age, ") + "datenaissance, ") + "statut, ") + "onlineid, ") + "sexe, ") + "telephone, ") + "adressedomicilaire, ") + "idsectioncommunalelieuresidence, ") + "idsectioncommunalelieunaissance, ") + "personnedecontact, ") + "telephoneducontact, ") + "adresseducontact, ") + "occupation, ") + "nommere, ") + "longitude, ") + "latitude from Patient where " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<Patient> liste = new ArrayList();
        while (cur.moveToNext()) {
            Patient obj = new Patient();
            obj.setId(cur.getLong(0));
            obj.setNom(cur.getString(1));
            obj.setPrenom(cur.getString(2));
            obj.setAge(cur.getInt(3));
            obj.setDatenaissance(cur.getString(4));
            obj.setStatut(cur.getString(5));
            obj.setOnlineid(cur.getLong(6));
            obj.setSexe(cur.getString(7));
            obj.setTelephone(cur.getString(8));
            obj.setAdressedomicilaire(cur.getString(9));
            obj.setIdsectioncommunalelieuresidence(cur.getLong(10));
            obj.setIdsectioncommunalelieunaissance(cur.getLong(11));
            obj.setPersonnedecontact(cur.getString(12));
            obj.setTelephoneducontact(cur.getString(13));
            obj.setAdresseducontact(cur.getString(14));
            obj.setOccupation(cur.getString(15));
            obj.setNommere(cur.getString(16));
            obj.setLongitude(cur.getDouble(17));
            obj.setLatitude(cur.getDouble(18));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static List<Long> SelectByName(Context c, String valeur) {
        String query = "select " + "onlineid from Patient where nom LIKE '%" + valeur + "%' or prenom LIKE '%" + valeur + "%'";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Long> liste = new ArrayList();
        while (cur.moveToNext()) {
            liste.add(Long.valueOf(cur.getLong(0)));
        }
        cur.close();
        db.close();
        return liste;
    }

    public static int Count(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        int l = 0;
        Cursor cur = db.getBdd().rawQuery("select count(*) from patient where " + colonne + " = ?", new String[]{value});
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public String toString() {
        return this.nom;
    }

    public static List<Patient> SelectByUser(Context c, String idUser) {
        String query = (((((((((((((((((("select distinct " + "p.id, ") + "p.nom, ") + "p.prenom, ") + "p.age, ") + "p.datenaissance, ") + "p.statut, ") + "p.onlineid, ") + "p.sexe, ") + "p.telephone, ") + "p.adressedomicilaire, ") + "p.idsectioncommunalelieuresidence, ") + "p.idsectioncommunalelieunaissance, ") + "p.personnedecontact, ") + "p.telephoneducontact, ") + "p.adresseducontact, ") + "p.occupation, ") + "p.nommere, ") + "p.longitude, ") + "p.latitude from Patient p, Suivi2 s where p.onlineid=s.idpatient and s.idagent=?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{idUser});
        List<Patient> liste = new ArrayList();
        while (cur.moveToNext()) {
            Patient obj = new Patient();
            obj.setId(cur.getLong(0));
            obj.setNom(cur.getString(1));
            obj.setPrenom(cur.getString(2));
            obj.setAge(cur.getInt(3));
            obj.setDatenaissance(cur.getString(4));
            obj.setStatut(cur.getString(5));
            obj.setOnlineid(cur.getLong(6));
            obj.setSexe(cur.getString(7));
            obj.setTelephone(cur.getString(8));
            obj.setAdressedomicilaire(cur.getString(9));
            obj.setIdsectioncommunalelieuresidence(cur.getLong(10));
            obj.setIdsectioncommunalelieunaissance(cur.getLong(11));
            obj.setPersonnedecontact(cur.getString(12));
            obj.setTelephoneducontact(cur.getString(13));
            obj.setAdresseducontact(cur.getString(14));
            obj.setOccupation(cur.getString(15));
            obj.setNommere(cur.getString(16));
            obj.setLongitude(cur.getDouble(17));
            obj.setLatitude(cur.getDouble(18));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }
}
