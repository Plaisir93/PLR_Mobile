package ht.solutions.plr.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ht.solutions.plr.R;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.ObservationVisite;
import ht.solutions.plr.util.ThreadService;
import ht.solutions.plr.webservices.FindUserFacade;
import ht.solutions.plr.MainActivity;
import ht.solutions.plr.entities.User;

import java.util.List;

public class FragmentLogin extends Fragment {
    public static final String TAG = "login";
    private Button btconnexion;
    private EditText password;
    ProgressDialog progress;
    private EditText pseudo;

    class C01621 implements OnClickListener {
        C01621() {
        }

        public void onClick(View v) {
            if (FragmentLogin.this.login() == 1) {
                Session.setFragment(FragmentListSuivi.TAG);
                Session.setMenuSelected(1);
                FragmentLogin.this.getActivity().startActivity(new Intent(FragmentLogin.this.getActivity(), MainActivity.class));
            }
            ThreadService threadService = new ThreadService(FragmentLogin.this.getActivity());
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        initComponents(v);
        setEvents();
        setHasOptionsMenu(true);
        return v;
    }

    private void initComponents(View v) {
        this.pseudo = (EditText) v.findViewById(R.id.et_pseudo);
        this.password = (EditText) v.findViewById(R.id.et_password);
        this.btconnexion = (Button) v.findViewById(R.id.connexion);
        User u = Session.getUser();
        if (u == null) {
            List<User> l = User.SelectByColumn(getActivity(), "connected", "1");
            if (l.size() > 0) {
                u = (User) l.get(0);
            }
        }
        if (u != null) {
            this.pseudo.setText(u.getPseudo());
        }
    }

    private void setEvents() {
        this.btconnexion.setOnClickListener(new C01621());
    }

    private int login() {
        User user0 = new User();
        user0.setPseudo(this.pseudo.getText().toString());
        user0.setPassword(this.password.getText().toString());
        User user1 = User.GetUser(getActivity(), user0.getPseudo(), user0.getPassword());
        if (user1 != null) {
            Session.setUser(user1);
            user1.setConnected(true);
            User.Update(getActivity(), user1);
            return 1;
        }
        this.progress = new ProgressDialog(getActivity());
        this.progress.setTitle("Connexion");
        this.progress.setMessage("Vérification de données saisies....");
        this.progress.show();
        FindUserFacade findUserFacade = new FindUserFacade(getActivity(), user0, this.btconnexion, this.progress);
        return 3;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    private void initDB() {
        if (ObservationVisite.Count(getActivity(), "id", "1") == 0) {
            ObservationVisite obs = new ObservationVisite();
            obs.setObservation("Autre");
            obs.setOnlineid(1);
            ObservationVisite.Insert(getActivity(), obs);
            obs = new ObservationVisite();
            obs.setObservation("Patient present et accepte de recevoir la visite");
            obs.setOnlineid(2);
            ObservationVisite.Insert(getActivity(), obs);
            obs = new ObservationVisite();
            obs.setObservation("Patient present mais refuse de recevoir la visite");
            obs.setOnlineid(3);
            ObservationVisite.Insert(getActivity(), obs);
            obs = new ObservationVisite();
            obs.setObservation("Patient absent mais l'adresse est correcte");
            obs.setOnlineid(4);
            ObservationVisite.Insert(getActivity(), obs);
            obs = new ObservationVisite();
            obs.setObservation("Patient absent, demenage");
            obs.setOnlineid(5);
            ObservationVisite.Insert(getActivity(), obs);
            obs = new ObservationVisite();
            obs.setObservation("Patient absent, fausse adresse");
            obs.setOnlineid(6);
            ObservationVisite.Insert(getActivity(), obs);
        }
    }
}
