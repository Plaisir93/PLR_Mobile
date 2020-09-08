package ht.solutions.plr.views;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


import ht.solutions.plr.R;
import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.MainActivity;


public class DialogDelete extends Dialog{

	private Context context;
	private Long id;
	private Button btn_one, btn_all;
	private LinearLayout progress;
	
	public DialogDelete(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public DialogDelete(Context c, Long id){
		super(c);
		context = c;
		this.id = id;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.dialog_sup);
		
		btn_one = (Button) findViewById(R.id.btn_sup_one);
		btn_all = (Button) findViewById(R.id.btn_sup_all);
		progress = (LinearLayout) findViewById(R.id.progess_sup);
		
		btn_one.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				btn_one.setVisibility(View.GONE);
				btn_all.setVisibility(View.GONE);
				progress.setVisibility(View.VISIBLE);
				Suivi2.Delete(context, "id", id+"");
				
				Session.setFragment("listSuivi");
				Intent intent = new Intent(context, MainActivity.class);
				context.startActivity(intent);
			}
		});
		
		btn_all.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				btn_one.setVisibility(View.GONE);
				btn_all.setVisibility(View.GONE);
				progress.setVisibility(View.VISIBLE);
				Suivi2.DeleteByAgent(context, Session.getUser().getId());
				
				Session.setFragment("listSuivi");
				Intent intent = new Intent(context, MainActivity.class);
				context.startActivity(intent);
				
			}
			
		});
	}

}
