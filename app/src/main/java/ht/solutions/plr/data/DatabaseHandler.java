package ht.solutions.plr.data;

import ht.solutions.plr.entities.Attribut;
import ht.solutions.plr.entities.ChaineConnection;
import ht.solutions.plr.entities.Commune;
import ht.solutions.plr.entities.FingerPatient;
import ht.solutions.plr.entities.Institution;
import ht.solutions.plr.entities.ObservationVisite;
import ht.solutions.plr.entities.Patient;
import ht.solutions.plr.entities.RaisonRattement;
import ht.solutions.plr.entities.RaisonVisite;
import ht.solutions.plr.entities.SectionCommunale;
import ht.solutions.plr.entities.Sensibilisation;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.entities.Suivi_Medicament;
import ht.solutions.plr.entities.User;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	private Context context;

	public DatabaseHandler(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		genererDB(db);
		 
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(Suivi_Medicament.getScript());
	}

	private void genererDB(SQLiteDatabase db) {
		db.execSQL(Suivi_Medicament.getScript());
		db.execSQL(ChaineConnection.getScript());
		db.execSQL(Patient.getScript());
		db.execSQL(RaisonVisite.getScript());
		db.execSQL(ObservationVisite.getScript());
		db.execSQL(RaisonRattement.getScript());
		db.execSQL(Sensibilisation.getScript());
		db.execSQL(Attribut.getScript());
		db.execSQL(Suivi2.getScript());
		db.execSQL(User.getScript());
		db.execSQL(Commune.getScript());
		db.execSQL(FingerPatient.getScript());
		db.execSQL(SectionCommunale.getScript());
		db.execSQL(Institution.getScript());
	}
}
