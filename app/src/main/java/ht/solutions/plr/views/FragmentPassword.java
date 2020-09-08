package ht.solutions.plr.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import ht.solutions.plr.R;
import ht.solutions.plr.data.Session;
import ht.solutions.plr.util.UpdateAppTask;
import ht.solutions.plr.entities.ChaineConnection;
import ht.solutions.plr.webservices.ChangePasswordFacade;
import ht.solutions.plr.webservices.FindInstitutionsFacade;

public class FragmentPassword extends Fragment {
    public static final String TAG = "password";
    private EditText adresseip;
    private Button bt_cancelpassword;
    private Button bt_changepassword;
    private Button bt_institution;
    private Button btnIP;
    private EditText conf_pass;
    private LinearLayout ll_changepassword;
    private LinearLayout ll_password;
    private ImageView new_conf_pass;
    private EditText new_pass;
    private EditText old_pass;
    private Button submit;
    private Button updateapp;

    class C01631 implements OnClickListener {
        C01631() {
        }

        public void onClick(View view) {
            ProgressDialog prog = new ProgressDialog(FragmentPassword.this.getActivity());
            prog.setMessage("Récupération de la liste des institutions");
            prog.setProgressStyle(1);
            prog.setProgress(0);
            prog.setMax(100);
            prog.setCancelable(false);
            prog.show();
            FindInstitutionsFacade findInstitutionsFacade = new FindInstitutionsFacade(FragmentPassword.this.getActivity(), prog);
        }
    }

    class C01642 implements TextWatcher {
        C01642() {
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public void afterTextChanged(Editable s) {
            FragmentPassword.this.new_conf_pass.setVisibility(View.VISIBLE);
            if (FragmentPassword.this.new_pass.getText().toString().equals(FragmentPassword.this.conf_pass.getText().toString())) {
                FragmentPassword.this.new_conf_pass.setImageResource(R.drawable.btn_check_buttonless_on);
            } else {
                FragmentPassword.this.new_conf_pass.setImageResource(R.drawable.btn_close_pressed);
            }
        }
    }

    class C01653 implements OnClickListener {
        C01653() {
        }

        public void onClick(View arg0) {
            new UpdateAppTask(FragmentPassword.this.getActivity(), FragmentPassword.this.updateapp).execute(new String[0]);
        }
    }

    class C01664 implements OnClickListener {
        C01664() {
        }

        public void onClick(View v) {
            String newpass = FragmentPassword.this.new_pass.getText().toString();
            if (FragmentPassword.this.conf_pass.getText().toString().equals(newpass) && FragmentPassword.this.old_pass.getText().toString().equals(Session.getUser().getPassword())) {
                ChangePasswordFacade changePasswordFacade = new ChangePasswordFacade(FragmentPassword.this.getActivity(), newpass, FragmentPassword.this.submit);
            } else {
                Toast.makeText(FragmentPassword.this.getActivity(), "Verifiez nouveau mot de passe!", Toast.LENGTH_LONG).show();
            }
        }
    }

    class C01675 implements OnClickListener {
        C01675() {
        }

        public void onClick(View v) {
            ChaineConnection connect = new ChaineConnection();
            if (FragmentPassword.this.adresseip.getText().toString().length() >= 11) {
                connect.setAdresseip(FragmentPassword.this.adresseip.getText().toString());
                connect.setWebservicespath("Systeme_Suivi_Patient/WebServices.asmx");
                ChaineConnection.Delete(FragmentPassword.this.getActivity());
                ChaineConnection.Insert(FragmentPassword.this.getActivity(), connect);
                Toast.makeText(FragmentPassword.this.getActivity(), "Adresse IP modifiee avec succes!\n" + ChaineConnection.SelectAll(FragmentPassword.this.getActivity()).get(0), Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(FragmentPassword.this.getActivity(), "Verifiez l'adresse IP!", Toast.LENGTH_LONG).show();
        }
    }

    class C01686 implements OnClickListener {
        C01686() {
        }

        public void onClick(View v) {
            FragmentPassword.this.ll_changepassword.setVisibility(View.VISIBLE);
            FragmentPassword.this.ll_password.setVisibility(View.GONE);
        }
    }

    class C01697 implements OnClickListener {
        C01697() {
        }

        public void onClick(View v) {
            FragmentPassword.this.ll_changepassword.setVisibility(View.GONE);
            FragmentPassword.this.ll_password.setVisibility(View.VISIBLE);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_password, container, false);
        this.old_pass = (EditText) v.findViewById(R.id.et_frag_old_password);
        this.new_pass = (EditText) v.findViewById(R.id.et_frag_new_password);
        this.conf_pass = (EditText) v.findViewById(R.id.et_frag_confirm_password);
        this.new_conf_pass = (ImageView) v.findViewById(R.id.img_frag_password_isCorrect);
        this.submit = (Button) v.findViewById(R.id.bt_frag_password_submit);
        this.btnIP = (Button) v.findViewById(R.id.bt_settings_sub);
        this.updateapp = (Button) v.findViewById(R.id.bt_settings_updateapp);
        this.adresseip = (EditText) v.findViewById(R.id.et_settings_adresseip);
        this.adresseip.setText(((ChaineConnection) ChaineConnection.SelectAll(getActivity()).get(0)).toString());
        this.bt_institution = (Button) v.findViewById(R.id.bt_password_institution);
 //       this.bt_changepassword = (Button) v.findViewById(R.id.bt_changepassword);
//        this.bt_cancelpassword = (Button) v.findViewById(R.id.bt_frag_password_cancel);
//        this.ll_changepassword = (LinearLayout) v.findViewById(R.id.ll_changepassword);
//        this.ll_password = (LinearLayout) v.findViewById(R.id.ll_password);
        this.bt_institution.setOnClickListener(new C01631());
        this.conf_pass.addTextChangedListener(new C01642());
        this.updateapp.setOnClickListener(new C01653());
        this.submit.setOnClickListener(new C01664());
        this.btnIP.setOnClickListener(new C01675());
     //   this.bt_cancelpassword.setOnClickListener(new C01686());
     //   this.bt_changepassword.setOnClickListener(new C01697());
        return v;
    }

    void show() {
        Toast.makeText(getActivity(), "Text Changing!", Toast.LENGTH_LONG).show();
    }
}
