package ht.solutions.plr.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import ht.solutions.plr.data.Database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Suivi {
    private boolean accouchementenmilieuhospitalier;
    private String autreobservation = "";
    private String autreraison = "";
    private boolean bbabeneficiepcr;
    private boolean bbainitieprophylaxie;
    private boolean bbexposeenvie;
    private boolean bbsuiviailleurs;
    private String centrereceveurbb = "";
    private String centrereceveurpatient = "";
    private String dateaccouchement = "";
    private String datebbsuiviailleurs = "";
    private String datedecespatient = "";
    private String dateenregistrement;
    private String datepatientsuiviailleurs = "";
    private String dateretourpatient = "";
    private String datesuvi = "";
    private String decespatientrapportepar = "";
    private boolean done;
    private String errorMessage;
    private boolean fraistransport;
    private long id;
    private long idobservationvisite;
    private long idpatient;
    private long idraisonrattement;
    private long idraisonvisite;
    private long idsensibilisation;
    private long iduser;
    private double latitude;
    private double longitude;
    private String motifbbsuiviailleurs = "";
    private String motifpatientsuiviailleurs = "";
    private long onlineid;
    private boolean oubli;
    private Patient patient = new Patient();
    private boolean patientdecede;
    private boolean patientnonretrouve;
    private boolean patientrefuse;
    private boolean patientretourne;
    private boolean patientsuiviailleurs;
    private boolean peurdetrevuaucentre;
    private String raisonrefusnonretour = "";
    private List<RaisonVisite> raisons = new ArrayList();
    private boolean seen;
    private boolean servicesinsatisfaisants;
    private boolean soinsalternatifs;
    private Suivi_Medicament suivimedicament;
    private boolean synchronised;
    private boolean tropmalade;

    public Suivi_Medicament getSuivimedicament() {
        return this.suivimedicament;
    }

    public void setSuivimedicament(Suivi_Medicament suivimedicament) {
        this.suivimedicament = suivimedicament;
    }

    public String getDateenregistrement() {
        return this.dateenregistrement;
    }

    public void setDateenregistrement(String dateenregistrement) {
        this.dateenregistrement = dateenregistrement;
    }

    public long getIduser() {
        return this.iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public long getId() {
        return this.id;
    }

    public void loadRaisonVisite(Context c) {
        if (this.id > 0) {
            this.raisons = RaisonVisite.SelectByColumn(c, "idsuivi", "" + this.id);
        }
    }

    public void setId(long value) {
        this.id = value;
    }

    public List<RaisonVisite> getRaisons() {
        return this.raisons;
    }

    public void setRaisons(List<RaisonVisite> raisons) {
        this.raisons = raisons;
    }

    public String getDatesuvi() {
        return this.datesuvi;
    }

    public void setDatesuvi(String value) {
        this.datesuvi = value;
    }

    public boolean isAccouchementenmilieuhospitalier() {
        return this.accouchementenmilieuhospitalier;
    }

    public void setAccouchementenmilieuhospitalier(boolean value) {
        this.accouchementenmilieuhospitalier = value;
    }

    public String getDateaccouchement() {
        return this.dateaccouchement;
    }

    public void setDateaccouchement(String value) {
        this.dateaccouchement = value;
    }

    public boolean isBbexposeenvie() {
        return this.bbexposeenvie;
    }

    public void setBbexposeenvie(boolean value) {
        this.bbexposeenvie = value;
    }

    public boolean isBbainitieprophylaxie() {
        return this.bbainitieprophylaxie;
    }

    public void setBbainitieprophylaxie(boolean value) {
        this.bbainitieprophylaxie = value;
    }

    public boolean isBbabeneficiepcr() {
        return this.bbabeneficiepcr;
    }

    public void setBbabeneficiepcr(boolean value) {
        this.bbabeneficiepcr = value;
    }

    public boolean isBbsuiviailleurs() {
        return this.bbsuiviailleurs;
    }

    public void setBbsuiviailleurs(boolean value) {
        this.bbsuiviailleurs = value;
    }

    public String getDatebbsuiviailleurs() {
        return this.datebbsuiviailleurs;
    }

    public void setDatebbsuiviailleurs(String value) {
        this.datebbsuiviailleurs = value;
    }

    public String getMotifbbsuiviailleurs() {
        return this.motifbbsuiviailleurs;
    }

    public void setMotifbbsuiviailleurs(String value) {
        this.motifbbsuiviailleurs = value;
    }

    public String getCentrereceveurbb() {
        return this.centrereceveurbb;
    }

    public void setCentrereceveurbb(String value) {
        this.centrereceveurbb = value;
    }

    public boolean isPatientretourne() {
        return this.patientretourne;
    }

    public void setPatientretourne(boolean value) {
        this.patientretourne = value;
    }

    public String getRaisonrefusnonretour() {
        return this.raisonrefusnonretour;
    }

    public void setRaisonrefusnonretour(String value) {
        this.raisonrefusnonretour = value;
    }

    public boolean isPatientsuiviailleurs() {
        return this.patientsuiviailleurs;
    }

    public void setPatientsuiviailleurs(boolean value) {
        this.patientsuiviailleurs = value;
    }

    public String getDatepatientsuiviailleurs() {
        return this.datepatientsuiviailleurs;
    }

    public void setDatepatientsuiviailleurs(String value) {
        this.datepatientsuiviailleurs = value;
    }

    public String getMotifpatientsuiviailleurs() {
        return this.motifpatientsuiviailleurs;
    }

    public void setMotifpatientsuiviailleurs(String value) {
        this.motifpatientsuiviailleurs = value;
    }

    public String getCentrereceveurpatient() {
        return this.centrereceveurpatient;
    }

    public void setCentrereceveurpatient(String value) {
        this.centrereceveurpatient = value;
    }

    public boolean isPatientdecede() {
        return this.patientdecede;
    }

    public void setPatientdecede(boolean value) {
        this.patientdecede = value;
    }

    public String getDatedecespatient() {
        return this.datedecespatient;
    }

    public void setDatedecespatient(String value) {
        this.datedecespatient = value;
    }

    public String getDecespatientrapportepar() {
        return this.decespatientrapportepar;
    }

    public void setDecespatientrapportepar(String value) {
        this.decespatientrapportepar = value;
    }

    public boolean isPatientnonretrouve() {
        return this.patientnonretrouve;
    }

    public void setPatientnonretrouve(boolean value) {
        this.patientnonretrouve = value;
    }

    public String getDateretourpatient() {
        return this.dateretourpatient;
    }

    public void setDateretourpatient(String value) {
        this.dateretourpatient = value;
    }

    public boolean isPatientrefuse() {
        return this.patientrefuse;
    }

    public void setPatientrefuse(boolean value) {
        this.patientrefuse = value;
    }

    public String getAutreobservation() {
        return this.autreobservation;
    }

    public void setAutreobservation(String value) {
        this.autreobservation = value;
    }

    public long getOnlineid() {
        return this.onlineid;
    }

    public void setOnlineid(long value) {
        this.onlineid = value;
    }

    public boolean isSynchronised() {
        return this.synchronised;
    }

    public void setSynchronised(boolean value) {
        this.synchronised = value;
    }

    public long getIdpatient() {
        return this.idpatient;
    }

    public void setIdpatient(long value) {
        this.idpatient = value;
    }

    public long getIdobservationvisite() {
        return this.idobservationvisite;
    }

    public void setIdobservationvisite(long value) {
        this.idobservationvisite = value;
    }

    public long getIdraisonrattement() {
        return this.idraisonrattement;
    }

    public void setIdraisonrattement(long value) {
        this.idraisonrattement = value;
    }

    public boolean isOubli() {
        return this.oubli;
    }

    public void setOubli(boolean value) {
        this.oubli = value;
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

    public boolean isSoinsalternatifs() {
        return this.soinsalternatifs;
    }

    public void setSoinsalternatifs(boolean value) {
        this.soinsalternatifs = value;
    }

    public boolean isPeurdetrevuaucentre() {
        return this.peurdetrevuaucentre;
    }

    public void setPeurdetrevuaucentre(boolean value) {
        this.peurdetrevuaucentre = value;
    }

    public boolean isServicesinsatisfaisants() {
        return this.servicesinsatisfaisants;
    }

    public void setServicesinsatisfaisants(boolean value) {
        this.servicesinsatisfaisants = value;
    }

    public String getAutreraison() {
        return this.autreraison;
    }

    public void setAutreraison(String value) {
        this.autreraison = value;
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

    public long getIdsensibilisation() {
        return this.idsensibilisation;
    }

    public void setIdsensibilisation(long value) {
        this.idsensibilisation = value;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean value) {
        this.done = value;
    }

    public boolean isSeen() {
        return this.seen;
    }

    public void setSeen(boolean value) {
        this.seen = value;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public static String getScript() {
        return ((((((((((((((((((((((((((((((((((((((((((("CREATE TABLE Suivi(" + "id INTEGER PRIMARY KEY AUTOINCREMENT ,") + "datesuvi TEXT ,") + "accouchementenmilieuhospitalier BOOL ,") + "dateaccouchement TEXT ,") + "bbexposeenvie BOOL ,") + "bbainitieprophylaxie BOOL ,") + "bbabeneficiepcr BOOL ,") + "bbsuiviailleurs BOOL ,") + "datebbsuiviailleurs TEXT ,") + "motifbbsuiviailleurs TEXT ,") + "centrereceveurbb TEXT ,") + "patientretourne BOOL ,") + "raisonrefusnonretour TEXT ,") + "patientsuiviailleurs BOOL ,") + "datepatientsuiviailleurs TEXT ,") + "motifpatientsuiviailleurs TEXT ,") + "centrereceveurpatient TEXT ,") + "patientdecede BOOL ,") + "datedecespatient TEXT ,") + "decespatientrapportepar TEXT ,") + "patientnonretrouve BOOL ,") + "dateretourpatient TEXT ,") + "patientrefuse BOOL ,") + "autreobservation TEXT ,") + "onlineid INTEGER ,") + "synchronised BOOL ,") + "idpatient INTEGER ,") + "idobservationvisite INTEGER ,") + "idraisonvisite INTEGER ,") + "idraisonrattement INTEGER ,") + "oubli BOOL ,") + "fraistransport BOOL ,") + "tropmalade BOOL ,") + "soinsalternatifs BOOL ,") + "peurdetrevuaucentre BOOL ,") + "servicesinsatisfaisants BOOL ,") + "autreraison TEXT ,") + "longitude REAL ,") + "latitude REAL ,") + "idsensibilisation INTEGER ,") + "done BOOL ,") + "seen BOOL ,") + "dateenregistrement TEXT ,") + "iduser INTEGER);";
    }

    public static long Insert(Context c, Suivi obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("datesuvi", obj.getDatesuvi().replace(" 12:00:00 AM", ""));
        value.put("accouchementenmilieuhospitalier", Boolean.valueOf(obj.isAccouchementenmilieuhospitalier()));
        value.put("dateaccouchement", obj.getDateaccouchement());
        value.put("bbexposeenvie", Boolean.valueOf(obj.isBbexposeenvie()));
        value.put("bbainitieprophylaxie", Boolean.valueOf(obj.isBbainitieprophylaxie()));
        value.put("bbabeneficiepcr", Boolean.valueOf(obj.isBbabeneficiepcr()));
        value.put("bbsuiviailleurs", Boolean.valueOf(obj.isBbsuiviailleurs()));
        value.put("datebbsuiviailleurs", obj.getDatebbsuiviailleurs());
        value.put("motifbbsuiviailleurs", obj.getMotifbbsuiviailleurs());
        value.put("centrereceveurbb", obj.getCentrereceveurbb());
        value.put("patientretourne", Boolean.valueOf(obj.isPatientretourne()));
        value.put("raisonrefusnonretour", obj.getRaisonrefusnonretour());
        value.put("patientsuiviailleurs", Boolean.valueOf(obj.isPatientsuiviailleurs()));
        value.put("datepatientsuiviailleurs", obj.getDatepatientsuiviailleurs());
        value.put("motifpatientsuiviailleurs", obj.getMotifpatientsuiviailleurs());
        value.put("centrereceveurpatient", obj.getCentrereceveurpatient());
        value.put("patientdecede", Boolean.valueOf(obj.isPatientdecede()));
        value.put("datedecespatient", obj.getDatedecespatient());
        value.put("decespatientrapportepar", obj.getDecespatientrapportepar());
        value.put("patientnonretrouve", Boolean.valueOf(obj.isPatientnonretrouve()));
        value.put("dateretourpatient", obj.getDateretourpatient());
        value.put("patientrefuse", Boolean.valueOf(obj.isPatientrefuse()));
        value.put("autreobservation", obj.getAutreobservation());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        value.put("synchronised", Boolean.valueOf(obj.isSynchronised()));
        value.put("idpatient", Long.valueOf(obj.getIdpatient()));
        value.put("idobservationvisite", Long.valueOf(obj.getIdobservationvisite()));
        value.put("idraisonrattement", Long.valueOf(obj.getIdraisonrattement()));
        value.put("oubli", Boolean.valueOf(obj.isOubli()));
        value.put("fraistransport", Boolean.valueOf(obj.isFraistransport()));
        value.put("tropmalade", Boolean.valueOf(obj.isTropmalade()));
        value.put("soinsalternatifs", Boolean.valueOf(obj.isSoinsalternatifs()));
        value.put("peurdetrevuaucentre", Boolean.valueOf(obj.isPeurdetrevuaucentre()));
        value.put("servicesinsatisfaisants", Boolean.valueOf(obj.isServicesinsatisfaisants()));
        value.put("autreraison", obj.getAutreraison());
        value.put("longitude", Double.valueOf(obj.getLongitude()));
        value.put("latitude", Double.valueOf(obj.getLatitude()));
        value.put("idsensibilisation", Long.valueOf(obj.getIdsensibilisation()));
        value.put("done", Boolean.valueOf(obj.isDone()));
        value.put("seen", Boolean.valueOf(obj.isSeen()));
        value.put("iduser", Long.valueOf(obj.getIduser()));
        db.getBdd().insert("Suivi", null, value);
        Cursor cur = db.getBdd().rawQuery("select max(id) from Suivi", null);
        long l = 0;
        if (cur.moveToNext()) {
            l = cur.getLong(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static void Update(Context c, Suivi obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("id", Long.valueOf(obj.getId()));
        value.put("datesuvi", obj.getDatesuvi().replace("12:00:00 AM", "").replace(" ", ""));
        value.put("accouchementenmilieuhospitalier", Boolean.valueOf(obj.isAccouchementenmilieuhospitalier()));
        value.put("dateaccouchement", obj.getDateaccouchement());
        value.put("bbexposeenvie", Boolean.valueOf(obj.isBbexposeenvie()));
        value.put("bbainitieprophylaxie", Boolean.valueOf(obj.isBbainitieprophylaxie()));
        value.put("bbabeneficiepcr", Boolean.valueOf(obj.isBbabeneficiepcr()));
        value.put("bbsuiviailleurs", Boolean.valueOf(obj.isBbsuiviailleurs()));
        value.put("datebbsuiviailleurs", obj.getDatebbsuiviailleurs());
        value.put("motifbbsuiviailleurs", obj.getMotifbbsuiviailleurs());
        value.put("centrereceveurbb", obj.getCentrereceveurbb());
        value.put("patientretourne", Boolean.valueOf(obj.isPatientretourne()));
        value.put("raisonrefusnonretour", obj.getRaisonrefusnonretour());
        value.put("patientsuiviailleurs", Boolean.valueOf(obj.isPatientsuiviailleurs()));
        value.put("datepatientsuiviailleurs", obj.getDatepatientsuiviailleurs());
        value.put("motifpatientsuiviailleurs", obj.getMotifpatientsuiviailleurs());
        value.put("centrereceveurpatient", obj.getCentrereceveurpatient());
        value.put("patientdecede", Boolean.valueOf(obj.isPatientdecede()));
        value.put("datedecespatient", obj.getDatedecespatient());
        value.put("decespatientrapportepar", obj.getDecespatientrapportepar());
        value.put("patientnonretrouve", Boolean.valueOf(obj.isPatientnonretrouve()));
        value.put("dateretourpatient", obj.getDateretourpatient());
        value.put("patientrefuse", Boolean.valueOf(obj.isPatientrefuse()));
        value.put("autreobservation", obj.getAutreobservation());
        value.put("onlineid", Long.valueOf(obj.getOnlineid()));
        value.put("synchronised", Boolean.valueOf(false));
        value.put("idpatient", Long.valueOf(obj.getIdpatient()));
        value.put("idobservationvisite", Long.valueOf(obj.getIdobservationvisite()));
        value.put("idraisonrattement", Long.valueOf(obj.getIdraisonrattement()));
        value.put("oubli", Boolean.valueOf(obj.isOubli()));
        value.put("fraistransport", Boolean.valueOf(obj.isFraistransport()));
        value.put("tropmalade", Boolean.valueOf(obj.isTropmalade()));
        value.put("soinsalternatifs", Boolean.valueOf(obj.isSoinsalternatifs()));
        value.put("peurdetrevuaucentre", Boolean.valueOf(obj.isPeurdetrevuaucentre()));
        value.put("servicesinsatisfaisants", Boolean.valueOf(obj.isServicesinsatisfaisants()));
        value.put("autreraison", obj.getAutreraison());
        value.put("longitude", Double.valueOf(obj.getLongitude()));
        value.put("latitude", Double.valueOf(obj.getLatitude()));
        value.put("idsensibilisation", Long.valueOf(obj.getIdsensibilisation()));
        value.put("done", Boolean.valueOf(true));
        value.put("dateenregistrement", obj.getDateenregistrement());
        value.put("seen", Boolean.valueOf(obj.isSeen()));
        db.getBdd().update("Suivi", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void Delete(Context c, String colonne, String value) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("Suivi", colonne + " = ?", new String[]{String.valueOf(value)});
        db.close();
    }

    public static void DeleteByAgent(Context c, Long idu) {
        Database db = new Database(c);
        db.open();
        db.getBdd().delete("Suivi", "synchronised=1 and iduser = ?", new String[]{String.valueOf(idu)});
        db.close();
    }

    public static String list_id(Context c, long idu) {
        String query = ("select " + "onlineid") + " from Suivi where iduser = " + idu + " and synchronised = 0";
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

    public static List<Suivi> SelectAll(Context c, long idu) {
        String query = ((((((((((((((((((((((((((((((((((((((((("select " + "id, ") + "datesuvi, ") + "accouchementenmilieuhospitalier, ") + "dateaccouchement, ") + "bbexposeenvie, ") + "bbainitieprophylaxie, ") + "bbabeneficiepcr, ") + "bbsuiviailleurs, ") + "datebbsuiviailleurs, ") + "motifbbsuiviailleurs, ") + "centrereceveurbb, ") + "patientretourne, ") + "raisonrefusnonretour, ") + "patientsuiviailleurs, ") + "datepatientsuiviailleurs, ") + "motifpatientsuiviailleurs, ") + "centrereceveurpatient, ") + "patientdecede, ") + "datedecespatient, ") + "decespatientrapportepar, ") + "patientnonretrouve, ") + "dateretourpatient, ") + "patientrefuse, ") + "autreobservation, ") + "onlineid, ") + "synchronised, ") + "idpatient, ") + "idraisonvisite, ") + "idobservationvisite, ") + "idraisonrattement, ") + "oubli, ") + "fraistransport, ") + "tropmalade, ") + "soinsalternatifs, ") + "peurdetrevuaucentre, ") + "servicesinsatisfaisants, ") + "autreraison, ") + "longitude, ") + "latitude, ") + "idsensibilisation, ") + "done, ") + "seen, dateenregistrement from Suivi where iduser =" + idu;
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Suivi> liste = new ArrayList();
        while (cur.moveToNext()) {
            boolean z;
            Suivi obj = new Suivi();
            obj.setId(cur.getLong(0));
            obj.setDatesuvi(cur.getString(1));
            obj.setAccouchementenmilieuhospitalier(cur.getInt(2) == 1);
            obj.setDateaccouchement(cur.getString(3));
            if (cur.getInt(4) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbexposeenvie(z);
            if (cur.getInt(5) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbainitieprophylaxie(z);
            if (cur.getInt(6) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbabeneficiepcr(z);
            if (cur.getInt(7) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbsuiviailleurs(z);
            obj.setDatebbsuiviailleurs(cur.getString(8));
            obj.setMotifbbsuiviailleurs(cur.getString(9));
            obj.setCentrereceveurbb(cur.getString(10));
            if (cur.getInt(11) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientretourne(z);
            obj.setRaisonrefusnonretour(cur.getString(12));
            if (cur.getInt(13) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientsuiviailleurs(z);
            obj.setDatepatientsuiviailleurs(cur.getString(14));
            obj.setMotifpatientsuiviailleurs(cur.getString(15));
            obj.setCentrereceveurpatient(cur.getString(16));
            if (cur.getInt(17) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientdecede(z);
            obj.setDatedecespatient(cur.getString(18));
            obj.setDecespatientrapportepar(cur.getString(19));
            if (cur.getInt(20) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientnonretrouve(z);
            obj.setDateretourpatient(cur.getString(21));
            if (cur.getInt(22) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientrefuse(z);
            obj.setAutreobservation(cur.getString(23));
            obj.setOnlineid(cur.getLong(24));
            if (cur.getInt(25) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSynchronised(z);
            obj.setIdpatient(cur.getLong(26));
            obj.setIdobservationvisite(cur.getLong(28));
            obj.setIdraisonrattement(cur.getLong(29));
            if (cur.getInt(30) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setOubli(z);
            if (cur.getInt(31) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setFraistransport(z);
            if (cur.getInt(32) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setTropmalade(z);
            if (cur.getInt(33) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSoinsalternatifs(z);
            if (cur.getInt(34) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPeurdetrevuaucentre(z);
            if (cur.getInt(35) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setServicesinsatisfaisants(z);
            obj.setAutreraison(cur.getString(36));
            obj.setLongitude(cur.getDouble(37));
            obj.setLatitude(cur.getDouble(38));
            obj.setIdsensibilisation(cur.getLong(39));
            if (cur.getInt(40) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setDone(z);
            if (cur.getInt(41) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSeen(z);
            obj.setDateenregistrement(cur.getString(42));
            obj.loadRaisonVisite(c);
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static Suivi SelectById(Context c, long id) {
        boolean z = true;
        String query = ((((((((((((((((((((((((((((((((((((((((("select " + "id, ") + "datesuvi, ") + "accouchementenmilieuhospitalier, ") + "dateaccouchement, ") + "bbexposeenvie, ") + "bbainitieprophylaxie, ") + "bbabeneficiepcr, ") + "bbsuiviailleurs, ") + "datebbsuiviailleurs, ") + "motifbbsuiviailleurs, ") + "centrereceveurbb, ") + "patientretourne, ") + "raisonrefusnonretour, ") + "patientsuiviailleurs, ") + "datepatientsuiviailleurs, ") + "motifpatientsuiviailleurs, ") + "centrereceveurpatient, ") + "patientdecede, ") + "datedecespatient, ") + "decespatientrapportepar, ") + "patientnonretrouve, ") + "dateretourpatient, ") + "patientrefuse, ") + "autreobservation, ") + "onlineid, ") + "synchronised, ") + "idpatient, ") + "idraisonvisite, ") + "idobservationvisite, ") + "idraisonrattement, ") + "oubli, ") + "fraistransport, ") + "tropmalade, ") + "soinsalternatifs, ") + "peurdetrevuaucentre, ") + "servicesinsatisfaisants, ") + "autreraison, ") + "longitude, ") + "latitude, ") + "idsensibilisation, ") + "done, ") + "seen,dateenregistrement from Suivi where id = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{id + ""});
        if (cur.moveToNext()) {
            boolean z2;
            Suivi obj = new Suivi();
            obj.setId(cur.getLong(0));
            obj.setDatesuvi(cur.getString(1));
            if (cur.getInt(2) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setAccouchementenmilieuhospitalier(z2);
            obj.setDateaccouchement(cur.getString(3));
            if (cur.getInt(4) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setBbexposeenvie(z2);
            if (cur.getInt(5) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setBbainitieprophylaxie(z2);
            if (cur.getInt(6) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setBbabeneficiepcr(z2);
            if (cur.getInt(7) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setBbsuiviailleurs(z2);
            obj.setDatebbsuiviailleurs(cur.getString(8));
            obj.setMotifbbsuiviailleurs(cur.getString(9));
            obj.setCentrereceveurbb(cur.getString(10));
            if (cur.getInt(11) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPatientretourne(z2);
            obj.setRaisonrefusnonretour(cur.getString(12));
            if (cur.getInt(13) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPatientsuiviailleurs(z2);
            obj.setDatepatientsuiviailleurs(cur.getString(14));
            obj.setMotifpatientsuiviailleurs(cur.getString(15));
            obj.setCentrereceveurpatient(cur.getString(16));
            if (cur.getInt(17) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPatientdecede(z2);
            obj.setDatedecespatient(cur.getString(18));
            obj.setDecespatientrapportepar(cur.getString(19));
            if (cur.getInt(20) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPatientnonretrouve(z2);
            obj.setDateretourpatient(cur.getString(21));
            if (cur.getInt(22) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPatientrefuse(z2);
            obj.setAutreobservation(cur.getString(23));
            obj.setOnlineid(cur.getLong(24));
            if (cur.getInt(25) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setSynchronised(z2);
            obj.setIdpatient(cur.getLong(26));
            obj.setIdobservationvisite(cur.getLong(28));
            obj.setIdraisonrattement(cur.getLong(29));
            if (cur.getInt(30) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setOubli(z2);
            if (cur.getInt(31) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setFraistransport(z2);
            if (cur.getInt(32) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setTropmalade(z2);
            if (cur.getInt(33) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setSoinsalternatifs(z2);
            if (cur.getInt(34) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setPeurdetrevuaucentre(z2);
            if (cur.getInt(35) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setServicesinsatisfaisants(z2);
            obj.setAutreraison(cur.getString(36));
            obj.setLongitude(cur.getDouble(37));
            obj.setLatitude(cur.getDouble(38));
            obj.setIdsensibilisation(cur.getLong(39));
            if (cur.getInt(40) == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            obj.setDone(z2);
            if (cur.getInt(41) != 1) {
                z = false;
            }
            obj.setSeen(z);
            obj.setDateenregistrement(cur.getString(42));
            obj.loadRaisonVisite(c);
            return obj;
        }
        cur.close();
        db.close();
        return null;
    }

    public static List<Suivi> SelectByColumn(Context c, String colonne, String valeur, long idu) {
        String query = ((((((((((((((((((((((((((((((((((((((((("select " + "id, ") + "datesuvi, ") + "accouchementenmilieuhospitalier, ") + "dateaccouchement, ") + "bbexposeenvie, ") + "bbainitieprophylaxie, ") + "bbabeneficiepcr, ") + "bbsuiviailleurs, ") + "datebbsuiviailleurs, ") + "motifbbsuiviailleurs, ") + "centrereceveurbb, ") + "patientretourne, ") + "raisonrefusnonretour, ") + "patientsuiviailleurs, ") + "datepatientsuiviailleurs, ") + "motifpatientsuiviailleurs, ") + "centrereceveurpatient, ") + "patientdecede, ") + "datedecespatient, ") + "decespatientrapportepar, ") + "patientnonretrouve, ") + "dateretourpatient, ") + "patientrefuse, ") + "autreobservation, ") + "onlineid, ") + "synchronised, ") + "idpatient, ") + "idraisonvisite, ") + "idobservationvisite, ") + "idraisonrattement, ") + "oubli, ") + "fraistransport, ") + "tropmalade, ") + "soinsalternatifs, ") + "peurdetrevuaucentre, ") + "servicesinsatisfaisants, ") + "autreraison, ") + "longitude, ") + "latitude, ") + "idsensibilisation, ") + "done, ") + "seen,dateenregistrement from Suivi where iduser =" + idu + " and " + colonne + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{valeur});
        List<Suivi> liste = new ArrayList();
        while (cur.moveToNext()) {
            boolean z;
            Suivi obj = new Suivi();
            obj.setId(cur.getLong(0));
            obj.setDatesuvi(cur.getString(1));
            obj.setAccouchementenmilieuhospitalier(cur.getInt(2) == 1);
            obj.setDateaccouchement(cur.getString(3));
            if (cur.getInt(4) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbexposeenvie(z);
            if (cur.getInt(5) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbainitieprophylaxie(z);
            if (cur.getInt(6) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbabeneficiepcr(z);
            if (cur.getInt(7) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbsuiviailleurs(z);
            obj.setDatebbsuiviailleurs(cur.getString(8));
            obj.setMotifbbsuiviailleurs(cur.getString(9));
            obj.setCentrereceveurbb(cur.getString(10));
            if (cur.getInt(11) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientretourne(z);
            obj.setRaisonrefusnonretour(cur.getString(12));
            if (cur.getInt(13) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientsuiviailleurs(z);
            obj.setDatepatientsuiviailleurs(cur.getString(14));
            obj.setMotifpatientsuiviailleurs(cur.getString(15));
            obj.setCentrereceveurpatient(cur.getString(16));
            if (cur.getInt(17) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientdecede(z);
            obj.setDatedecespatient(cur.getString(18));
            obj.setDecespatientrapportepar(cur.getString(19));
            if (cur.getInt(20) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientnonretrouve(z);
            obj.setDateretourpatient(cur.getString(21));
            if (cur.getInt(22) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientrefuse(z);
            obj.setAutreobservation(cur.getString(23));
            obj.setOnlineid(cur.getLong(24));
            if (cur.getInt(25) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSynchronised(z);
            obj.setIdpatient(cur.getLong(26));
            obj.setIdobservationvisite(cur.getLong(28));
            if (cur.getInt(30) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setOubli(z);
            if (cur.getInt(31) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setFraistransport(z);
            if (cur.getInt(32) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setTropmalade(z);
            if (cur.getInt(33) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSoinsalternatifs(z);
            if (cur.getInt(34) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPeurdetrevuaucentre(z);
            if (cur.getInt(35) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setServicesinsatisfaisants(z);
            obj.setAutreraison(cur.getString(36));
            obj.setLongitude(cur.getDouble(37));
            obj.setLatitude(cur.getDouble(38));
            obj.setIdsensibilisation(cur.getLong(39));
            if (cur.getInt(40) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setDone(z);
            if (cur.getInt(41) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSeen(z);
            obj.setDateenregistrement(cur.getString(42));
            obj.loadRaisonVisite(c);
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static List<Suivi> SelectAllForSync(Context c) {
        String query = ((((((((((((((((((((((((((((((((((((((((("select " + "id, ") + "datesuvi, ") + "accouchementenmilieuhospitalier, ") + "dateaccouchement, ") + "bbexposeenvie, ") + "bbainitieprophylaxie, ") + "bbabeneficiepcr, ") + "bbsuiviailleurs, ") + "datebbsuiviailleurs, ") + "motifbbsuiviailleurs, ") + "centrereceveurbb, ") + "patientretourne, ") + "raisonrefusnonretour, ") + "patientsuiviailleurs, ") + "datepatientsuiviailleurs, ") + "motifpatientsuiviailleurs, ") + "centrereceveurpatient, ") + "patientdecede, ") + "datedecespatient, ") + "decespatientrapportepar, ") + "patientnonretrouve, ") + "dateretourpatient, ") + "patientrefuse, ") + "autreobservation, ") + "onlineid, ") + "synchronised, ") + "idpatient, ") + "idraisonvisite, ") + "idobservationvisite, ") + "idraisonrattement, ") + "oubli, ") + "fraistransport, ") + "tropmalade, ") + "soinsalternatifs, ") + "peurdetrevuaucentre, ") + "servicesinsatisfaisants, ") + "autreraison, ") + "longitude, ") + "latitude, ") + "idsensibilisation, ") + "done, ") + "seen,dateenregistrement from Suivi where  done = 1 and synchronised = 0";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, null);
        List<Suivi> liste = new ArrayList();
        while (cur.moveToNext()) {
            boolean z;
            Suivi obj = new Suivi();
            obj.setId(cur.getLong(0));
            obj.setDatesuvi(cur.getString(1));
            obj.setAccouchementenmilieuhospitalier(cur.getInt(2) == 1);
            obj.setDateaccouchement(cur.getString(3));
            if (cur.getInt(4) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbexposeenvie(z);
            if (cur.getInt(5) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbainitieprophylaxie(z);
            if (cur.getInt(6) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbabeneficiepcr(z);
            if (cur.getInt(7) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setBbsuiviailleurs(z);
            obj.setDatebbsuiviailleurs(cur.getString(8));
            obj.setMotifbbsuiviailleurs(cur.getString(9));
            obj.setCentrereceveurbb(cur.getString(10));
            if (cur.getInt(11) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientretourne(z);
            obj.setRaisonrefusnonretour(cur.getString(12));
            if (cur.getInt(13) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientsuiviailleurs(z);
            obj.setDatepatientsuiviailleurs(cur.getString(14));
            obj.setMotifpatientsuiviailleurs(cur.getString(15));
            obj.setCentrereceveurpatient(cur.getString(16));
            if (cur.getInt(17) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientdecede(z);
            obj.setDatedecespatient(cur.getString(18));
            obj.setDecespatientrapportepar(cur.getString(19));
            if (cur.getInt(20) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientnonretrouve(z);
            obj.setDateretourpatient(cur.getString(21));
            if (cur.getInt(22) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPatientrefuse(z);
            obj.setAutreobservation(cur.getString(23));
            obj.setOnlineid(cur.getLong(24));
            if (cur.getInt(25) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSynchronised(z);
            obj.setIdpatient(cur.getLong(26));
            obj.setIdobservationvisite(cur.getLong(28));
            if (cur.getInt(30) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setOubli(z);
            if (cur.getInt(31) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setFraistransport(z);
            if (cur.getInt(32) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setTropmalade(z);
            if (cur.getInt(33) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSoinsalternatifs(z);
            if (cur.getInt(34) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setPeurdetrevuaucentre(z);
            if (cur.getInt(35) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setServicesinsatisfaisants(z);
            obj.setAutreraison(cur.getString(36));
            obj.setLongitude(cur.getDouble(37));
            obj.setLatitude(cur.getDouble(38));
            obj.setIdsensibilisation(cur.getLong(39));
            if (cur.getInt(40) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setDone(z);
            if (cur.getInt(41) == 1) {
                z = true;
            } else {
                z = false;
            }
            obj.setSeen(z);
            obj.setDateenregistrement(cur.getString(42));
            obj.loadRaisonVisite(c);
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
        Cursor cur = db.getBdd().rawQuery("select count(*) from suivi where " + colonne + " = ?", new String[]{value});
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static List<Suivi> SelectBy2Columns(Context c, String col1, String val1, String col2, String val2, long idu) {
        String query = ((((((((((((((((((((((((((((((((((((((((("select " + "id, ") + "datesuvi, ") + "accouchementenmilieuhospitalier, ") + "dateaccouchement, ") + "bbexposeenvie, ") + "bbainitieprophylaxie, ") + "bbabeneficiepcr, ") + "bbsuiviailleurs, ") + "datebbsuiviailleurs, ") + "motifbbsuiviailleurs, ") + "centrereceveurbb, ") + "patientretourne, ") + "raisonrefusnonretour, ") + "patientsuiviailleurs, ") + "datepatientsuiviailleurs, ") + "motifpatientsuiviailleurs, ") + "centrereceveurpatient, ") + "patientdecede, ") + "datedecespatient, ") + "decespatientrapportepar, ") + "patientnonretrouve, ") + "dateretourpatient, ") + "patientrefuse, ") + "autreobservation, ") + "onlineid, ") + "synchronised, ") + "idpatient, ") + "idpatient, ") + "idobservationvisite, ") + "idraisonrattement, ") + "oubli, ") + "fraistransport, ") + "tropmalade, ") + "soinsalternatifs, ") + "peurdetrevuaucentre, ") + "servicesinsatisfaisants, ") + "autreraison, ") + "longitude, ") + "latitude, ") + "idsensibilisation, ") + "done, ") + "seen,dateenregistrement from Suivi where iduser=" + idu + " and " + col1 + " = ? and " + col2 + " = ?";
        Database db = new Database(c);
        db.open();
        Cursor cur = db.getBdd().rawQuery(query, new String[]{val1, val2});
        List<Suivi> liste = new ArrayList();
        while (cur.moveToNext()) {
            Suivi obj = new Suivi();
            obj.setId(cur.getLong(0));
            obj.setDatesuvi(cur.getString(1));
            obj.setAccouchementenmilieuhospitalier(cur.getInt(2) == 1);
            obj.setDateaccouchement(cur.getString(3));
            obj.setBbexposeenvie(cur.getInt(4) == 1);
            obj.setBbainitieprophylaxie(cur.getInt(5) == 1);
            obj.setBbabeneficiepcr(cur.getInt(6) == 1);
            obj.setBbsuiviailleurs(cur.getInt(7) == 1);
            obj.setDatebbsuiviailleurs(cur.getString(8));
            obj.setMotifbbsuiviailleurs(cur.getString(9));
            obj.setCentrereceveurbb(cur.getString(10));
            obj.setPatientretourne(cur.getInt(11) == 1);
            obj.setRaisonrefusnonretour(cur.getString(12));
            obj.setPatientsuiviailleurs(cur.getInt(13) == 1);
            obj.setDatepatientsuiviailleurs(cur.getString(14));
            obj.setMotifpatientsuiviailleurs(cur.getString(15));
            obj.setCentrereceveurpatient(cur.getString(16));
            obj.setPatientdecede(cur.getInt(17) == 1);
            obj.setDatedecespatient(cur.getString(18));
            obj.setDecespatientrapportepar(cur.getString(19));
            obj.setPatientnonretrouve(cur.getInt(20) == 1);
            obj.setDateretourpatient(cur.getString(21));
            obj.setPatientrefuse(cur.getInt(22) == 1);
            obj.setAutreobservation(cur.getString(23));
            obj.setOnlineid(cur.getLong(24));
            obj.setSynchronised(cur.getInt(25) == 1);
            obj.setIdpatient(cur.getLong(26));
            obj.setIdobservationvisite(cur.getLong(28));
            obj.setIdraisonrattement(cur.getLong(29));
            obj.setOubli(cur.getInt(30) == 1);
            obj.setFraistransport(cur.getInt(31) == 1);
            obj.setTropmalade(cur.getInt(32) == 1);
            obj.setSoinsalternatifs(cur.getInt(33) == 1);
            obj.setPeurdetrevuaucentre(cur.getInt(34) == 1);
            obj.setServicesinsatisfaisants(cur.getInt(35) == 1);
            obj.setAutreraison(cur.getString(36));
            obj.setLongitude(cur.getDouble(37));
            obj.setLatitude(cur.getDouble(38));
            obj.setIdsensibilisation(cur.getLong(39));
            obj.setDone(cur.getInt(40) == 1);
            obj.setSeen(cur.getInt(41) == 1);
            obj.setDateenregistrement(cur.getString(42));
            obj.loadRaisonVisite(c);
            liste.add(obj);
        }
        cur.close();
        db.close();
        return liste;
    }

    public static void Synchronise(Context c, Suivi obj) {
        ContentValues value = new ContentValues();
        Database db = new Database(c);
        db.open();
        value.put("synchronised", Boolean.valueOf(true));
        db.getBdd().update("Suivi", value, "id = ?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public String toString() {
        return this.datesuvi;
    }

    public int datecomp(String d1, String d2) {
        String[] dbdate = d1.split("/");
        Calendar c = Calendar.getInstance();
        int mYear = c.get(1);
        int mMonth = c.get(2) + 1;
        int mDay = c.get(5);
        int dbYear = Integer.parseInt(dbdate[2]);
        int dbMonth = Integer.parseInt(dbdate[0]);
        int dbDay = Integer.parseInt(dbdate[1]);
        if (dbYear == mYear && dbMonth == mMonth && dbDay == mDay) {
            return 0;
        }
        if (dbYear > mYear) {
            return 1;
        }
        if (dbYear == mYear && dbMonth > mMonth) {
            return 1;
        }
        if (dbYear == mYear && dbMonth == mMonth && dbDay > mDay) {
            return 1;
        }
        return -1;
    }

    public static int Count(Context c) {
        Database db = new Database(c);
        db.open();
        int l = 0;
        Cursor cur = db.getBdd().rawQuery("select count(*) from suivi", null);
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }

    public static int Count(Context c, String where_clause) {
        Database db = new Database(c);
        db.open();
        int l = 0;
        Cursor cur = db.getBdd().rawQuery("select count(*) from suivi " + where_clause, null);
        if (cur.moveToNext()) {
            l = cur.getInt(0);
        }
        cur.close();
        db.close();
        return l;
    }
}
