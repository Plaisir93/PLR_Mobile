package ht.solutions.plr.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import ht.solutions.plr.R;
import ht.solutions.plr.MainActivity;
import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.Patient;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.util.MessageDialog;

import java.sql.Date;

public class FragmentConfirm extends Fragment {
    private Button bt_cancel;
    private Button bt_save;
    private EditText et_adressedomiciliaire;
    private EditText et_adressepersonnecontact;
    private EditText et_personnecontact;
    private EditText et_telephone;
    private EditText et_telephonepersonnecontact;
    private TextView tv_agent;
    private TextView tv_datevisite;
    private TextView tv_institution;

    class C01481 implements OnClickListener {
        C01481() {
        }

        public void onClick(View v) {
            ((SuiviActivity) FragmentConfirm.this.getActivity()).save();
            String datainvalid = Session.getCurrentSuivi().DataSafetoSave();
            if (datainvalid.equals("")) {
                FragmentConfirm.this.save();
            } else {
                MessageDialog.message(FragmentConfirm.this.getActivity(), "Données non valides", datainvalid);
            }
        }
    }

    class C01492 implements OnClickListener {
        C01492() {
        }

        public void onClick(View v) {
            FragmentConfirm.this.cancel();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_suivi5, container, false);
        initComponents(v);
        setEvents();
        setData();
        return v;
    }

    private void initComponents(View v) {
        if (Session.getCurrentSuivi() == null) {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
            return;
        }
        this.tv_institution = (TextView) v.findViewById(R.id.tv_institution);
        this.tv_datevisite = (TextView) v.findViewById(R.id.tv_datevisite);
        this.tv_agent = (TextView) v.findViewById(R.id.tv_agent);
        this.et_telephone = (EditText) v.findViewById(R.id.et_telephone);
        this.et_adressedomiciliaire = (EditText) v.findViewById(R.id.et_adressedomiciliaire);
        this.et_personnecontact = (EditText) v.findViewById(R.id.et_personnecontact);
        this.et_adressepersonnecontact = (EditText) v.findViewById(R.id.et_adressepersonnecontact);
        this.et_telephonepersonnecontact = (EditText) v.findViewById(R.id.et_telephonepersonnecontact);
        this.bt_save = (Button) v.findViewById(R.id.bt_save);
        this.bt_cancel = (Button) v.findViewById(R.id.bt_cancel);
    }

    public void save() {
        try {
            Suivi2 obj = Session.getCurrentSuivi();
            Patient patient = Session.getCurrentPatient();
            if (patient == null) {
                patient = new Patient();
            }
            patient.setAdressedomicilaire(this.et_adressedomiciliaire.getText().toString());
            patient.setAdresseducontact(this.et_adressepersonnecontact.getText().toString());
            patient.setPersonnedecontact(this.et_personnecontact.getText().toString());
            patient.setTelephone(this.et_telephone.getText().toString());
            patient.setTelephoneducontact(this.et_telephonepersonnecontact.getText().toString());
            obj.setSync(false);
            if (obj.isGpsisforpatient()) {
                patient.setLongitude(obj.getLongitude());
                patient.setLatitude(obj.getLatitude());
            }
            Suivi2.Update(getActivity(), obj);
            Patient.Update(getActivity(), patient);
            Session.setFragment(FragmentListSuivi.TAG);
            getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        } catch (Exception e) {
        }
    }

    private void cancel() {
        Session.setCurrentPatient(null);
        Session.setCurrentSuivi(null);
        Session.setFragment(FragmentListSuivi.TAG);
        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
    }

    public void setData() {
        Patient patient = Session.getCurrentPatient();
        Suivi2 obj = Session.getCurrentSuivi();
        try {
            this.et_adressedomiciliaire.setText(patient.getAdressedomicilaire());
        } catch (Exception e) {
        }
        try {
            this.et_adressepersonnecontact.setText(patient.getAdresseducontact());
        } catch (Exception e2) {
        }
        try {
            this.et_personnecontact.setText(patient.getPersonnedecontact());
        } catch (Exception e3) {
        }
        try {
            this.et_telephone.setText(patient.getTelephone());
        } catch (Exception e4) {
        }
        try {
            this.et_telephonepersonnecontact.setText(patient.getTelephoneducontact());
        } catch (Exception e5) {
        }
        try {
            if (!obj.isDone()) {
                obj.setDone_on(new Date(new java.util.Date().getTime()).toString());
                obj.setDone_by(Session.getUser().getPseudo());
            }
            this.tv_datevisite.setText(obj.getDone_on());
            this.tv_agent.setText(obj.getDone_by());
        } catch (Exception e6) {
        }
    }

    private void setEvents() {
        this.bt_save.setOnClickListener(new C01481());
        this.bt_cancel.setOnClickListener(new C01492());
    }
}
