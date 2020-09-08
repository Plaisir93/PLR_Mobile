package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import ht.solutions.plr.data.Database;

public class FingerPatient {
    private long id;
    private long refPatient;
    private String date_enregistrer;
    private String finger_template;
    private String finger_image;
    private Patient patient;
    private String errorMessage;
    public long getId() {
        return this.id;
    }
    public void setId(long value){
        this.id = value;
    }
    public long getRefPatient() {return this.refPatient;}
    public void  setRefPatient(long value) {this.refPatient=value;}

    public String getDate_Enregistrer(){
        return this.date_enregistrer;
    }

    public void setDate_Enregistrer(String value){
        this.date_enregistrer = value;
    }

    public String getFinger_template(){
        return this.finger_template;
    }

    public void setFinger_template(String value){
        this.finger_template = value;
    }

    public String getFinger_image(){
        return this.finger_image;
    }

    public void setFinger_image(String value){
        this.finger_image = value;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
     public boolean isSet() {
        return true;
    }

    public static String getScript() {
        String script = "CREATE TABLE FingerPatient(";
        script += "id INTEGER PRIMARY KEY AUTOINCREMENT ,";
        script += "refPatient INTEGER ,";
        script += "date_enregistrer TEXT ,";
        script += "finger_template TEXT ,";
        script += "finger_image TEXT );";
        return script;
    }

    public static long Insert(Context c, FingerPatient obj) {
         FingerPatient fp = null;
        try{
             fp = SelectByColumn(c,"refPatient",obj.getRefPatient()).get(0);
        }catch (Exception e){}
        if(fp == null){
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("refPatient", obj.getRefPatient());
        value.put("date_enregistrer", obj.getDate_Enregistrer());
        value.put("finger_template", obj.getFinger_template());
        value.put("finger_image", obj.getFinger_image());
        db.getBdd().insert("FingerPatient", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from FingerPatient", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
        }
        else
        {
            Update(c,obj);
            return fp.getId();
        }

    }

    public static void Update(Context c, FingerPatient obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("refPatient", obj.getRefPatient());
        value.put("date_enregistrer", obj.getDate_Enregistrer());
        value.put("finger_template", obj.getFinger_template());
        value.put("finger_image", obj.getFinger_image());
        db.getBdd().update("FingerPatient", value, "id = ?",
                new String[] { String.valueOf(obj.getId()) });
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("FingerPatient", colonne + " = ?",
                new String[] { String.valueOf(value) });
        db.close();
    }

    public static List<FingerPatient> SelectAll(Context c) {
        String query = "select ";
        query += "id, ";
        query += "refPatient, ";
        query += "date_enregistrer, ";
        query += "finger_template, ";
        query += "finger_image from FingerPatient";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<FingerPatient> liste = new ArrayList<FingerPatient>();
        while (cur.moveToNext()) {
            FingerPatient obj = new FingerPatient();
            obj.setId(cur.getLong(0));
            obj.setRefPatient(cur.getLong(1));
            obj.setDate_Enregistrer(cur.getString(2));
            obj.setFinger_template(cur.getString(3));
            obj.setFinger_image(cur.getString(4));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static FingerPatient SelectById(Context c, long id) {
        String query = "select ";
        query += "id, ";
        query += "refPatient, ";
        query += "date_enregistrer, ";
        query += "finger_template, ";
        query += "finger_image from FingerPatient where id = ?";
        Database db = new Database(c);
        db.open();
       // db.getBdd().execSQL(FingerPatient.getScript());
        Cursor cur = db.getBdd().rawQuery(query, new String[] { id + "" });

        if (cur.moveToNext()) {
            FingerPatient obj = new FingerPatient();
            obj.setId(cur.getLong(0));
            obj.setRefPatient(cur.getLong(1));
            obj.setDate_Enregistrer(cur.getString(2));
            obj.setFinger_template(cur.getString(3));
            obj.setFinger_image(cur.getString(4));
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static FingerPatient SelectByColumn1(Context c, String colonne, long refPatient) {
        String query = "select ";
        query += "id, ";
        query += "refPatient, ";
        query += "date_enregistrer, ";
        query += "finger_template, ";
        query += "finger_image from FingerPatient where "+ colonne +" = ? limit 1";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[] {Long.toString(refPatient)});
        // List<FingerPatient> liste = new ArrayList<FingerPatient>();
        List<FingerPatient> liste = new ArrayList<FingerPatient>();
        while (cur.moveToNext()) {
            FingerPatient obj = new FingerPatient();
            obj.setId(cur.getLong(0));
            obj.setRefPatient(cur.getLong(1));
            obj.setDate_Enregistrer(cur.getString(2));
            obj.setFinger_template(cur.getString(3));
            obj.setFinger_image(cur.getString(4));
            return  obj;
        }
        cur.close();
        db.close();
        return null;
    }
    public static List<FingerPatient> SelectByColumn(Context c, String colonne, long refPatient) {
        String query = "select ";
        query += "id, ";
        query += "refPatient, ";
        query += "date_enregistrer, ";
        query += "finger_template, ";
        query += "finger_image from FingerPatient where "+ colonne +" = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[] {Long.toString(refPatient)});
        // List<FingerPatient> liste = new ArrayList<FingerPatient>();
        List<FingerPatient> liste = new ArrayList<FingerPatient>();
        while (cur.moveToNext()) {
            FingerPatient obj = new FingerPatient();
            obj.setId(cur.getLong(0));
            obj.setRefPatient(cur.getLong(1));
            obj.setDate_Enregistrer(cur.getString(2));
            obj.setFinger_template(cur.getString(3));
            obj.setFinger_image(cur.getString(4));
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }




    public static Cursor SelectByColumnCur(Context c, String colonne, long refPatient) {
        String query = "select ";
        query += "id, ";
        query += "refPatient, ";
        query += "date_enregistrer, ";
        query += "finger_template, ";
        query += "finger_image from FingerPatient where "+ colonne +" = ?";
        Database db = new Database(c);
        db.open();
        db.open();
        // db.getBdd().execSQL("ALTER TABLE personne ADD COLUMN date_enregistrer TEXT");
        Cursor cur = db.getBdd().rawQuery(query, new String[] {Long.toString(refPatient)});
        //  cur.close();
        // db.close();
        return cur;
    }
  /*  public static List<Long> SelectByName(Context c, String valeur) {
        String query = "select ";
        query += "onlineid from Patient where nom LIKE '%"+valeur+"%' or prenom LIKE '%"+valeur+"%'";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);

        List<Long> liste = new ArrayList<Long>();
        while (cur.moveToNext()) {
            liste.add(cur.getLong(0));
        }
        cur.close();
        db.close();
        return liste;
    }
*/
    public static int Count(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        int l = 0;
        Cursor cur = db.getBdd().rawQuery(
                "select count(*) from FingerPatient where " + colonne + " = ?",
                new String[] { value });
        if (cur.moveToNext())
            l = cur.getInt(0);
        cur.close();
        db.close();
        return l;
    }

  /*  @Override
    public String toString() {
        return nom;
    }
*/
  /*  public static List<Patient> SelectByUser(Context c, String idUser)
    {
        String query = "select distinct ";
        query += "p.id, ";
        query += "p.nom, ";
        query += "p.prenom, ";
        query += "p.age, ";
        query += "p.datenaissance, ";
        query += "p.statut, ";
        query += "p.onlineid, ";
        query += "p.sexe, ";
        query += "p.telephone, ";
        query += "p.adressedomicilaire, ";
        query += "p.idsectioncommunalelieuresidence, ";
        query += "p.idsectioncommunalelieunaissance, ";
        query += "p.personnedecontact, ";
        query += "p.telephoneducontact, ";
        query += "p.adresseducontact, ";
        query += "p.occupation, ";
        query += "p.nommere, ";
        query += "p.longitude, ";
        query += "p.latitude from Patient p, Suivi2 s where p.onlineid=s.idpatient and s.idagent=?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[] { idUser });

        List<Patient> liste = new ArrayList<Patient>();
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
    }*/
}
