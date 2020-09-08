package ht.solutions.plr.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.Institution;
import ht.solutions.plr.entities.Patient;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class FindInstitutionsFacade extends Thread {
    private Context contexte;
    private List<Patient> objs;
    ProgressDialog progress;

    public FindInstitutionsFacade(Context c, ProgressDialog p) {
        this.contexte = c;
        this.progress = p;
        if (!isOnline()) {
            Toast.makeText(this.contexte, "Non connecté\n Verifiez votre connexion internet", Toast.LENGTH_SHORT).show();
        }
        start();
    }

    public void run() {
        Gson g = new Gson();
        if (isOnline()) {
            try {
                SoapObject request = new SoapObject("http://tempuri.org/", "loadAllInstitutions");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                new HttpTransportSE(Session.getUrl(this.contexte)).call("http://tempuri.org/loadAllInstitutions", envelope);
                String resp = ((SoapPrimitive) envelope.getResponse()).toString();
                if (resp.equals("failed")) {
                    this.progress.cancel();
                    Toast.makeText(this.contexte, "Mise a jour echoue! verifiez votre connection internet", Toast.LENGTH_LONG).show();
                    return;
                }
                Log.v("params :", resp);
                ListInstitutions tasks = (ListInstitutions) g.fromJson(resp, ListInstitutions.class);
                int lenght = tasks.getInstitutions().size();
                int i = 0;
                for (Institution inst : tasks.getInstitutions()) {
                    i++;
                    this.progress.setProgress((i * 100) / lenght);
                    if (((long) Institution.Count(this.contexte, "onlineid", "" + inst.getOnlineId())) == 0) {
                        Institution.Insert(this.contexte, inst);
                    }
                }
                this.progress.cancel();
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
