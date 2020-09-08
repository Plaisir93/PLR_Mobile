package ht.solutions.plr.webservices;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.views.FragmentPassword;
import ht.solutions.plr.MainActivity;
import ht.solutions.plr.entities.User;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class ChangePasswordFacade extends Thread {
    private Button connexion;
    private Context contexte;
    private String newpass;

    public ChangePasswordFacade(Context c, String newPass, Button bt) {
        this.contexte = c;
        this.connexion = bt;
        this.newpass = newPass;
        if (!isOnline()) {
            Toast.makeText(this.contexte, "Non connecté\n Verifiez votre connexion internet", Toast.LENGTH_SHORT).show();
        }
        start();
    }

    public void run() {
        if (isOnline()) {
            try {
                SoapObject request = new SoapObject("http://tempuri.org/", "changePassword");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                PropertyInfo propertyInfo = new PropertyInfo();
                propertyInfo.setName("pseudo");
                propertyInfo.setValue(Session.getUser().getPseudo());
                propertyInfo.setType(String.class);
                request.addProperty(propertyInfo);
                propertyInfo = new PropertyInfo();
                propertyInfo.setName(FragmentPassword.TAG);
                propertyInfo.setValue(this.newpass);
                propertyInfo.setType(String.class);
                request.addProperty(propertyInfo);
                envelope.setOutputSoapObject(request);
                new HttpTransportSE(Session.getUrl(this.contexte)).call("http://tempuri.org/changePassword", envelope);
                final String resp = ((SoapPrimitive) envelope.getResponse()).toString();
                Log.v("", resp);
                if (resp.equals("success")) {
                    User u = Session.getUser();
                    u.setPassword(this.newpass);
                    User.Update(this.contexte, u);
                    Session.setMenuSelected(1);
                    Session.setFragment("listsuivi");
                    this.contexte.startActivity(new Intent(this.contexte, MainActivity.class));
                    return;
                }
                this.connexion.post(new Runnable() {
                    public void run() {
                        Toast.makeText(ChangePasswordFacade.this.contexte, resp, Toast.LENGTH_LONG).show();
                    }
                });
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
