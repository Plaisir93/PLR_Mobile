package ht.solutions.plr.webservices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.DeviceInformation;
import ht.solutions.plr.entities.Patient;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.util.SendNotification;
import ht.solutions.plr.entities.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class FindSuivisFacade extends Thread {
    private Context contexte;

    public FindSuivisFacade(Context c) {
        this.contexte = c;
        Session.setServerMessage("");
        start();
    }

    public void run() {
        Gson g = new Gson();
        if (isOnline()) {
            User user = null;
            try {
                List<User> ll = User.SelectByColumn(this.contexte, "connected", "1");
                if (ll.size() > 0) {
                    user = (User) ll.get(0);
                    Session.setUser(user);
                }
                if (user != null) {
                    long iduser = user.getId();
                    SoapObject soapObject = new SoapObject("http://tempuri.org/", "getVisites");
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = true;
                    PropertyInfo propertyInfo = new PropertyInfo();
                    propertyInfo.setName("pseudo");
                   // Log.v("pseudo", user.getPseudo() + " . " + user.getPassword());
                    propertyInfo.setValue(user.getPseudo());
                    propertyInfo.setType(String.class);
                    soapObject.addProperty(propertyInfo);
                    propertyInfo = new PropertyInfo();
                    propertyInfo.setName("imei");
                    DeviceInformation d = new DeviceInformation();
                    d.initialize(this.contexte);
                    Log.v("imei", d.getImei());
                    propertyInfo.setValue(d.getImei());
                    propertyInfo.setType(String.class);
                    soapObject.addProperty(propertyInfo);
                    propertyInfo = new PropertyInfo();
                    propertyInfo.setName("versionapp");
                    Log.v("versionapp", Session.VERSION_APP);
                    propertyInfo.setValue(Session.VERSION_APP);
                    propertyInfo.setType(String.class);
                    soapObject.addProperty(propertyInfo);
                    propertyInfo = new PropertyInfo();
                    propertyInfo.setName("list_id");
                    String ids = Suivi2.list_id(this.contexte, iduser);
                    propertyInfo.setValue(ids);
                    Log.v("ids", ids);
                    propertyInfo.setType(String.class);
                    soapObject.addProperty(propertyInfo);
                    propertyInfo = new PropertyInfo();
                    propertyInfo.setName("time");
                    long time = new Date().getTime();
                    propertyInfo.setValue(time + "");
                    Log.v("time", time + "");
                    propertyInfo.setType(String.class);
                    soapObject.addProperty(propertyInfo);
                    envelope.setOutputSoapObject(soapObject);
                    new HttpTransportSE(Session.getUrl(this.contexte)).call("http://tempuri.org/getVisites", envelope);
                    String resp = ((SoapPrimitive) envelope.getResponse()).toString();
                     if (!resp.equals("failed")) {
                        if (resp.equals("getDevice")) {
                            SendDeviceFacade sendDeviceFacade = new SendDeviceFacade(this.contexte);
                        } else if (resp.equals("mustUpdate")) {
                            Session.setServerMessage("L'application nécessite une mise à jour. Veuillez installer la derniere mise à jour");
                        } else if (resp.equals("badTime")) {
                            Session.setServerMessage("La date de l'appareil est incorrecte, veuillez changer la date");
                        } else if (resp.equals("unknownDevice")) {
                            Session.setServerMessage("L'appareil que vous détenez n'est pas enregistré dans le système.");
                        } else if (resp.startsWith("customMessage")) {
                            Session.setServerMessage(resp.replace("customMessage:", ""));
                        } else {
                            Log.v("data", resp);
                            for (Suivi2 sv : ((ListNewTasks) g.fromJson(resp, ListNewTasks.class)).getSuivis()) {
                               try{
                                   Patient pt = sv.getPatient();
                                   if (((long) Patient.Count(this.contexte, "onlineid", "" + pt.getOnlineid())) == 0) {
                                       Patient.Insert(this.contexte, pt);
                                   } else {
                                       Patient.Update(this.contexte, pt);
                                   }
                                   if (((long) Suivi2.Count(this.contexte, "onlineid", "" + sv.getOnlineid())) == 0) {
                                       sv.setIdpatient(pt.getOnlineid());
                                       sv.setIdagent(iduser);
                                       sv.setDone(false);
                                       sv.setSync(false);
                                       Suivi2.Insert(this.contexte, sv);
                                   }
                                   SendNotification.SendNotification(this.contexte, "Visite pour : " + pt.getPrenom() + " " + pt.getNom() + ", prévue pour le " + sv.getDateprevue(), (int) (sv.getOnlineid() % 1000000));

                               }catch (Exception xex)
                               {
                             //      SendNotification.SendNotification(this.contexte, "Une erreur s'est produite : " + xex.getMessage(), (int) (sv.getOnlineid() % 1000000));
                               }
                             }
                        }
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (XmlPullParserException e3) {
                e3.printStackTrace();
            }
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) contexte
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
