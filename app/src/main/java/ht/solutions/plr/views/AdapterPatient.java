package ht.solutions.plr.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import ht.solutions.plr.R;

import ht.solutions.plr.entities.Patient;
import ht.solutions.plr.util.DatePickerFragment;

import java.util.ArrayList;
import java.util.List;

public class AdapterPatient extends ArrayAdapter<Patient> {
    private Context context;
    private List<Integer> mModel = new ArrayList();
    private final List<Patient> objs;

    public AdapterPatient(Context context, int textViewResourceId, List<Patient> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.objs = objects;
    }

    public int getCount() {
        return this.objs.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
         if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.view_patient, parent,false);
        }else {

            rowView = convertView;
        }
        TextView tvnom = (TextView) rowView.findViewById(R.id.tv_view_patient_nom);
        TextView tvage = (TextView) rowView.findViewById(R.id.tv_view_patient_age);
        TextView tvtel = (TextView) rowView.findViewById(R.id.tv_view_patient_tel);
        TextView tvadresse = (TextView) rowView.findViewById(R.id.tv_view_patient_adresse);
        Button btsync = (Button) rowView.findViewById(R.id.bt_view_suivis_sync);
        final Patient obj = (Patient) this.objs.get(position);
        ((TextView) rowView.findViewById(R.id.tv_view_patient_num)).setText("" + (position + 1));
        tvnom.setText( obj.getPrenom() + " " +obj.getNom().toUpperCase());
        try {
            tvage.setText((new DatePickerFragment().getYear() - Integer.parseInt(obj.getDatenaissance().replace(" 12:00:00 AM", "").split("/")[2])) + " ans");
        } catch (Exception e) {
            tvage.setText("Non spécifié");
        }
        try {
            tvage.setText((new DatePickerFragment().getYear() - Integer.parseInt(obj.getDatenaissance().replace(" 12:00:00 AM", "").split("/")[2])) + " ans");
        } catch (Exception e2) {
            tvage.setText("Non spécifié");
        }
        tvtel.setText("Tél: " + obj.getTelephone());
        tvadresse.setText("Adr : " + obj.getAdressedomicilaire());
        if (obj.getLatitude() == 0.0d || obj.getLongitude() == 0.0d) {
            btsync.setVisibility(View.GONE);
        } else {
            btsync.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    AdapterPatient.this.context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://maps.google.com/maps?daddr=" + obj.getLatitude() + "," + obj.getLongitude())));
                }
            });
        }
//        rowView.setOnClickListener(new OnClickListener() {
//            public void onClick(View v) {
//                Session.setFragment(FragmentPatient.TAG);
//                Intent intent = new Intent(AdapterPatient.this.context, MainActivity.class);
//                intent.putExtra("idpatient", obj.getId());
//                AdapterPatient.this.context.startActivity(intent);
//            }
//        });
        return rowView;
    }

    public void bind(List<Integer> model) {
        this.mModel = model;
    }
}
