package ht.solutions.plr.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import ht.solutions.plr.R;
import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.Patient;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.util.Utilitaire;

import java.util.ArrayList;
import java.util.List;

public class AdapterSuivi extends ArrayAdapter<Suivi2> {
    private Context context;
    private List<Integer> mModel = new ArrayList();
    private final List<Suivi2> objs;

    public AdapterSuivi(Context context, int textViewResourceId, List<Suivi2> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.objs = objects;
    }

    public int getCount() {
        return this.objs.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        final Suivi2 obj;
        final Patient p;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.view_suivi, parent,false);
        }else {
            rowView = convertView;
        }
        ImageView statusSync = (ImageView) rowView.findViewById(R.id.iv_suivi_sync);
        ((TextView) rowView.findViewById(R.id.tv_view_patient_num)).setText("" + (position + 1));
        ImageView statusDone = (ImageView) rowView.findViewById(R.id.iv_suivi_fait);
        TextView tvraisonVisite = (TextView) rowView.findViewById(R.id.tv_raisons);
        TextView tvdateVisite = (TextView) rowView.findViewById(R.id.tv_suivi_dateprevuevisite);
        TextView tvpatient = (TextView) rowView.findViewById(R.id.tv_patient);
        if (position % 2 == 0) {
            obj = (Suivi2) this.objs.get(position);
            p = (Patient) Patient.SelectByColumn(this.context, "onlineid", "" + obj.getIdpatient()).get(0);
        } else {
            obj = (Suivi2) this.objs.get(position);
            p = (Patient) Patient.SelectByColumn(this.context, "onlineid", "" + obj.getIdpatient()).get(0);
        }
        try {
            tvpatient.setText(p.getPrenom() + " " + p.getNom().toUpperCase());
        } catch (Exception e) {
        }
        tvraisonVisite.setText(obj.toString());
        try {
            String date = "";
            try {
                String[] dates = obj.getDateprevue().replace("12:00:00 AM", "").split("/");
                date = dates[1] + " " + Utilitaire.MOIS[(int) Double.parseDouble(dates[0])] + " " + dates[2];
            } catch (Exception e2) {
                date = obj.getDateprevue();
            }
            tvdateVisite.setText(date);
            if (obj.isSync()) {
                statusSync.setBackgroundResource(R.drawable.ic_checkmark_holo_light);
            } else {
                statusSync.setBackgroundResource(R.drawable.ic_menu_close_clear_cancel);
            }
            if (obj.isDone()) {
                statusDone.setBackgroundResource(R.drawable.ic_checkmark_holo_light);
            } else {
                statusDone.setBackgroundResource(R.drawable.ic_menu_close_clear_cancel);
            }
        } catch (Exception e3) {
        }
        rowView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Session.setCurrentSuivi(obj);
                Session.setCurrentPatient(p);
                AdapterSuivi.this.context.startActivity(new Intent(AdapterSuivi.this.context, SuiviActivity.class));
            }
        });
        rowView.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                DialogDelete dialog = new DialogDelete(AdapterSuivi.this.context, Long.valueOf(obj.getId()));
                dialog.setTitle("Suppresion de Suivi");
                dialog.show();
                return true;
            }
        });
        return rowView;
    }

    public void bind(List<Integer> model) {
        this.mModel = model;
    }
}
