package ht.solutions.plr.webservices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.Patient;
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

public class PatientFacade extends Thread {
    private Context contexte;
    private List<Patient> objs;

    public PatientFacade(Context c, List<Patient> objs) {
        this.contexte = c;
        this.objs = objs;
        if (!isOnline()) {
            Toast.makeText(this.contexte, "Non connecté\n Verifiez votre connexion internet", 0).show();
        }
        start();
    }

    public void run() {
        Gson g = new Gson();
        if (isOnline()) {
            for (Object obj : this.objs) {
                try {
                    SoapObject request = new SoapObject("http://tempuri.org/", "Save");
                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = true;
                    PropertyInfo propertyInfo = new PropertyInfo();
                    propertyInfo.setName("dataToSync");
                    propertyInfo.setValue(String.valueOf(g.toJson(obj)));
                    propertyInfo.setType(String.class);
                    request.addProperty(propertyInfo);
                    envelope.setOutputSoapObject(request);
                    new HttpTransportSE(Session.getUrl(this.contexte)).call("http://tempuri.org/Save", envelope);
                    String resp = ((SoapPrimitive) envelope.getResponse()).toString();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (XmlPullParserException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    private boolean isOnline() {
        NetworkInfo netInfo = ((ConnectivityManager) this.contexte.getSystemService("connectivity")).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
}
