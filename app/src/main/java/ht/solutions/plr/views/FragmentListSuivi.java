package ht.solutions.plr.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;


import ht.solutions.plr.R;
import ht.solutions.plr.MainActivity;
import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.Patient;
import ht.solutions.plr.entities.Suivi2;
import ht.solutions.plr.util.MessageDialog;
import ht.solutions.plr.util.ThreadService;
import ht.solutions.plr.webservices.FindSuivisFacade;
import ht.solutions.plr.webservices.SuiviFacade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentListSuivi extends ListFragment {
    public static final String TAG = "listSuivi";
    private Button btSyncTout;
    private Context f7c;
    private int countItems = 0;
    private Button first;
    private long index = 1;
    private Button last;
    private TextView mCount;
    private TextView mPaging;
    private EditText mSearch;
    private long max;
    private Button next;
    private CheckBox nonSync;
    List<Suivi2> objs = new ArrayList();
    private Button previous;
    private long step = 10;

    class C01551 implements OnClickListener {
        C01551() {
        }

        public void onClick(View v) {
            if (new Date().getTime() - Session.getLasttime() > 1800000) {
                Session.setFragment(FragmentLogin.TAG);
                FragmentListSuivi.this.getActivity().startActivity(new Intent(FragmentListSuivi.this.getActivity(), MainActivity.class));
            }
            List<Suivi2> list = Suivi2.SelectAllForSync(FragmentListSuivi.this.getActivity(), Session.getUser().getId());
            ProgressDialog mProgressDialog = ProgressDialog.show(FragmentListSuivi.this.getActivity(), "Patientez", "Synchronisation en cours .....", true);
            SuiviFacade suiviFacade = new SuiviFacade(FragmentListSuivi.this.getActivity(), list, FragmentListSuivi.this.btSyncTout);
        }
    }

    class C01562 implements OnClickListener {
        C01562() {
        }

        public void onClick(View arg0) {
            if (FragmentListSuivi.this.index + FragmentListSuivi.this.step < FragmentListSuivi.this.max + 1) {
                FragmentListSuivi.this.index = FragmentListSuivi.this.index + FragmentListSuivi.this.step;
                FragmentListSuivi.this.updateList();
            }
        }
    }

    class C01573 implements OnClickListener {
        C01573() {
        }

        public void onClick(View arg0) {
            if (FragmentListSuivi.this.index > 1) {
                FragmentListSuivi.this.index = FragmentListSuivi.this.index - FragmentListSuivi.this.step;
                FragmentListSuivi.this.updateList();
            }
        }
    }

    class C01584 implements OnClickListener {
        C01584() {
        }

        public void onClick(View arg0) {
            if (FragmentListSuivi.this.index != 1) {
                FragmentListSuivi.this.index = 1;
                FragmentListSuivi.this.updateList();
            }
        }
    }

    class C01595 implements OnClickListener {
        C01595() {
        }

        public void onClick(View arg0) {
            if (FragmentListSuivi.this.index + FragmentListSuivi.this.step < FragmentListSuivi.this.max + 1) {
                FragmentListSuivi.this.index = (FragmentListSuivi.this.max - (FragmentListSuivi.this.max % FragmentListSuivi.this.step)) + 1;
                FragmentListSuivi.this.updateList();
            }
        }
    }

    class C01606 implements OnKeyListener {
        C01606() {
        }

        public boolean onKey(View view, int keyCode, KeyEvent event) {
            if (event.getAction() != 0 || keyCode != 66) {
                return false;
            }
            List<Suivi2> p = new ArrayList();
            for (Long id : Patient.SelectByName(FragmentListSuivi.this.getActivity(), FragmentListSuivi.this.mSearch.getText().toString())) {
                for (Suivi2 s : FragmentListSuivi.this.objs) {
                    if (id.longValue() == s.getIdpatient()) {
                        p.add(s);
                    }
                }
            }
            FragmentListSuivi.this.setListAdapter(new AdapterSuivi(FragmentListSuivi.this.getActivity(), 17367049, p));
            FragmentListSuivi.this.countItems = p.size();
            return true;
        }
    }

    class C01617 implements OnCheckedChangeListener {
        C01617() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (FragmentListSuivi.this.nonSync.isChecked()) {
                List<Suivi2> p = Suivi2.SelectByColumn(FragmentListSuivi.this.getActivity(), "sync", "0", Session.getUser().getId());
                FragmentListSuivi.this.setListAdapter(new AdapterSuivi(FragmentListSuivi.this.getActivity(), 17367049, p));
                FragmentListSuivi.this.countItems = p.size();
                return;
            }
            FragmentListSuivi.this.setListAdapter(new AdapterSuivi(FragmentListSuivi.this.getActivity(), 17367049, FragmentListSuivi.this.objs));
            FragmentListSuivi.this.countItems = FragmentListSuivi.this.objs.size();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_suivi, container, false);
        this.f7c = v.getContext();
        initComponents(v);
        setEvents();
        if (!Session.getServerMessage().equals("")) {
            MessageDialog.message(getActivity(), "Message serveur", Session.getServerMessage());
        }
        return v;
    }

    public void onResume() {
        super.onResume();
        if (!Session.getServerMessage().equals("")) {
            MessageDialog.message(getActivity(), "Message", Session.getServerMessage());
        }
    }

    public void show(String str) {
        updateList();
        updatePagerNumbering();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateList();
        updatePagerNumbering();
    }

    public void updateList() {
        switch (Session.getMenuSelected()) {
            case 1:
                this.objs = Suivi2.SelectAll(getActivity(), Session.getUser().getId());
                this.mCount.setText("Sync: " + Suivi2.Count(getActivity(), "sync", "1"));
                break;
        }
        setListAdapter(new AdapterSuivi(getActivity(), android.R.layout.simple_list_item_1, this.objs));
        this.countItems = this.objs.size();
    }

    private void setEvents() {
        this.btSyncTout.setOnClickListener(new C01551());
        this.next.setOnClickListener(new C01562());
        this.previous.setOnClickListener(new C01573());
        this.first.setOnClickListener(new C01584());
        this.last.setOnClickListener(new C01595());
        this.mSearch.setOnKeyListener(new C01606());
        this.nonSync.setOnCheckedChangeListener(new C01617());
    }

    private void initComponents(View v) {
        this.first = (Button) v.findViewById(R.id.bt_suivi_first);
        this.last = (Button) v.findViewById(R.id.bt_suivi_last);
        this.next = (Button) v.findViewById(R.id.bt_suivi_suiv);
        this.previous = (Button) v.findViewById(R.id.bt_suivi_prec);
        this.mCount = (TextView) v.findViewById(R.id.tv_suivi_syncinfo);
        this.mPaging = (TextView) v.findViewById(R.id.tv_list_suivi_pager);
        this.mSearch = (EditText) v.findViewById(R.id.sv_list_suivi_search);
        this.btSyncTout = (Button) v.findViewById(R.id.bt_list_suivi_sync_all);
        this.nonSync = (CheckBox) v.findViewById(R.id.cb_list_suivi_synchronized);
    }

    private void updatePagerNumbering() {
        this.mPaging.setText("1/" + (((long) this.countItems) / this.step));
    }
}
