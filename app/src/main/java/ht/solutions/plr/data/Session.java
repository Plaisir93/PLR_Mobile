package ht.solutions.plr.data;

import android.content.Context;

import ht.solutions.plr.entities.FingerPatient;
import ht.solutions.plr.entities.Patient;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.entities.User;

public class Session { 
	private static String fragment = "login";
	private static double chtupdatev3update = 3.0;
	private static Patient currentPatient;
	private static int menuSelected=1;
	private static User user;
	private static long lasttime=0;
	public static final String VERSION_APP = "3.4";
	private static String serverMessage="";
 	private static Suivi2 currentSuivi;
	private static FingerPatient currentFingerPatient;

	public static FingerPatient getCurrentFingerPatient() {
		return currentFingerPatient;
	}

	public static void setCurrentFingerPatient(FingerPatient currentFingerPatient) {
		Session.currentFingerPatient = currentFingerPatient;
	}

	public static String getServerMessage() {
		return serverMessage;
	}
	public static void setServerMessage(String serverMessage) {
		Session.serverMessage = serverMessage;
	}
    public static long getLasttime() {
		return lasttime;
	}
	public static void setLasttime(long lasttime) {
		Session.lasttime = lasttime;
	}
	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		Session.user = user;
	}
	public static Patient getCurrentPatient() {
		return currentPatient;
	}
	public static int getMenuSelected() {
		return menuSelected;
	}
	public static void setMenuSelected(int menuSelected) {
		Session.menuSelected = menuSelected;
	}
	public static void setCurrentPatient(Patient obj) {
		currentPatient = obj;
	}
  	public static Suivi2 getCurrentSuivi() {
		return currentSuivi;
	}
	public static void setCurrentSuivi(Suivi2 obj) {
		currentSuivi = obj;
	}
	public static String getFragment() {
		return fragment;
	}
	public static void setFragment(String fragment) {
		Session.fragment = fragment;
	}
	public static String getUrl(Context c) {
		return "https://surveillance.mesi.ht/cht_webservice/webservices.asmx";
	}
//    public static String getUrl(Context c) {
//       return "http://209.160.26.16/FingerMobilWebService/WebServices.asmx";
//		//return "http://209.61.231.45/CHT_WebserviceFingerPrint/WebServices.asmx";
//    }
 	public static String getUrlAPK() {
		return "https://surveillance.mesi.ht/cht/cht.apk";
	}
}
