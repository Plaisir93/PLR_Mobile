package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import ht.solutions.plr.data.Database;
import java.util.ArrayList;
import java.util.List;

public class Suivi2 {
    private double accuracy;
    private String autretrouvaille = "";
    private boolean besoindetransfert;
    private String centresuiviailleurs = "";
    private String data1 = "";
    private String data2 = "";
    private String data3 = "";
    private String datedeces = "";
    private String dateprevue = "";
    private String dateretourpromise = "";
    private boolean decesdanslafamille;
    private String decesrapportepar = "";
    private boolean done;
    private String done_by = "";
    private String done_on = "";
    private String errorMessage;
    private boolean fraistransport;
    private boolean gpsisforpatient;
    private long id;
    private long idagent;
    private int idobservationvisite;
    private long idpatient;
    private double latitude;
    private String lieuvisite = "";
    private double longitude;
    private String medoc_autremedicament = "";
    private boolean medoc_boitsesarv;
    private boolean medoc_cotrimox;
    private String medoc_datelivraison = "";
    private String medoc_dureelivraison = "";
    private boolean medoc_effetsecondaire;
    private boolean medoc_fluconazole;
    private boolean medoc_inh;
    private boolean medoc_patientseportebien;
    private boolean medoc_ratesesdoses;
    private boolean medoc_recu;
    private String medoc_regime = "";
    private boolean migration;
    private String motifsuiviailleurs = "";
    private boolean occupation;
    private long onlineid;
    private boolean oubli;
    private Patient patient;
    private boolean patientacceptederetourner;
    private boolean patientdecede;
    private boolean patientensuiviailleurs;
    private boolean patientrefusederetourner;
    private boolean peurdetrevu;
    private String raisonrefus = "";
    private String raisonvisite = "";
    private String receive_on = "";
    private boolean received;
    private boolean recherchesoins;
    private boolean refusderecevoirsoins;
    private boolean servicesinsatisfaits;
    private String statut = "";
    private boolean stigmatisation;
    private boolean sync;
    private String sync_by = "";
    private String sync_on = "";
    private boolean tropmalade;
    private boolean voyage;

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public static void DeleteByAgent(Context c, Long idu) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("Suivi2", "sync=1 and idagent = ?", new String[]{String.valueOf(idu)});
        db.close();
    }

    public static String list_id(Context c, long idu) {
        String query = ("select " + "onlineid") + " from Suivi2 where idagent = " + idu + " and sync = 0";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        String ids = "0";
        while (cur.moveToNext()) {
            ids = ids + "-" + cur.getString(0);
        }
        cur.close();
        db.close();
        return ids;
    }

    public static void Synchronise(Context c, Suivi2 obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("sync", Boolean.valueOf(true));
        db.getBdd().update("Suivi2", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public String DataSafetoSave() {
        if (this.patientacceptederetourner && this.dateretourpromise.equals("")) {
            return "Précisez la date de retour à la clinique";
        }
        if (this.patientdecede && this.decesrapportepar.equals("")) {
            return "Précisez le nom de la personne qui a rapporté le décès";
        }
        if (this.patientdecede && this.datedeces.equals("")) {
            return "Précisez la date du décès";
        }
        if (this.idobservationvisite == 0 || this.idobservationvisite == 1) {
            return "Précisez l'observation faite lors de la visite";
        }
        return "";
    }

    public long getId() {
        return this.id;
    }

    public void setId(long value) {
        this.id = value;
    }

    public String getRaisonvisite() {
        return this.raisonvisite;
    }

    public void setRaisonvisite(String value) {
        this.raisonvisite = value;
    }

    public long getIdagent() {
        return this.idagent;
    }

    public void setIdagent(long value) {
        this.idagent = value;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setStatut(String value) {
        this.statut = value;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean value) {
        this.done = value;
    }

    public boolean isSync() {
        return this.sync;
    }

    public void setSync(boolean value) {
        this.sync = value;
    }

    public int getIdobservationvisite() {
        return this.idobservationvisite;
    }

    public void setIdobservationvisite(int value) {
        this.idobservationvisite = value;
    }

    public String getDateprevue() {
        return this.dateprevue;
    }

    public void setDateprevue(String value) {
        this.dateprevue = value;
    }

    public boolean isPatientacceptederetourner() {
        return this.patientacceptederetourner;
    }

    public void setPatientacceptederetourner(boolean value) {
        this.patientacceptederetourner = value;
    }

    public String getDateretourpromise() {
        return this.dateretourpromise;
    }

    public void setDateretourpromise(String value) {
        this.dateretourpromise = value;
    }

    public boolean isPatientrefusederetourner() {
        return this.patientrefusederetourner;
    }

    public void setPatientrefusederetourner(boolean value) {
        this.patientrefusederetourner = value;
    }

    public String getRaisonrefus() {
        return this.raisonrefus;
    }

    public void setRaisonrefus(String value) {
        this.raisonrefus = value;
    }

    public boolean isPatientensuiviailleurs() {
        return this.patientensuiviailleurs;
    }

    public void setPatientensuiviailleurs(boolean value) {
        this.patientensuiviailleurs = value;
    }

    public String getCentresuiviailleurs() {
        return this.centresuiviailleurs;
    }

    public void setCentresuiviailleurs(String value) {
        this.centresuiviailleurs = value;
    }

    public String getMotifsuiviailleurs() {
        return this.motifsuiviailleurs;
    }

    public void setMotifsuiviailleurs(String value) {
        this.motifsuiviailleurs = value;
    }

    public boolean isPatientdecede() {
        return this.patientdecede;
    }

    public void setPatientdecede(boolean value) {
        this.patientdecede = value;
    }

    public String getDatedeces() {
        return this.datedeces;
    }

    public void setDatedeces(String value) {
        this.datedeces = value;
    }

    public String getDecesrapportepar() {
        return this.decesrapportepar;
    }

    public void setDecesrapportepar(String value) {
        this.decesrapportepar = value;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double value) {
        this.latitude = value;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double value) {
        this.longitude = value;
    }

    public String getReceive_on() {
        return this.receive_on;
    }

    public void setReceive_on(String value) {
        this.receive_on = value;
    }

    public String getDone_on() {
        return this.done_on;
    }

    public void setDone_on(String value) {
        this.done_on = value;
    }

    public String getSync_on() {
        return this.sync_on;
    }

    public void setSync_on(String value) {
        this.sync_on = value;
    }

    public String getDone_by() {
        return this.done_by;
    }

    public void setDone_by(String value) {
        this.done_by = value;
    }

    public String getSync_by() {
        return this.sync_by;
    }

    public void setSync_by(String value) {
        this.sync_by = value;
    }

    public boolean isFraistransport() {
        return this.fraistransport;
    }

    public void setFraistransport(boolean value) {
        this.fraistransport = value;
    }

    public boolean isTropmalade() {
        return this.tropmalade;
    }

    public void setTropmalade(boolean value) {
        this.tropmalade = value;
    }

    public boolean isOubli() {
        return this.oubli;
    }

    public void setOubli(boolean value) {
        this.oubli = value;
    }

    public boolean isRecherchesoins() {
        return this.recherchesoins;
    }

    public void setRecherchesoins(boolean value) {
        this.recherchesoins = value;
    }

    public boolean isPeurdetrevu() {
        return this.peurdetrevu;
    }

    public void setPeurdetrevu(boolean value) {
        this.peurdetrevu = value;
    }

    public boolean isServicesinsatisfaits() {
        return this.servicesinsatisfaits;
    }

    public void setServicesinsatisfaits(boolean value) {
        this.servicesinsatisfaits = value;
    }

    public long getOnlineid() {
        return this.onlineid;
    }

    public void setOnlineid(long value) {
        this.onlineid = value;
    }

    public long getIdpatient() {
        return this.idpatient;
    }

    public void setIdpatient(long value) {
        this.idpatient = value;
    }

    public boolean isVoyage() {
        return this.voyage;
    }

    public void setVoyage(boolean value) {
        this.voyage = value;
    }

    public boolean isMigration() {
        return this.migration;
    }

    public void setMigration(boolean value) {
        this.migration = value;
    }

    public boolean isStigmatisation() {
        return this.stigmatisation;
    }

    public void setStigmatisation(boolean value) {
        this.stigmatisation = value;
    }

    public boolean isOccupation() {
        return this.occupation;
    }

    public void setOccupation(boolean value) {
        this.occupation = value;
    }

    public boolean isDecesdanslafamille() {
        return this.decesdanslafamille;
    }

    public void setDecesdanslafamille(boolean value) {
        this.decesdanslafamille = value;
    }

    public boolean isBesoindetransfert() {
        return this.besoindetransfert;
    }

    public void setBesoindetransfert(boolean value) {
        this.besoindetransfert = value;
    }

    public String getAutretrouvaille() {
        return this.autretrouvaille;
    }

    public void setAutretrouvaille(String value) {
        this.autretrouvaille = value;
    }

    public boolean isReceived() {
        return this.received;
    }

    public void setReceived(boolean value) {
        this.received = value;
    }

    public double getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(double value) {
        this.accuracy = value;
    }

    public String getMedoc_dureelivraison() {
        return this.medoc_dureelivraison;
    }

    public void setMedoc_dureelivraison(String value) {
        this.medoc_dureelivraison = value;
    }

    public boolean isMedoc_boitsesarv() {
        return this.medoc_boitsesarv;
    }

    public void setMedoc_boitsesarv(boolean value) {
        this.medoc_boitsesarv = value;
    }

    public boolean isMedoc_recu() {
        return this.medoc_recu;
    }

    public void setMedoc_recu(boolean value) {
        this.medoc_recu = value;
    }

    public boolean isMedoc_ratesesdoses() {
        return this.medoc_ratesesdoses;
    }

    public void setMedoc_ratesesdoses(boolean value) {
        this.medoc_ratesesdoses = value;
    }

    public boolean isMedoc_inh() {
        return this.medoc_inh;
    }

    public void setMedoc_inh(boolean value) {
        this.medoc_inh = value;
    }

    public String getMedoc_datelivraison() {
        return this.medoc_datelivraison;
    }

    public void setMedoc_datelivraison(String value) {
        this.medoc_datelivraison = value;
    }

    public String getMedoc_regime() {
        return this.medoc_regime;
    }

    public void setMedoc_regime(String value) {
        this.medoc_regime = value;
    }

    public boolean isMedoc_cotrimox() {
        return this.medoc_cotrimox;
    }

    public void setMedoc_cotrimox(boolean value) {
        this.medoc_cotrimox = value;
    }

    public boolean isMedoc_fluconazole() {
        return this.medoc_fluconazole;
    }

    public void setMedoc_fluconazole(boolean value) {
        this.medoc_fluconazole = value;
    }

    public boolean isMedoc_patientseportebien() {
        return this.medoc_patientseportebien;
    }

    public void setMedoc_patientseportebien(boolean value) {
        this.medoc_patientseportebien = value;
    }

    public boolean isMedoc_effetsecondaire() {
        return this.medoc_effetsecondaire;
    }

    public void setMedoc_effetsecondaire(boolean value) {
        this.medoc_effetsecondaire = value;
    }

    public String getMedoc_autremedicament() {
        return this.medoc_autremedicament;
    }

    public void setMedoc_autremedicament(String value) {
        this.medoc_autremedicament = value;
    }

    public String getLieuvisite() {
        return this.lieuvisite;
    }

    public void setLieuvisite(String value) {
        this.lieuvisite = value;
    }

    public String getData1() {
        return this.data1;
    }

    public void setData1(String value) {
        this.data1 = value;
    }

    public String getData2() {
        return this.data2;
    }

    public void setData2(String value) {
        this.data2 = value;
    }

    public String getData3() {
        return this.data3;
    }

    public void setData3(String value) {
        this.data3 = value;
    }

    public boolean isGpsisforpatient() {
        return this.gpsisforpatient;
    }

    public void setGpsisforpatient(boolean value) {
        this.gpsisforpatient = value;
    }

    public boolean isRefusderecevoirsoins() {
        return this.refusderecevoirsoins;
    }

    public void setRefusderecevoirsoins(boolean value) {
        this.refusderecevoirsoins = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public static String getScript() {
        return ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("CREATE TABLE Suivi2(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,") + "raisonvisite TEXT ,") + "idagent INTEGER ,") + "statut TEXT ,") + "done BOOL ,") + "sync BOOL ,") + "idobservationvisite INTEGER ,") + "dateprevue TEXT ,") + "patientacceptederetourner BOOL ,") + "dateretourpromise TEXT ,") + "patientrefusederetourner BOOL ,") + "raisonrefus TEXT ,") + "patientensuiviailleurs BOOL ,") + "centresuiviailleurs TEXT ,") + "motifsuiviailleurs TEXT ,") + "patientdecede BOOL ,") + "datedeces TEXT ,") + "decesrapportepar TEXT ,") + "latitude REAL ,") + "longitude REAL ,") + "receive_on TEXT ,") + "done_on TEXT ,") + "sync_on TEXT ,") + "done_by TEXT ,") + "sync_by TEXT ,") + "fraistransport BOOL ,") + "tropmalade BOOL ,") + "oubli BOOL ,") + "recherchesoins BOOL ,") + "peurdetrevu BOOL ,") + "servicesinsatisfaits BOOL ,") + "onlineid INTEGER ,") + "idpatient INTEGER ,") + "voyage BOOL ,") + "migration BOOL ,") + "stigmatisation BOOL ,") + "occupation BOOL ,") + "decesdanslafamille BOOL ,") + "besoindetransfert BOOL ,") + "autretrouvaille TEXT ,") + "received BOOL ,") + "accuracy REAL ,") + "medoc_dureelivraison TEXT ,") + "medoc_boitsesarv BOOL ,") + "medoc_recu BOOL ,") + "medoc_ratesesdoses BOOL ,") + "medoc_inh BOOL ,") + "medoc_datelivraison TEXT ,") + "medoc_regime TEXT ,") + "medoc_cotrimox BOOL ,") + "medoc_fluconazole BOOL ,") + "medoc_patientseportebien BOOL ,") + "medoc_effetsecondaire BOOL ,") + "medoc_autremedicament TEXT ,") + "lieuvisite TEXT ,") + "data1 TEXT ,") + "data2 TEXT ,") + "data3 TEXT ,") + "gpsisforpatient BOOL ,") + "refusderecevoirsoins BOOL );";
    }

    public static long Insert(Context c, Suivi2 obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("raisonvisite", obj.getRaisonvisite());
        value.put("idagent", Long.valueOf(obj.getIdagent()));
        value.put("done", Boolean.valueOf(obj.isDone()));
        value.put("sync", Boolean.valueOf(obj.isSync()));
        value.put("dateprevue", obj.getDateprevue());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        value.put("idpatient", Long.valueOf(obj.getIdpatient()));
        value.put("medoc_dureelivraison", obj.getMedoc_dureelivraison());
        value.put("medoc_inh", Boolean.valueOf(obj.isMedoc_inh()));
        value.put("medoc_datelivraison", obj.getMedoc_datelivraison());
        value.put("medoc_regime", obj.getMedoc_regime());
        value.put("medoc_cotrimox", Boolean.valueOf(obj.isMedoc_cotrimox()));
        value.put("medoc_fluconazole", Boolean.valueOf(obj.isMedoc_fluconazole()));
        value.put("lieuvisite", obj.getLieuvisite());
        db.getBdd().insert("Suivi2", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from Suivi2", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, Suivi2 obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("raisonvisite", obj.getRaisonvisite());
        value.put("idagent", Long.valueOf(obj.getIdagent()));
        value.put("statut", obj.getStatut());
        value.put("done", Boolean.valueOf(true));
        value.put("sync", Boolean.valueOf(obj.isSync()));
        value.put("idobservationvisite", Integer.valueOf(obj.getIdobservationvisite()));
        value.put("dateprevue", obj.getDateprevue());
        value.put("patientacceptederetourner", Boolean.valueOf(obj.isPatientacceptederetourner()));
        value.put("dateretourpromise", obj.getDateretourpromise());
        value.put("patientrefusederetourner", Boolean.valueOf(obj.isPatientrefusederetourner()));
        value.put("raisonrefus", obj.getRaisonrefus());
        value.put("patientensuiviailleurs", Boolean.valueOf(obj.isPatientensuiviailleurs()));
        value.put("centresuiviailleurs", obj.getCentresuiviailleurs());
        value.put("motifsuiviailleurs", obj.getMotifsuiviailleurs());
        value.put("patientdecede", Boolean.valueOf(obj.isPatientdecede()));
        value.put("datedeces", obj.getDatedeces());
        value.put("decesrapportepar", obj.getDecesrapportepar());
        value.put("latitude", Double.valueOf(obj.getLatitude()));
        value.put("longitude", Double.valueOf(obj.getLongitude()));
        value.put("receive_on", obj.getReceive_on());
        value.put("done_on", obj.getDone_on());
        value.put("sync_on", obj.getSync_on());
        value.put("done_by", obj.getDone_by());
        value.put("sync_by", obj.getSync_by());
        value.put("fraistransport", Boolean.valueOf(obj.isFraistransport()));
        value.put("tropmalade", Boolean.valueOf(obj.isTropmalade()));
        value.put("oubli", Boolean.valueOf(obj.isOubli()));
        value.put("recherchesoins", Boolean.valueOf(obj.isRecherchesoins()));
        value.put("peurdetrevu", Boolean.valueOf(obj.isPeurdetrevu()));
        value.put("servicesinsatisfaits", Boolean.valueOf(obj.isServicesinsatisfaits()));
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        value.put("idpatient", Long.valueOf(obj.getIdpatient()));
        value.put("voyage", Boolean.valueOf(obj.isVoyage()));
        value.put("migration", Boolean.valueOf(obj.isMigration()));
        value.put("stigmatisation", Boolean.valueOf(obj.isStigmatisation()));
        value.put("occupation", Boolean.valueOf(obj.isOccupation()));
        value.put("decesdanslafamille", Boolean.valueOf(obj.isDecesdanslafamille()));
        value.put("besoindetransfert", Boolean.valueOf(obj.isBesoindetransfert()));
        value.put("autretrouvaille", obj.getAutretrouvaille());
        value.put("received", Boolean.valueOf(obj.isReceived()));
        value.put("accuracy", Double.valueOf(obj.getAccuracy()));
        value.put("medoc_dureelivraison", obj.getMedoc_dureelivraison());
        value.put("medoc_boitsesarv", Boolean.valueOf(obj.isMedoc_boitsesarv()));
        value.put("medoc_recu", Boolean.valueOf(obj.isMedoc_recu()));
        value.put("medoc_ratesesdoses", Boolean.valueOf(obj.isMedoc_ratesesdoses()));
        value.put("medoc_inh", Boolean.valueOf(obj.isMedoc_inh()));
        value.put("medoc_datelivraison", obj.getMedoc_datelivraison());
        value.put("medoc_regime", obj.getMedoc_regime());
        value.put("medoc_cotrimox", Boolean.valueOf(obj.isMedoc_cotrimox()));
        value.put("medoc_fluconazole", Boolean.valueOf(obj.isMedoc_fluconazole()));
        value.put("medoc_patientseportebien", Boolean.valueOf(obj.isMedoc_patientseportebien()));
        value.put("medoc_effetsecondaire", Boolean.valueOf(obj.isMedoc_effetsecondaire()));
        value.put("medoc_autremedicament", obj.getMedoc_autremedicament());
        value.put("lieuvisite", obj.getLieuvisite());
        value.put("data1", obj.getData1());
        value.put("data2", obj.getData2());
        value.put("data3", obj.getData3());
        value.put("gpsisforpatient", Boolean.valueOf(obj.isGpsisforpatient()));
        value.put("refusderecevoirsoins", Boolean.valueOf(obj.isRefusderecevoirsoins()));
        db.getBdd().update("Suivi2", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("Suivi2", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static List<Suivi2> SelectAllForSync(Context c, long idagent) {
        String query = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("select " + "id, ") + "raisonvisite, ") + "idagent, ") + "statut, ") + "done, ") + "sync, ") + "idobservationvisite, ") + "dateprevue, ") + "patientacceptederetourner, ") + "dateretourpromise, ") + "patientrefusederetourner, ") + "raisonrefus, ") + "patientensuiviailleurs, ") + "centresuiviailleurs, ") + "motifsuiviailleurs, ") + "patientdecede, ") + "datedeces, ") + "decesrapportepar, ") + "latitude, ") + "longitude, ") + "receive_on, ") + "done_on, ") + "sync_on, ") + "done_by, ") + "sync_by, ") + "fraistransport, ") + "tropmalade, ") + "oubli, ") + "recherchesoins, ") + "peurdetrevu, ") + "servicesinsatisfaits, ") + "onlineid, ") + "idpatient, ") + "voyage, ") + "migration, ") + "stigmatisation, ") + "occupation, ") + "decesdanslafamille, ") + "besoindetransfert, ") + "autretrouvaille, ") + "received, ") + "accuracy, ") + "medoc_dureelivraison, ") + "medoc_boitsesarv, ") + "medoc_recu, ") + "medoc_ratesesdoses, ") + "medoc_inh, ") + "medoc_datelivraison, ") + "medoc_regime, ") + "medoc_cotrimox, ") + "medoc_fluconazole, ") + "medoc_patientseportebien, ") + "medoc_effetsecondaire, ") + "medoc_autremedicament, ") + "lieuvisite, ") + "data1, ") + "data2, ") + "data3, ") + "gpsisforpatient, ") + "refusderecevoirsoins from Suivi2 where done = 1 and sync = 0 and idagent = " + idagent;
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Suivi2> liste = new ArrayList();
        while (cur.moveToNext()) {
            boolean z;
            Suivi2 obj = new Suivi2();
            obj.setId(cur.getLong(0));
            obj.setRaisonvisite(cur.getString(1));
            obj.setIdagent(cur.getLong(2));
            obj.setStatut(cur.getString(3));
            obj.setDone(cur.getInt(4) == 1);
            if (cur.getInt(5) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSync(z);
            obj.setIdobservationvisite(cur.getInt(6));
            obj.setDateprevue(cur.getString(7));
            if (cur.getInt(8) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientacceptederetourner(z);
            obj.setDateretourpromise(cur.getString(9));
            if (cur.getInt(10) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientrefusederetourner(z);
            obj.setRaisonrefus(cur.getString(11));
            if (cur.getInt(12) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientensuiviailleurs(z);
            obj.setCentresuiviailleurs(cur.getString(13));
            obj.setMotifsuiviailleurs(cur.getString(14));
            if (cur.getInt(15) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientdecede(z);
            obj.setDatedeces(cur.getString(16));
            obj.setDecesrapportepar(cur.getString(17));
            obj.setLatitude(cur.getDouble(18));
            obj.setLongitude(cur.getDouble(19));
            obj.setReceive_on(cur.getString(20));
            obj.setDone_on(cur.getString(21));
            obj.setSync_on(cur.getString(22));
            obj.setDone_by(cur.getString(23));
            obj.setSync_by(cur.getString(24));
            if (cur.getInt(25) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setFraistransport(z);
            if (cur.getInt(26) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setTropmalade(z);
            if (cur.getInt(27) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setOubli(z);
            if (cur.getInt(28) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setRecherchesoins(z);
            if (cur.getInt(29) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPeurdetrevu(z);
            if (cur.getInt(30) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setServicesinsatisfaits(z);
            obj.setOnlineid(cur.getLong(31));
            obj.setIdpatient(cur.getLong(32));
            if (cur.getInt(33) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setVoyage(z);
            if (cur.getInt(34) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMigration(z);
            if (cur.getInt(35) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setStigmatisation(z);
            if (cur.getInt(36) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setOccupation(z);
            if (cur.getInt(37) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setDecesdanslafamille(z);
            if (cur.getInt(38) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBesoindetransfert(z);
            obj.setAutretrouvaille(cur.getString(39));
            if (cur.getInt(40) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setReceived(z);
            obj.setAccuracy(cur.getDouble(41));
            obj.setMedoc_dureelivraison(cur.getString(42));
            if (cur.getInt(43) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_boitsesarv(z);
            if (cur.getInt(44) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_recu(z);
            if (cur.getInt(45) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_ratesesdoses(z);
            if (cur.getInt(46) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_inh(z);
            obj.setMedoc_datelivraison(cur.getString(47));
            obj.setMedoc_regime(cur.getString(48));
            if (cur.getInt(49) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_cotrimox(z);
            if (cur.getInt(50) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_fluconazole(z);
            if (cur.getInt(51) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_patientseportebien(z);
            if (cur.getInt(52) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_effetsecondaire(z);
            obj.setMedoc_autremedicament(cur.getString(53));
            obj.setLieuvisite(cur.getString(54));
            obj.setData1(cur.getString(55));
            obj.setData2(cur.getString(56));
            obj.setData3(cur.getString(57));
            if (cur.getInt(58) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setGpsisforpatient(z);
            if (cur.getInt(59) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setRefusderecevoirsoins(z);
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static List<Suivi2> SelectAll(Context c, long idagent) {
        String query = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("select " + "id, ") + "raisonvisite, ") + "idagent, ") + "statut, ") + "done, ") + "sync, ") + "idobservationvisite, ") + "dateprevue, ") + "patientacceptederetourner, ") + "dateretourpromise, ") + "patientrefusederetourner, ") + "raisonrefus, ") + "patientensuiviailleurs, ") + "centresuiviailleurs, ") + "motifsuiviailleurs, ") + "patientdecede, ") + "datedeces, ") + "decesrapportepar, ") + "latitude, ") + "longitude, ") + "receive_on, ") + "done_on, ") + "sync_on, ") + "done_by, ") + "sync_by, ") + "fraistransport, ") + "tropmalade, ") + "oubli, ") + "recherchesoins, ") + "peurdetrevu, ") + "servicesinsatisfaits, ") + "onlineid, ") + "idpatient, ") + "voyage, ") + "migration, ") + "stigmatisation, ") + "occupation, ") + "decesdanslafamille, ") + "besoindetransfert, ") + "autretrouvaille, ") + "received, ") + "accuracy, ") + "medoc_dureelivraison, ") + "medoc_boitsesarv, ") + "medoc_recu, ") + "medoc_ratesesdoses, ") + "medoc_inh, ") + "medoc_datelivraison, ") + "medoc_regime, ") + "medoc_cotrimox, ") + "medoc_fluconazole, ") + "medoc_patientseportebien, ") + "medoc_effetsecondaire, ") + "medoc_autremedicament, ") + "lieuvisite, ") + "data1, ") + "data2, ") + "data3, ") + "gpsisforpatient, ") + "refusderecevoirsoins from Suivi2 where idagent = " + idagent;
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Suivi2> liste = new ArrayList();
        while (cur.moveToNext()) {
            boolean z;
            Suivi2 obj = new Suivi2();
            obj.setId(cur.getLong(0));
            obj.setRaisonvisite(cur.getString(1));
            obj.setIdagent(cur.getLong(2));
            obj.setStatut(cur.getString(3));
            obj.setDone(cur.getInt(4) == 1);
            if (cur.getInt(5) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSync(z);
            obj.setIdobservationvisite(cur.getInt(6));
            obj.setDateprevue(cur.getString(7));
            if (cur.getInt(8) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientacceptederetourner(z);
            obj.setDateretourpromise(cur.getString(9));
            if (cur.getInt(10) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientrefusederetourner(z);
            obj.setRaisonrefus(cur.getString(11));
            if (cur.getInt(12) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientensuiviailleurs(z);
            obj.setCentresuiviailleurs(cur.getString(13));
            obj.setMotifsuiviailleurs(cur.getString(14));
            if (cur.getInt(15) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientdecede(z);
            obj.setDatedeces(cur.getString(16));
            obj.setDecesrapportepar(cur.getString(17));
            obj.setLatitude(cur.getDouble(18));
            obj.setLongitude(cur.getDouble(19));
            obj.setReceive_on(cur.getString(20));
            obj.setDone_on(cur.getString(21));
            obj.setSync_on(cur.getString(22));
            obj.setDone_by(cur.getString(23));
            obj.setSync_by(cur.getString(24));
            if (cur.getInt(25) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setFraistransport(z);
            if (cur.getInt(26) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setTropmalade(z);
            if (cur.getInt(27) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setOubli(z);
            if (cur.getInt(28) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setRecherchesoins(z);
            if (cur.getInt(29) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPeurdetrevu(z);
            if (cur.getInt(30) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setServicesinsatisfaits(z);
            obj.setOnlineid(cur.getLong(31));
            obj.setIdpatient(cur.getLong(32));
            if (cur.getInt(33) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setVoyage(z);
            if (cur.getInt(34) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMigration(z);
            if (cur.getInt(35) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setStigmatisation(z);
            if (cur.getInt(36) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setOccupation(z);
            if (cur.getInt(37) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setDecesdanslafamille(z);
            if (cur.getInt(38) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBesoindetransfert(z);
            obj.setAutretrouvaille(cur.getString(39));
            if (cur.getInt(40) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setReceived(z);
            obj.setAccuracy(cur.getDouble(41));
            obj.setMedoc_dureelivraison(cur.getString(42));
            if (cur.getInt(43) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_boitsesarv(z);
            if (cur.getInt(44) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_recu(z);
            if (cur.getInt(45) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_ratesesdoses(z);
            if (cur.getInt(46) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_inh(z);
            obj.setMedoc_datelivraison(cur.getString(47));
            obj.setMedoc_regime(cur.getString(48));
            if (cur.getInt(49) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_cotrimox(z);
            if (cur.getInt(50) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_fluconazole(z);
            if (cur.getInt(51) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_patientseportebien(z);
            if (cur.getInt(52) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_effetsecondaire(z);
            obj.setMedoc_autremedicament(cur.getString(53));
            obj.setLieuvisite(cur.getString(54));
            obj.setData1(cur.getString(55));
            obj.setData2(cur.getString(56));
            obj.setData3(cur.getString(57));
            if (cur.getInt(58) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setGpsisforpatient(z);
            if (cur.getInt(59) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setRefusderecevoirsoins(z);
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static Suivi2 SelectById(Context c, long id) {
        boolean z = true;
        String query = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("select " + "id, ") + "raisonvisite, ") + "idagent, ") + "statut, ") + "done, ") + "sync, ") + "idobservationvisite, ") + "dateprevue, ") + "patientacceptederetourner, ") + "dateretourpromise, ") + "patientrefusederetourner, ") + "raisonrefus, ") + "patientensuiviailleurs, ") + "centresuiviailleurs, ") + "motifsuiviailleurs, ") + "patientdecede, ") + "datedeces, ") + "decesrapportepar, ") + "latitude, ") + "longitude, ") + "receive_on, ") + "done_on, ") + "sync_on, ") + "done_by, ") + "sync_by, ") + "fraistransport, ") + "tropmalade, ") + "oubli, ") + "recherchesoins, ") + "peurdetrevu, ") + "servicesinsatisfaits, ") + "onlineid, ") + "idpatient, ") + "voyage, ") + "migration, ") + "stigmatisation, ") + "occupation, ") + "decesdanslafamille, ") + "besoindetransfert, ") + "autretrouvaille, ") + "received, ") + "accuracy, ") + "medoc_dureelivraison, ") + "medoc_boitsesarv, ") + "medoc_recu, ") + "medoc_ratesesdoses, ") + "medoc_inh, ") + "medoc_datelivraison, ") + "medoc_regime, ") + "medoc_cotrimox, ") + "medoc_fluconazole, ") + "medoc_patientseportebien, ") + "medoc_effetsecondaire, ") + "medoc_autremedicament, ") + "lieuvisite, ") + "data1, ") + "data2, ") + "data3, ") + "gpsisforpatient, ") + "refusderecevoirsoins from Suivi2 where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            boolean z2;
            Suivi2 obj = new Suivi2();
            obj.setId(cur.getLong(0));
            obj.setRaisonvisite(cur.getString(1));
            obj.setIdagent(cur.getLong(2));
            obj.setStatut(cur.getString(3));
            if (cur.getInt(4) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setDone(z2);
            if (cur.getInt(5) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setSync(z2);
            obj.setIdobservationvisite(cur.getInt(6));
            obj.setDateprevue(cur.getString(7));
            if (cur.getInt(8) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPatientacceptederetourner(z2);
            obj.setDateretourpromise(cur.getString(9));
            if (cur.getInt(10) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPatientrefusederetourner(z2);
            obj.setRaisonrefus(cur.getString(11));
            if (cur.getInt(12) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPatientensuiviailleurs(z2);
            obj.setCentresuiviailleurs(cur.getString(13));
            obj.setMotifsuiviailleurs(cur.getString(14));
            if (cur.getInt(15) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPatientdecede(z2);
            obj.setDatedeces(cur.getString(16));
            obj.setDecesrapportepar(cur.getString(17));
            obj.setLatitude(cur.getDouble(18));
            obj.setLongitude(cur.getDouble(19));
            obj.setReceive_on(cur.getString(20));
            obj.setDone_on(cur.getString(21));
            obj.setSync_on(cur.getString(22));
            obj.setDone_by(cur.getString(23));
            obj.setSync_by(cur.getString(24));
            if (cur.getInt(25) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setFraistransport(z2);
            if (cur.getInt(26) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setTropmalade(z2);
            if (cur.getInt(27) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setOubli(z2);
            if (cur.getInt(28) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setRecherchesoins(z2);
            if (cur.getInt(29) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPeurdetrevu(z2);
            if (cur.getInt(30) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setServicesinsatisfaits(z2);
            obj.setOnlineid(cur.getLong(31));
            obj.setIdpatient(cur.getLong(32));
            if (cur.getInt(33) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setVoyage(z2);
            if (cur.getInt(34) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setMigration(z2);
            if (cur.getInt(35) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setStigmatisation(z2);
            if (cur.getInt(36) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setOccupation(z2);
            if (cur.getInt(37) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setDecesdanslafamille(z2);
            if (cur.getInt(38) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setBesoindetransfert(z2);
            obj.setAutretrouvaille(cur.getString(39));
            if (cur.getInt(40) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setReceived(z2);
            obj.setAccuracy(cur.getDouble(41));
            obj.setMedoc_dureelivraison(cur.getString(42));
            if (cur.getInt(43) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setMedoc_boitsesarv(z2);
            if (cur.getInt(44) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setMedoc_recu(z2);
            if (cur.getInt(45) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setMedoc_ratesesdoses(z2);
            if (cur.getInt(46) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setMedoc_inh(z2);
            obj.setMedoc_datelivraison(cur.getString(47));
            obj.setMedoc_regime(cur.getString(48));
            if (cur.getInt(49) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setMedoc_cotrimox(z2);
            if (cur.getInt(50) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setMedoc_fluconazole(z2);
            if (cur.getInt(51) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setMedoc_patientseportebien(z2);
            if (cur.getInt(52) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setMedoc_effetsecondaire(z2);
            obj.setMedoc_autremedicament(cur.getString(53));
            obj.setLieuvisite(cur.getString(54));
            obj.setData1(cur.getString(55));
            obj.setData2(cur.getString(56));
            obj.setData3(cur.getString(57));
            if (cur.getInt(58) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setGpsisforpatient(z2);
            if (cur.getInt(59) != 1) {
                z = false;
            }
            obj.setRefusderecevoirsoins(z);
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<Suivi2> SelectByColumn(Context c, String colonne, String valeur, long idagent) {
        String query = ((((((((((((((((((((((((((((((((((((((((((((((((((((((((((("select " + "id, ") + "raisonvisite, ") + "idagent, ") + "statut, ") + "done, ") + "sync, ") + "idobservationvisite, ") + "dateprevue, ") + "patientacceptederetourner, ") + "dateretourpromise, ") + "patientrefusederetourner, ") + "raisonrefus, ") + "patientensuiviailleurs, ") + "centresuiviailleurs, ") + "motifsuiviailleurs, ") + "patientdecede, ") + "datedeces, ") + "decesrapportepar, ") + "latitude, ") + "longitude, ") + "receive_on, ") + "done_on, ") + "sync_on, ") + "done_by, ") + "sync_by, ") + "fraistransport, ") + "tropmalade, ") + "oubli, ") + "recherchesoins, ") + "peurdetrevu, ") + "servicesinsatisfaits, ") + "onlineid, ") + "idpatient, ") + "voyage, ") + "migration, ") + "stigmatisation, ") + "occupation, ") + "decesdanslafamille, ") + "besoindetransfert, ") + "autretrouvaille, ") + "received, ") + "accuracy, ") + "medoc_dureelivraison, ") + "medoc_boitsesarv, ") + "medoc_recu, ") + "medoc_ratesesdoses, ") + "medoc_inh, ") + "medoc_datelivraison, ") + "medoc_regime, ") + "medoc_cotrimox, ") + "medoc_fluconazole, ") + "medoc_patientseportebien, ") + "medoc_effetsecondaire, ") + "medoc_autremedicament, ") + "lieuvisite, ") + "data1, ") + "data2, ") + "data3, ") + "gpsisforpatient, ") + "refusderecevoirsoins from Suivi2 where " + colonne + " = ? and idagent = " + idagent;
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<Suivi2> liste = new ArrayList();
        while (cur.moveToNext()) {
            boolean z;
            Suivi2 obj = new Suivi2();
            obj.setId(cur.getLong(0));
            obj.setRaisonvisite(cur.getString(1));
            obj.setIdagent(cur.getLong(2));
            obj.setStatut(cur.getString(3));
            obj.setDone(cur.getInt(4) == 1);
            if (cur.getInt(5) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSync(z);
            obj.setIdobservationvisite(cur.getInt(6));
            obj.setDateprevue(cur.getString(7));
            if (cur.getInt(8) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientacceptederetourner(z);
            obj.setDateretourpromise(cur.getString(9));
            if (cur.getInt(10) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientrefusederetourner(z);
            obj.setRaisonrefus(cur.getString(11));
            if (cur.getInt(12) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientensuiviailleurs(z);
            obj.setCentresuiviailleurs(cur.getString(13));
            obj.setMotifsuiviailleurs(cur.getString(14));
            if (cur.getInt(15) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientdecede(z);
            obj.setDatedeces(cur.getString(16));
            obj.setDecesrapportepar(cur.getString(17));
            obj.setLatitude(cur.getDouble(18));
            obj.setLongitude(cur.getDouble(19));
            obj.setReceive_on(cur.getString(20));
            obj.setDone_on(cur.getString(21));
            obj.setSync_on(cur.getString(22));
            obj.setDone_by(cur.getString(23));
            obj.setSync_by(cur.getString(24));
            if (cur.getInt(25) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setFraistransport(z);
            if (cur.getInt(26) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setTropmalade(z);
            if (cur.getInt(27) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setOubli(z);
            if (cur.getInt(28) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setRecherchesoins(z);
            if (cur.getInt(29) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPeurdetrevu(z);
            if (cur.getInt(30) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setServicesinsatisfaits(z);
            obj.setOnlineid(cur.getLong(31));
            obj.setIdpatient(cur.getLong(32));
            if (cur.getInt(33) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setVoyage(z);
            if (cur.getInt(34) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMigration(z);
            if (cur.getInt(35) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setStigmatisation(z);
            if (cur.getInt(36) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setOccupation(z);
            if (cur.getInt(37) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setDecesdanslafamille(z);
            if (cur.getInt(38) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBesoindetransfert(z);
            obj.setAutretrouvaille(cur.getString(39));
            if (cur.getInt(40) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setReceived(z);
            obj.setAccuracy(cur.getDouble(41));
            obj.setMedoc_dureelivraison(cur.getString(42));
            if (cur.getInt(43) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_boitsesarv(z);
            if (cur.getInt(44) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_recu(z);
            if (cur.getInt(45) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_ratesesdoses(z);
            if (cur.getInt(46) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_inh(z);
            obj.setMedoc_datelivraison(cur.getString(47));
            obj.setMedoc_regime(cur.getString(48));
            if (cur.getInt(49) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_cotrimox(z);
            if (cur.getInt(50) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_fluconazole(z);
            if (cur.getInt(51) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_patientseportebien(z);
            if (cur.getInt(52) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setMedoc_effetsecondaire(z);
            obj.setMedoc_autremedicament(cur.getString(53));
            obj.setLieuvisite(cur.getString(54));
            obj.setData1(cur.getString(55));
            obj.setData2(cur.getString(56));
            obj.setData3(cur.getString(57));
            if (cur.getInt(58) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setGpsisforpatient(z);
            if (cur.getInt(59) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setRefusderecevoirsoins(z);
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
        Cursor cur = db.getBdd().rawQuery("select count(*) from suivi2 where " + colonne + " = ?", new String[]{value});
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public String toString() {
        String rep = "Inconnu";
        if (this.raisonvisite.equals("inactif")) {
            return "Patient inactif";
        }
        if (this.raisonvisite.equals("distribution")) {
            return "Distribution de médicaments";
        }
        if (this.raisonvisite.equals("rendez-vous")) {
            return "Rendez-vous raté";
        }
        if (this.raisonvisite.equals("adresse")) {
            return "Vérification de l'adresse";
        }
        return rep;
    }
}
