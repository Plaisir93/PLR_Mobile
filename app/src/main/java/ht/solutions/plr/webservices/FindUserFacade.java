package ht.solutions.plr.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Button;
import android.widget.Toast;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.views.FragmentPassword;
import ht.solutions.plr.MainActivity;
import ht.solutions.plr.entities.User;
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

public class FindUserFacade extends Thread {
    private Button connexion;
    private Context contexte;
    ProgressDialog prog;
    private User user;

    class C01911 implements Runnable {
        C01911() {
        }

        public void run() {
            Toast.makeText(FindUserFacade.this.contexte, "Connexion echouée, Votre mot de passe ou psuedo est incorrect", 1).show();
        }
    }

    public FindUserFacade(Context c, User user, Button conn, ProgressDialog p) {
        this.contexte = c;
        this.user = user;
        this.connexion = conn;
        this.prog = p;
        if (!isOnline()) {
            Toast.makeText(this.contexte, "Non connecté\n Vérifiez votre connexion internet", 0).show();
        }
        start();
    }

    public void run() {
        Gson g = new Gson();
        if (isOnline()) {
            try {
                SoapObject request = new SoapObject("http://tempuri.org/", "findUser");
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                PropertyInfo propertyInfo = new PropertyInfo();
                propertyInfo.setName("pseudo");
                propertyInfo.setValue(this.user.getPseudo());
                propertyInfo.setType(String.class);
                request.addProperty(propertyInfo);
                propertyInfo = new PropertyInfo();
                propertyInfo.setName(FragmentPassword.TAG);
                propertyInfo.setValue(this.user.getPassword());
                propertyInfo.setType(String.class);
                request.addProperty(propertyInfo);
                envelope.setOutputSoapObject(request);
                new HttpTransportSE(Session.getUrl(this.contexte)).call("http://tempuri.org/findUser", envelope);
                if (((SoapPrimitive) envelope.getResponse()).toString().equals("success")) {
                    User.setUserConnected(this.contexte, this.user, false);
                    if (((long) User.Count(this.contexte, "pseudo", this.user.getPseudo())) == 0) {
                        this.user.setConnected(true);
                        this.user.setId(User.Insert(this.contexte, this.user));
                    } else {
                        this.user.setId(((User) User.SelectByColumn(this.contexte, "pseudo", this.user.getPseudo()).get(0)).getId());
                        this.user.setPassword(this.user.getPassword());
                        this.user.setConnected(true);
                        User.Update(this.contexte, this.user);
                    }
                    Session.setUser(this.user);
                    Session.setFragment("listsuivi");
                    this.contexte.startActivity(new Intent(this.contexte, MainActivity.class));
                } else {
                    this.connexion.post(new C01911());
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (XmlPullParserException e3) {
                e3.printStackTrace();
            }
        }
        this.prog.cancel();
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) contexte
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }
}
