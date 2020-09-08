package ht.solutions.plr.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.Patient;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.MainActivity;
import ht.solutions.plr.util.GPSTracker;

import ht.solutions.plr.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InitVisitFragment extends Fragment implements OnMapReadyCallback, ConnectionCallbacks, OnConnectionFailedListener {
    private Button bt_refreshgps;
    private CheckBox ch_gps_ok;
    private CheckBox ch_patient_recoit_medoc;
    GoogleMap gMap;
    GPSTracker gps;
    private Spinner sp_observationvisite;
    private TextView tv_adressepatient;
    private TextView tv_autremedicament;
    private TextView tv_dureeprovision;
    private TextView tv_gps;
    private TextView tv_help;
    private TextView tv_lieuvisite;
    private TextView tv_patient_a_visiter;
    private TextView tv_raison_visite;

    class C01871 implements OnClickListener {
        C01871() {
        }

        public void onClick(View v) {

        }
    }

    class C01882 implements OnClickListener {
        C01882() {
        }

        public void onClick(View v) {
            InitVisitFragment.this.loadGPS();
        }
    }

    class C01893 implements OnItemSelectedListener {
        C01893() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            Suivi2 obj = Session.getCurrentSuivi();
            if (position == 4 || position == 5) {
                InitVisitFragment.this.ch_gps_ok.setChecked(false);
                InitVisitFragment.this.ch_gps_ok.setEnabled(false);
                InitVisitFragment.this.ch_patient_recoit_medoc.setChecked(false);
                InitVisitFragment.this.ch_patient_recoit_medoc.setEnabled(false);
                return;
            }
            InitVisitFragment.this.ch_gps_ok.setChecked(obj.getLieuvisite().contains("domicile"));
            InitVisitFragment.this.ch_gps_ok.setEnabled(true);
            InitVisitFragment.this.ch_patient_recoit_medoc.setEnabled(true);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_suivi2, container, false);
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        initComponents(v);
        return v;
    }

    private void initComponents(View v) {
        if (Session.getCurrentSuivi() == null) {
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
            return;
        }
        this.tv_help = (TextView) v.findViewById(R.id.tv_help);
        this.tv_gps = (TextView) v.findViewById(R.id.tv_gps);
        this.tv_autremedicament = (TextView) v.findViewById(R.id.tv_autremedicament);
        this.tv_patient_a_visiter = (TextView) v.findViewById(R.id.tv_patient_a_visiter);
        this.tv_raison_visite = (TextView) v.findViewById(R.id.tv_raison_visite);
        this.tv_lieuvisite = (TextView) v.findViewById(R.id.tv_lieuvisite);
        this.tv_dureeprovision = (TextView) v.findViewById(R.id.tv_dureeprovision);
        this.tv_adressepatient = (TextView) v.findViewById(R.id.tv_adressepatient);
        this.bt_refreshgps = (Button) v.findViewById(R.id.bt_refreshgps);
        this.ch_gps_ok = (CheckBox) v.findViewById(R.id.ch_gps_ok);
        this.ch_patient_recoit_medoc = (CheckBox) v.findViewById(R.id.ch_patient_recoit_medoc);
        this.sp_observationvisite = (Spinner) v.findViewById(R.id.sp_observationvisite);
        hideComponents();
        setEvents();
        setData();
        this.gps = new GPSTracker(getActivity());
        int width = 0;
     //   DisplayMetrics metrics = getResources().getDisplayMetrics();
    //    width = metrics.widthPixels;


      //  dialog.getWindow().setLayout((6 * width) / 7, TableLayout.LayoutParams.WRAP_CONTENT);

    }

    private void hideComponents() {
        if (!Session.getCurrentSuivi().getRaisonvisite().toLowerCase().contains("distribution")) {
            this.tv_autremedicament.setVisibility(View.GONE);
            this.tv_dureeprovision.setVisibility(View.GONE);
            this.ch_patient_recoit_medoc.setVisibility(View.GONE);
        }
        else{

        }
    }

    private void setEvents() {
        this.tv_help.setOnClickListener(new C01871());
        this.bt_refreshgps.setOnClickListener(new C01882());
        this.sp_observationvisite.setOnItemSelectedListener(new C01893());
    }

    private void setData() {
        boolean z = true;
        Suivi2 obj = Session.getCurrentSuivi();
        try {
            boolean z2;
            CheckBox checkBox = this.ch_gps_ok;
            if (obj.getLieuvisite().contains("fixe")) {
                z2 = false;
            } else {
                z2 = true;
            }
            checkBox.setChecked(z2);
        } catch (Exception e) {
        }
        if (obj.isDone()) {
            try {
                this.sp_observationvisite.setSelection(obj.getIdobservationvisite() - 1);
            } catch (Exception e2) {
            }
            try {
                CheckBox checkBox2 = this.ch_gps_ok;
                if (obj.isGpsisforpatient()) {
                    z = false;
                }
                checkBox2.setChecked(z);
            } catch (Exception e3) {
            }
            try {
                this.ch_patient_recoit_medoc.setChecked(obj.isMedoc_recu());
            } catch (Exception e4) {
            }
        }
        Patient p = Session.getCurrentPatient();
        try {
            this.tv_adressepatient.setText("Adresse : " + p.getAdressedomicilaire());
        } catch (Exception e5) {
        }
        try {
            String str;
            TextView textView = this.tv_autremedicament;
            StringBuilder append = new StringBuilder().append("Autres médicaments : ");
            if (obj.isMedoc_inh()) {
                str = "INH,";
            } else {
                str = "*" + (obj.isMedoc_fluconazole() ? "FLUCONAZOLE," : "*") + (obj.isMedoc_cotrimox() ? "COTRIMOX," : "*");
            }
            textView.setText(append.append(str.replace(",*", "").replace("*", "")).toString());
        } catch (Exception e6) {
        }
        try {
            this.tv_lieuvisite.setText("Lieu de visite : " + obj.getLieuvisite());
        } catch (Exception e7) {
        }
        try {
            this.tv_dureeprovision.setText("Durée de la livraison : " + obj.getMedoc_dureelivraison());
        } catch (Exception e8) {
        }
        try {
            this.tv_raison_visite.setText("Raison : " + obj.toString());
        } catch (Exception e9) {
        }
        try {
            this.tv_patient_a_visiter.setText(p.getPrenom() + " " + p.getNom());
        } catch (Exception e10) {
        }
    }

    public void save() {
        Suivi2 obj = Session.getCurrentSuivi();
        obj.setIdobservationvisite(this.sp_observationvisite.getSelectedItemPosition() + 1);
        obj.setMedoc_recu(this.ch_patient_recoit_medoc.isChecked());
        if (!obj.isDone()) {
            String[] gpsdata = this.tv_gps.getText().toString().split(",");
            try {
                obj.setLatitude(Double.parseDouble(gpsdata[0].replace("Lat : ", "")));
                obj.setLongitude(Double.parseDouble(gpsdata[1].replace("Lng : ", "")));
                obj.setAccuracy(Double.parseDouble(gpsdata[2].replace("Préc : ", "").replace("m", "")));
                obj.setGpsisforpatient(this.ch_gps_ok.isChecked());
            } catch (Exception e) {
            }
        }
    }

    public void onConnected(@Nullable Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    public void onMapReady(GoogleMap googleMap) {
        double accuracy;
        LatLng pos;
        this.gMap = googleMap;
        Suivi2 obj = Session.getCurrentSuivi();
        if (obj.isDone()) {
            accuracy = obj.getAccuracy();
            pos = new LatLng(obj.getLatitude(), obj.getLongitude());
            this.bt_refreshgps.setEnabled(false);
        } else {
            this.gps = new GPSTracker(getActivity());
            pos = new LatLng(this.gps.getLatitude(), this.gps.getLongitude());
            accuracy = this.gps.getAccuracy();
        }
        this.tv_gps.setText("Lat : " + pos.latitude + ",Lng : " + pos.longitude + ",Préc : " + accuracy + "m");
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15.0f));
        googleMap.addMarker(new MarkerOptions().title("Ma position").draggable(true).position(pos));
    }

    private void loadGPS() {
        if (this.gps == null) {
            this.gps = new GPSTracker(getActivity());
        } else {
            this.gps.getLocation();
        }
        LatLng pos = new LatLng(this.gps.getLatitude(), this.gps.getLongitude());
        this.tv_gps.setText("Lat : " + pos.latitude + ",Lng : " + pos.longitude + ",Préc : " + this.gps.getAccuracy() + "m");
        try {
            this.gMap.clear();
            this.gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 17.0f));
            this.gMap.addMarker(new MarkerOptions().title("Ma position").draggable(false).position(pos));
        } catch (Exception e) {
        }
    }
}
