package ht.solutions.plr.webservices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.DeviceInformation;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class DeviceInformationFacade extends Thread {
    private Context contexte;

    public DeviceInformationFacade(Context c) {
        this.contexte = c;
        start();
    }

    public void run() {
        if (isOnline()) {
            try {
                DeviceInformation di = new DeviceInformation();
                di.initialize(this.contexte);
                SoapObject request = new SoapObject("http://tempuri.org/", "SaveDeviceInfos");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                PropertyInfo propertyInfo = new PropertyInfo();
                propertyInfo.setName("deviceInfos");
                Gson gson = new Gson();
                Log.v("deviceInfos", gson.toJson(di));
                propertyInfo.setValue(gson.toJson(di));
                propertyInfo.setType(String.class);
                request.addProperty(propertyInfo);
                propertyInfo = new PropertyInfo();
                propertyInfo.setName("username");
                propertyInfo.setValue(Session.getUser().getPseudo());
                propertyInfo.setType(String.class);
                request.addProperty(propertyInfo);
                envelope.setOutputSoapObject(request);
                new HttpTransportSE(Session.getUrl(this.contexte)).call("http://tempuri.org/SaveDeviceInfos", envelope);
                String resp = ((SoapPrimitive) envelope.getResponse()).toString();
                if (!resp.equals("failed")) {
                    Session.setServerMessage(resp);
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
