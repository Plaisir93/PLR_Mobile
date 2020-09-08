package ht.solutions.plr.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;


import ht.solutions.plr.R;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.MainActivity;
import ht.solutions.plr.data.Session;

public class FragmentTrouvailleVisite extends Fragment {
    private CheckBox ch_autostigmatisation;
    private CheckBox ch_besoindetransfert;
    private CheckBox ch_decesdanslafamille;
    private CheckBox ch_effetssecondaires;
    private CheckBox ch_fraisnondisponible;
    private CheckBox ch_migration;
    private CheckBox ch_occupation;
    private CheckBox ch_oubli;
    private CheckBox ch_patientaratesesdoses;
    private CheckBox ch_patientboitarv;
    private CheckBox ch_patientseportebien;
    private CheckBox ch_recherchesoins;
    private CheckBox ch_refuserecevoirsoins;
    private CheckBox ch_servicesnonsatisfaisants;
    private CheckBox ch_stigmatisation;
    private CheckBox ch_tropmalade;
    private CheckBox ch_voyageinterurbain;
    private EditText et_remarque;
    private TableRow tr_autostigmatisation;
    private TableRow tr_besoindetransfert;
    private TableRow tr_decesdanslafamille;
    private TableRow tr_effetssecondaires;
    private TableRow tr_fraisnondisponible;
    private TableRow tr_migration;
    private TableRow tr_occupation;
    private TableRow tr_oubli;
    private TableRow tr_patientaratesesdoses;
    private TableRow tr_patientboitarv;
    private TableRow tr_patientseportebien;
    private TableRow tr_recherchesoins;
    private TableRow tr_refuserecevoirsoins;
    private TableRow tr_remarque;
    private TableRow tr_servicesnonsatisfaisants;
    private TableRow tr_stigmatisation;
    private TableRow tr_tropmalade;
    private TableRow tr_voyageinterurbain;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_suivi3, container, false);
        initComponents(v);
        hideComponents();
        setData();
        return v;
    }

    private void initComponents(View v) {
        if (Session.getCurrentSuivi() == null) {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
            return;
        }
        this.ch_fraisnondisponible = (CheckBox) v.findViewById(R.id.ch_fraisnondisponible);
        this.ch_oubli = (CheckBox) v.findViewById(R.id.ch_oubli);
        this.ch_tropmalade = (CheckBox) v.findViewById(R.id.ch_tropmalade);
        this.ch_servicesnonsatisfaisants = (CheckBox) v.findViewById(R.id.ch_servicesnonsatisfaisants);
        this.ch_refuserecevoirsoins = (CheckBox) v.findViewById(R.id.ch_refuserecevoirsoins);
        this.ch_decesdanslafamille = (CheckBox) v.findViewById(R.id.ch_decesdanslafamille);
        this.ch_besoindetransfert = (CheckBox) v.findViewById(R.id.ch_besoindetransfert);
        this.ch_voyageinterurbain = (CheckBox) v.findViewById(R.id.ch_voyageinterurbain);
        this.ch_migration = (CheckBox) v.findViewById(R.id.ch_migration);
        this.ch_stigmatisation = (CheckBox) v.findViewById(R.id.ch_stigmatisation);
        this.ch_autostigmatisation = (CheckBox) v.findViewById(R.id.ch_autostigmatisation);
        this.ch_occupation = (CheckBox) v.findViewById(R.id.ch_occupation);
        this.ch_patientboitarv = (CheckBox) v.findViewById(R.id.ch_patientboitarv);
        this.ch_patientseportebien = (CheckBox) v.findViewById(R.id.ch_patientseportebien);
        this.ch_effetssecondaires = (CheckBox) v.findViewById(R.id.ch_effetssecondaires);
        this.ch_patientaratesesdoses = (CheckBox) v.findViewById(R.id.ch_patientaratesesdoses);
        this.ch_recherchesoins = (CheckBox) v.findViewById(R.id.ch_recherchesoins);
        this.tr_recherchesoins = (TableRow) v.findViewById(R.id.tr_recherchesoins);
        this.tr_fraisnondisponible = (TableRow) v.findViewById(R.id.tr_fraisnondisponible);
        this.tr_oubli = (TableRow) v.findViewById(R.id.tr_oubli);
        this.tr_tropmalade = (TableRow) v.findViewById(R.id.tr_tropmalade);
        this.tr_servicesnonsatisfaisants = (TableRow) v.findViewById(R.id.tr_servicesnonsatisfaisants);
        this.tr_refuserecevoirsoins = (TableRow) v.findViewById(R.id.tr_refuserecevoirsoins);
        this.tr_decesdanslafamille = (TableRow) v.findViewById(R.id.tr_decesdanslafamille);
        this.tr_besoindetransfert = (TableRow) v.findViewById(R.id.tr_besoindetransfert);
        this.tr_voyageinterurbain = (TableRow) v.findViewById(R.id.tr_voyageinterurbain);
        this.tr_migration = (TableRow) v.findViewById(R.id.tr_migration);
        this.tr_stigmatisation = (TableRow) v.findViewById(R.id.tr_stigmatisation);
        this.tr_autostigmatisation = (TableRow) v.findViewById(R.id.tr_autostigmatisation);
        this.tr_occupation = (TableRow) v.findViewById(R.id.tr_occupation);
        this.tr_patientboitarv = (TableRow) v.findViewById(R.id.tr_patientboitarv);
        this.tr_patientseportebien = (TableRow) v.findViewById(R.id.tr_patientseportebien);
        this.tr_effetssecondaires = (TableRow) v.findViewById(R.id.tr_effetssecondaires);
        this.tr_patientaratesesdoses = (TableRow) v.findViewById(R.id.tr_patientaratesesdoses);
        this.tr_remarque = (TableRow) v.findViewById(R.id.tr_remarque);
        this.et_remarque = (EditText) v.findViewById(R.id.et_remarque);
    }

    private void hideComponents() {
        if (Session.getCurrentSuivi().getRaisonvisite().toLowerCase().contains("inactif")) {
            this.tr_patientboitarv.setVisibility(View.GONE);
            this.tr_patientseportebien.setVisibility(View.GONE);
            this.tr_effetssecondaires.setVisibility(View.GONE);
            this.tr_patientaratesesdoses.setVisibility(View.GONE);
            this.tr_oubli.setVisibility(View.GONE);
            this.tr_besoindetransfert.setVisibility(View.GONE);
        }
        if (Session.getCurrentSuivi().getRaisonvisite().toLowerCase().contains("rendez-vous")) {
            this.tr_recherchesoins.setVisibility(View.GONE);
            this.tr_patientboitarv.setVisibility(View.GONE);
            this.tr_patientseportebien.setVisibility(View.GONE);
            this.tr_effetssecondaires.setVisibility(View.GONE);
            this.tr_patientaratesesdoses.setVisibility(View.GONE);
            this.tr_voyageinterurbain.setVisibility(View.GONE);
            this.tr_migration.setVisibility(View.GONE);
            this.tr_autostigmatisation.setVisibility(View.GONE);
        }
        if (Session.getCurrentSuivi().getRaisonvisite().toLowerCase().contains("distribution")) {
            this.tr_recherchesoins.setVisibility(View.GONE);
            this.tr_fraisnondisponible.setVisibility(View.GONE);
            this.tr_oubli.setVisibility(View.GONE);
            this.tr_tropmalade.setVisibility(View.GONE);
            this.tr_servicesnonsatisfaisants.setVisibility(View.GONE);
            this.tr_refuserecevoirsoins.setVisibility(View.GONE);
            this.tr_decesdanslafamille.setVisibility(View.GONE);
            this.tr_besoindetransfert.setVisibility(View.GONE);
            this.tr_voyageinterurbain.setVisibility(View.GONE);
            this.tr_migration.setVisibility(View.GONE);
            this.tr_stigmatisation.setVisibility(View.GONE);
            this.tr_autostigmatisation.setVisibility(View.GONE);
            this.tr_occupation.setVisibility(View.GONE);
        }
    }

    public void setData() {
        Suivi2 obj = Session.getCurrentSuivi();
        if (obj.isDone()) {
            this.et_remarque.setText(obj.getAutretrouvaille());
            this.ch_fraisnondisponible.setChecked(obj.isFraistransport());
            this.ch_oubli.setChecked(obj.isOubli());
            this.ch_tropmalade.setChecked(obj.isTropmalade());
            this.ch_servicesnonsatisfaisants.setChecked(obj.isServicesinsatisfaits());
            this.ch_refuserecevoirsoins.setChecked(obj.isRefusderecevoirsoins());
            this.ch_decesdanslafamille.setChecked(obj.isDecesdanslafamille());
            this.ch_besoindetransfert.setChecked(obj.isBesoindetransfert());
            this.ch_voyageinterurbain.setChecked(obj.isVoyage());
            this.ch_migration.setChecked(obj.isMigration());
            this.ch_stigmatisation.setChecked(obj.isStigmatisation());
            this.ch_autostigmatisation.setChecked(obj.isPeurdetrevu());
            this.ch_occupation.setChecked(obj.isOccupation());
            this.ch_patientboitarv.setChecked(obj.isMedoc_boitsesarv());
            this.ch_patientseportebien.setChecked(obj.isMedoc_patientseportebien());
            this.ch_effetssecondaires.setChecked(obj.isMedoc_effetsecondaire());
            this.ch_patientaratesesdoses.setChecked(obj.isMedoc_ratesesdoses());
            this.ch_recherchesoins.setChecked(obj.isRecherchesoins());
        }
    }

    public void save() {
        Suivi2 obj = Session.getCurrentSuivi();
        obj.setAutretrouvaille(this.et_remarque.getText().toString());
        obj.setFraistransport(this.ch_fraisnondisponible.isChecked());
        obj.setOubli(this.ch_oubli.isChecked());
        obj.setTropmalade(this.ch_tropmalade.isChecked());
        obj.setServicesinsatisfaits(this.ch_servicesnonsatisfaisants.isChecked());
        obj.setRefusderecevoirsoins(this.ch_refuserecevoirsoins.isChecked());
        obj.setDecesdanslafamille(this.ch_decesdanslafamille.isChecked());
        obj.setBesoindetransfert(this.ch_besoindetransfert.isChecked());
        obj.setVoyage(this.ch_voyageinterurbain.isChecked());
        obj.setMigration(this.ch_migration.isChecked());
        obj.setStigmatisation(this.ch_stigmatisation.isChecked());
        obj.setPeurdetrevu(this.ch_autostigmatisation.isChecked());
        obj.setOccupation(this.ch_occupation.isChecked());
        obj.setMedoc_boitsesarv(this.ch_patientboitarv.isChecked());
        obj.setMedoc_patientseportebien(this.ch_patientseportebien.isChecked());
        obj.setMedoc_effetsecondaire(this.ch_effetssecondaires.isChecked());
        obj.setMedoc_ratesesdoses(this.ch_patientaratesesdoses.isChecked());
        obj.setRecherchesoins(this.ch_recherchesoins.isChecked());
    }
}
