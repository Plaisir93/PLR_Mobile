package ht.solutions.plr.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;


import ht.solutions.plr.R;
import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.util.DatePickerFragment;
import ht.solutions.plr.MainActivity;

public class FragmentResultatRelance extends Fragment {
    private EditText et_datedeces;
    private EditText et_dateretour;
    private EditText et_datesuiviailleurs;
    private EditText et_decesrapportepar;
    private EditText et_lieusuiviailleurs;
    private EditText et_motifsuiviailleurs;
    private RadioButton rb_na;
    private RadioButton rb_patient_suiviailleurs;
    private RadioButton rb_patientaccepte;
    private RadioButton rb_patientdecede;
    private RadioButton rb_patientrefuse;

    class C01701 implements OnCheckedChangeListener {
        C01701() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                FragmentResultatRelance.this.rb_na.setChecked(false);
                FragmentResultatRelance.this.rb_patient_suiviailleurs.setChecked(false);
                FragmentResultatRelance.this.rb_patientrefuse.setChecked(false);
                FragmentResultatRelance.this.rb_patientdecede.setChecked(false);
                FragmentResultatRelance.this.et_datedeces.setEnabled(false);
                FragmentResultatRelance.this.et_dateretour.setEnabled(true);
                FragmentResultatRelance.this.et_datesuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_decesrapportepar.setEnabled(false);
                FragmentResultatRelance.this.et_lieusuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_motifsuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_datedeces.setText("");
                FragmentResultatRelance.this.et_datesuiviailleurs.setText("");
                FragmentResultatRelance.this.et_decesrapportepar.setText("");
                FragmentResultatRelance.this.et_lieusuiviailleurs.setText("");
                FragmentResultatRelance.this.et_motifsuiviailleurs.setText("");
            }
        }
    }

    class C01712 implements OnCheckedChangeListener {
        C01712() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                FragmentResultatRelance.this.rb_patientaccepte.setChecked(false);
                FragmentResultatRelance.this.rb_patient_suiviailleurs.setChecked(false);
                FragmentResultatRelance.this.rb_patientrefuse.setChecked(false);
                FragmentResultatRelance.this.rb_patientdecede.setChecked(false);
                FragmentResultatRelance.this.et_datedeces.setEnabled(false);
                FragmentResultatRelance.this.et_dateretour.setEnabled(false);
                FragmentResultatRelance.this.et_datesuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_decesrapportepar.setEnabled(false);
                FragmentResultatRelance.this.et_lieusuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_motifsuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_datedeces.setText("");
                FragmentResultatRelance.this.et_dateretour.setText("");
                FragmentResultatRelance.this.et_datesuiviailleurs.setText("");
                FragmentResultatRelance.this.et_decesrapportepar.setText("");
                FragmentResultatRelance.this.et_lieusuiviailleurs.setText("");
                FragmentResultatRelance.this.et_motifsuiviailleurs.setText("");
            }
        }
    }

    class C01723 implements OnCheckedChangeListener {
        C01723() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                FragmentResultatRelance.this.rb_patientaccepte.setChecked(false);
                FragmentResultatRelance.this.rb_na.setChecked(false);
                FragmentResultatRelance.this.rb_patientrefuse.setChecked(false);
                FragmentResultatRelance.this.rb_patientdecede.setChecked(false);
                FragmentResultatRelance.this.et_datedeces.setEnabled(false);
                FragmentResultatRelance.this.et_dateretour.setEnabled(false);
                FragmentResultatRelance.this.et_datesuiviailleurs.setEnabled(true);
                FragmentResultatRelance.this.et_decesrapportepar.setEnabled(false);
                FragmentResultatRelance.this.et_lieusuiviailleurs.setEnabled(true);
                FragmentResultatRelance.this.et_motifsuiviailleurs.setEnabled(true);
                FragmentResultatRelance.this.et_datedeces.setText("");
                FragmentResultatRelance.this.et_dateretour.setText("");
                FragmentResultatRelance.this.et_decesrapportepar.setText("");
            }
        }
    }

    class C01734 implements OnCheckedChangeListener {
        C01734() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                FragmentResultatRelance.this.rb_patientaccepte.setChecked(false);
                FragmentResultatRelance.this.rb_na.setChecked(false);
                FragmentResultatRelance.this.rb_patient_suiviailleurs.setChecked(false);
                FragmentResultatRelance.this.rb_patientdecede.setChecked(false);
                FragmentResultatRelance.this.et_datedeces.setEnabled(false);
                FragmentResultatRelance.this.et_dateretour.setEnabled(false);
                FragmentResultatRelance.this.et_datesuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_decesrapportepar.setEnabled(false);
                FragmentResultatRelance.this.et_lieusuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_motifsuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_datedeces.setText("");
                FragmentResultatRelance.this.et_dateretour.setText("");
                FragmentResultatRelance.this.et_datesuiviailleurs.setText("");
                FragmentResultatRelance.this.et_decesrapportepar.setText("");
                FragmentResultatRelance.this.et_lieusuiviailleurs.setText("");
                FragmentResultatRelance.this.et_motifsuiviailleurs.setText("");
            }
        }
    }

    class C01745 implements OnCheckedChangeListener {
        C01745() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                FragmentResultatRelance.this.rb_patientaccepte.setChecked(false);
                FragmentResultatRelance.this.rb_na.setChecked(false);
                FragmentResultatRelance.this.rb_patient_suiviailleurs.setChecked(false);
                FragmentResultatRelance.this.rb_patientrefuse.setChecked(false);
                FragmentResultatRelance.this.et_datedeces.setEnabled(true);
                FragmentResultatRelance.this.et_dateretour.setEnabled(false);
                FragmentResultatRelance.this.et_datesuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_decesrapportepar.setEnabled(true);
                FragmentResultatRelance.this.et_lieusuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_motifsuiviailleurs.setEnabled(false);
                FragmentResultatRelance.this.et_dateretour.setText("");
                FragmentResultatRelance.this.et_datesuiviailleurs.setText("");
                FragmentResultatRelance.this.et_lieusuiviailleurs.setText("");
                FragmentResultatRelance.this.et_motifsuiviailleurs.setText("");
            }
        }
    }

    class C01756 implements OnClickListener {
        C01756() {
        }

        public void onClick(View v) {
            FragmentResultatRelance.this.setDate(FragmentResultatRelance.this.et_datedeces);
        }
    }

    class C01767 implements OnClickListener {
        C01767() {
        }

        public void onClick(View v) {
            FragmentResultatRelance.this.setDate(FragmentResultatRelance.this.et_datesuiviailleurs);
        }
    }

    class C01778 implements OnClickListener {
        C01778() {
        }

        public void onClick(View v) {
            FragmentResultatRelance.this.setDate(FragmentResultatRelance.this.et_dateretour);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_suivi4, container, false);
        initComponents(v);
        hideComponents();
        setEvents();
        setData();
        return v;
    }

    private void setEvents() {
        this.rb_patientaccepte.setOnCheckedChangeListener(new C01701());
        this.rb_na.setOnCheckedChangeListener(new C01712());
        this.rb_patient_suiviailleurs.setOnCheckedChangeListener(new C01723());
        this.rb_patientrefuse.setOnCheckedChangeListener(new C01734());
        this.rb_patientdecede.setOnCheckedChangeListener(new C01745());
    }

    private void initComponents(View v) {
        if (Session.getCurrentSuivi() == null) {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
            return;
        }
        this.rb_na = (RadioButton) v.findViewById(R.id.rb_na);
        this.rb_patientaccepte = (RadioButton) v.findViewById(R.id.rb_patientaccepte);
        this.rb_patientrefuse = (RadioButton) v.findViewById(R.id.rb_patientrefuse);
        this.rb_patientdecede = (RadioButton) v.findViewById(R.id.rb_patientdecede);
        this.rb_patient_suiviailleurs = (RadioButton) v.findViewById(R.id.rb_patient_suiviailleurs);
        this.et_dateretour = (EditText) v.findViewById(R.id.et_dateretour);
        this.et_datesuiviailleurs = (EditText) v.findViewById(R.id.et_datesuiviailleurs);
        this.et_lieusuiviailleurs = (EditText) v.findViewById(R.id.et_lieusuiviailleurs);
        this.et_motifsuiviailleurs = (EditText) v.findViewById(R.id.et_motifsuiviailleurs);
        this.et_datedeces = (EditText) v.findViewById(R.id.et_datedeces);
        this.et_decesrapportepar = (EditText) v.findViewById(R.id.et_decesrapportepar);
        this.et_datedeces.setOnClickListener(new C01756());
        this.et_datesuiviailleurs.setOnClickListener(new C01767());
        this.et_dateretour.setOnClickListener(new C01778());
    }

    private void hideComponents() {
        if (Session.getCurrentSuivi().getRaisonvisite().toLowerCase().contains("distribution") || Session.getCurrentSuivi().getRaisonvisite().toLowerCase().contains("adresse")) {
            this.rb_patientaccepte.setEnabled(false);
            this.rb_patientrefuse.setEnabled(false);
            this.rb_patient_suiviailleurs.setEnabled(false);
        }
        if (Session.getCurrentSuivi().getRaisonvisite().toLowerCase().contains("rendez-vous")) {
            this.rb_patient_suiviailleurs.setEnabled(false);
        }
    }

    public void save() {
        Suivi2 obj = Session.getCurrentSuivi();
        obj.setPatientacceptederetourner(this.rb_patientaccepte.isChecked());
        obj.setPatientrefusederetourner(this.rb_patientrefuse.isChecked());
        obj.setPatientdecede(this.rb_patientdecede.isChecked());
        obj.setPatientensuiviailleurs(this.rb_patient_suiviailleurs.isChecked());
        obj.setDateretourpromise(this.et_dateretour.getText().toString());
        obj.setMotifsuiviailleurs(this.et_motifsuiviailleurs.getText().toString());
        obj.setCentresuiviailleurs(this.et_lieusuiviailleurs.getText().toString());
        obj.setData1(this.et_datesuiviailleurs.getText().toString());
        obj.setDecesrapportepar(this.et_decesrapportepar.getText().toString());
        obj.setDatedeces(this.et_datedeces.getText().toString());
    }

    public void setData() {
        Suivi2 obj = Session.getCurrentSuivi();
        if (obj.isDone()) {
            this.rb_patientaccepte.setChecked(obj.isPatientacceptederetourner());
            this.rb_patientrefuse.setChecked(obj.isPatientrefusederetourner());
            this.rb_patientdecede.setChecked(obj.isPatientdecede());
            this.rb_patient_suiviailleurs.setChecked(obj.isPatientensuiviailleurs());
            this.et_dateretour.setText(obj.getDateretourpromise());
            this.et_motifsuiviailleurs.setText(obj.getMotifsuiviailleurs());
            this.et_lieusuiviailleurs.setText(obj.getCentresuiviailleurs());
            this.et_datesuiviailleurs.setText(obj.getData1());
            this.et_decesrapportepar.setText(obj.getDecesrapportepar());
            this.et_datedeces.setText(obj.getDatedeces());
        }
    }

    public void setDate(EditText v) {
        new DatePickerFragment(v).show(getFragmentManager(), "datePicker");
    }
}
