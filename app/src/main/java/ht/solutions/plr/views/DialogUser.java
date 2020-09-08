package ht.solutions.plr.views;

import android.widget.*;
import android.view.*;
import android.content.Context;
import android.app.Dialog;
import android.os.Bundle;


import ht.solutions.plr.R;
import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.User;

public class DialogUser extends Dialog {
	private EditText pseudo;
	private EditText password;
	private EditText nom;
	private EditText prenom;
	private RadioButton sexe_1;
	private RadioButton sexe_2;
	private Button save;
	private Button cancel;
	private Context context;

	public DialogUser(Context context) {
		super(context);	
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.dialog_user);
		initComponents();
		setEvents();
	}

	private void initComponents() {
		pseudo = (EditText) findViewById(R.id.et_user_pseudo);
		password = (EditText) findViewById(R.id.et_user_password);
		nom = (EditText) findViewById(R.id.et_user_nom);
		prenom = (EditText) findViewById(R.id.et_user_prenom);
		sexe_1 = (RadioButton) findViewById(R.id.rb_user_sexe_1);
		sexe_2 = (RadioButton) findViewById(R.id.rb_user_sexe_2);
		save = (Button) findViewById(R.id.btsave_user);
		cancel = (Button) findViewById(R.id.btcancel_user);
	}

	public boolean setValues() {
		User obj = new User();
		obj.setPseudo(pseudo.getText().toString());
		obj.setPassword(password.getText().toString());
		obj.setNom(nom.getText().toString());
		obj.setPrenom(prenom.getText().toString());
		if (sexe_1.isChecked())
			obj.setSexe("M");
		if (sexe_2.isChecked())
			obj.setSexe("F");
		long code = User.Insert(context, obj);
		Session.setUser(User.SelectById(context, code));
		return obj.isSet();
	}

	private void setEvents() {
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (setValues())
					DialogUser.this.cancel();
			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogUser.this.cancel();
			}
		});
	}
}
