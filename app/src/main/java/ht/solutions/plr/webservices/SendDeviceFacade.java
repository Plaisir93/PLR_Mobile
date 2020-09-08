package ht.solutions.plr.webservices;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.DeviceInformation;
import ht.solutions.plr.MainActivity;
import ht.solutions.plr.entities.Suivi;
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

public class SendDeviceFacade extends Thread {
    private Context contexte;
    private List<Suivi> objs;

    public SendDeviceFacade(Context c) {
        this.contexte = c;
        start();
    }

    public void run() {
        Gson g = new Gson();
        if (isOnline()) {
            try {
                User user = Session.getUser();
                if (user == null) {
                    List<User> l = User.SelectByColumn(this.contexte, "connected", "1");
                    if (l.size() > 0) {
                        user = (User) l.get(0);
                        Session.setUser(user);
                    }
                }
                if (user == null) {
                    Session.setFragment(FragmentLogin.TAG);
                    this.contexte.startActivity(new Intent(this.contexte, MainActivity.class));
                    return;
                }
                SoapObject request = new SoapObject("http://tempuri.org/", "getDevice");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                PropertyInfo propertyInfo = new PropertyInfo();
                Log.v("pseudo", user.getPseudo());
                propertyInfo.setName("pseudo");
                propertyInfo.setValue(user.getPseudo());
                propertyInfo.setType(String.class);
                request.addProperty(propertyInfo);
                DeviceInformation d = new DeviceInformation();
                d.initialize(this.contexte);
                String s = String.valueOf(g.toJson(d));
                Log.v("device", s);
                propertyInfo = new PropertyInfo();
                propertyInfo.setName("device");
                propertyInfo.setValue(s);
                propertyInfo.setType(String.class);
                request.addProperty(propertyInfo);
                envelope.setOutputSoapObject(request);
                new HttpTransportSE(Session.getUrl(this.contexte)).call("http://tempuri.org/getDevice", envelope);
                Log.v("response", ((SoapPrimitive) envelope.getResponse()).toString());
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
