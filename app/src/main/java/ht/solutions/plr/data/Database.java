package ht.solutions.plr.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Database {
	private DatabaseHandler handler;
	private SQLiteDatabase bdd;

	public Database(Context context) {
		handler = new DatabaseHandler(context, "suivipatient_database_v_3.bd",	null, 1);
	}

	public void open() {
		bdd = handler.getWritableDatabase();
	}

	public void close() {
		bdd.close();
	}

	public SQLiteDatabase getBdd() {
		return bdd;
	}
}
