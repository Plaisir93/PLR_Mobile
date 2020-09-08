package ht.solutions.plr.views;


import android.widget.*;
import android.view.*;
import android.content.Context;
import android.app.Dialog;
import android.os.Bundle;


import ht.solutions.plr.R;
import ht.solutions.plr.entities.Patient;


public class DialogPatient extends Dialog {
	private EditText nom;
	private EditText prenom;
	private EditText age;
	private EditText datenaissance;
	private EditText statut;
	private Button save;
	private Button cancel;
	private Context context;
	private int position = -1;
	private ListView listView;
	private TextView quantite;

	public DialogPatient(Context context, ListView listView, int position,
			TextView quantite) {
		super(context);
		this.context = context;
		this.listView = listView;
		this.position = position;
		this.quantite = quantite;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.dialog_patient);
		initComponents();
		fillFields();
		setEvents();
	}

	private void initComponents() {

		nom = (EditText) findViewById(R.id.et_patient_nom);
		prenom = (EditText) findViewById(R.id.et_patient_prenom);
		age = (EditText) findViewById(R.id.et_patient_age);
		datenaissance = (EditText) findViewById(R.id.et_patient_datenaissance);
		statut = (EditText) findViewById(R.id.et_patient_statut);
		save = (Button) findViewById(R.id.btsave_patient);
		cancel = (Button) findViewById(R.id.btcancel_patient);
	}

	private void fillFields() {
		if (this.position == -1)
			return;
		// Remplacer getCurrentObject par l'objet correspondant
		// Patient obj =
		// Session.getCurrentPatient().getPatients().get(position);
		// nom.setText(""+obj.getNom());
		// prenom.setText(""+obj.getPrenom());
		// age.setText(""+obj.getAge());
		// datenaissance.setText(""+obj.getDatenaissance());
		// statut.setText(""+obj.getStatut());
	}

	public boolean setValues() {
		Patient obj = new Patient();

		obj.setNom(nom.getText().toString());
		obj.setPrenom(prenom.getText().toString());
		obj.setAge((int) Double.parseDouble(age.getText().toString()));
		obj.setDatenaissance(datenaissance.getText().toString());
		obj.setStatut(statut.getText().toString());
		return obj.isSet();
	}

	private void setEvents() {
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (setValues())
					DialogPatient.this.cancel();
			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogPatient.this.cancel();
			}
		});
	}

}
