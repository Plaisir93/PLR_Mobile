package ht.solutions.plr.webservices;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.FingerPatient;
import ht.solutions.plr.entities.Patient;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.views.FragmentListSuivi;
import ht.solutions.plr.views.FragmentPassword;
import ht.solutions.plr.MainActivity;
import ht.solutions.plr.entities.User;
import ht.solutions.plr.views.FragmentLogin;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class SuiviFacade extends Thread {
    private Context contexte;
    private List<Suivi2> objs;
    private Button sync;

    class C01921 implements Runnable {
        C01921() {
        }

        public void run() {
            Toast.makeText(SuiviFacade.this.contexte, "Rien a synchroniser", Toast.LENGTH_LONG).show();
        }
    }

    public SuiviFacade(Context c, List<Suivi2> objs, Button b) {
        this.contexte = c;
        this.objs = objs;
        this.sync = b;
        if (!isOnline()) {
            Toast.makeText(this.contexte, "Non connecté\n Verifiez votre connexion internet", Toast.LENGTH_LONG).show();
        }
        start();
    }

    @Override
    public void run() {
        String s = null;

        Gson g = new Gson();
        if (isOnline()) {
            if(objs.size() == 0)
            {
                this.sync.post(new Runnable(){
                    @Override
                    public void run() {
                        Toast.makeText(contexte,"Rien a synchroniser", Toast.LENGTH_SHORT).show();
                    }
                });

            }
            int i = 0;
            for (Suivi2 obj : this.objs) {
                try {
                    User user = Session.getUser();
                    if (user == null) {
                        List<User> l = User.SelectByColumn(contexte, "connected",
                                "1");
                        if (l.size() > 0) {
                            user = l.get(0);
                            Session.setUser(user);
                        }
                    }
                    if(user == null){
                        Session.setFragment("login");
                        Intent intent = new Intent(contexte, MainActivity.class);
                        contexte.startActivity(intent);
                        return;
                    }
                    try{
                        Patient pat = Patient.SelectByColumn(contexte, "onlineid", obj.getIdpatient()+"").get(0);
                        if(pat !=null){
                            List<FingerPatient> _list=FingerPatient.SelectByColumn(contexte,"refPatient",pat.getOnlineid());
                            pat.setFingerList(_list);
                            obj.setPatient(pat);
                        }
                    }catch(Exception e){}

                    SoapObject request = new SoapObject("http://tempuri.org/",
                            "SaveSuivi");
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                            SoapEnvelope.VER11);
                    envelope.dotNet = true;

                    PropertyInfo propertyInfo = new PropertyInfo();
                    propertyInfo.setName("pseudo");

                    Log.v("pseudo",user.getPseudo());
                    propertyInfo.setValue(user.getPseudo());
                    propertyInfo.setType(String.class);
                    request.addProperty(propertyInfo);
                    Log.v("password",user.getPassword());
                    propertyInfo = new PropertyInfo();
                    propertyInfo.setName("password");

                    propertyInfo.setValue(user.getPassword());
                    propertyInfo.setType(String.class);
                    request.addProperty(propertyInfo);
                    propertyInfo = new PropertyInfo();
                    propertyInfo.setName("suivistr");
                    s = String.valueOf(g.toJson(obj));
                    propertyInfo.setValue(s);
                    Log.v("suivi",s);
                    propertyInfo.setType(String.class);
                    request.addProperty(propertyInfo);
                    envelope.setOutputSoapObject(request);
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(Session.getUrl(contexte));
                    androidHttpTransport.call("http://tempuri.org/SaveSuivi",envelope);
                    SoapPrimitive response = null;
                    response = (SoapPrimitive) envelope.getResponse();
                    final String resp= response.toString();

                    if(resp.equals("success")) {
                        i++;
                        obj.setSync(true);
                        Suivi2.Synchronise(contexte, obj);
                        final int z = i;
                        this.sync.post(new Runnable(){
                            @Override
                            public void run() {
                                Toast.makeText(contexte,resp+" : "+z+" / "+objs.size(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
            Session.setFragment("listSuivi");
            Intent intent = new Intent(contexte, MainActivity.class);
            contexte.startActivity(intent);
        }
    }


    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) contexte
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
