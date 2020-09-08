package ht.solutions.plr.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ht.solutions.plr.R;

import ht.solutions.plr.data.Session;
import ht.solutions.plr.entities.Patient;

import java.util.List;

public class FragmentListPatient extends ListFragment {
    public static final String TAG = "listPatient";
    private Context f6c;
    private int countItems = 0;
    private Button first;
    private long index = 1;
    private Button last;
    private TextView mCount;
    private TextView mPaging;
    private EditText mSearch;
    private long max;
    private Button next;
    private Button previous;
    private long step = 10;

    class C01501 implements OnClickListener {
        C01501() {
        }

        public void onClick(View arg0) {
            if (FragmentListPatient.this.index + FragmentListPatient.this.step < FragmentListPatient.this.max + 1) {
                FragmentListPatient.this.index = FragmentListPatient.this.index + FragmentListPatient.this.step;
                FragmentListPatient.this.updateList();
            }
        }
    }

    class C01512 implements OnClickListener {
        C01512() {
        }

        public void onClick(View arg0) {
            if (FragmentListPatient.this.index > 1) {
                FragmentListPatient.this.index = FragmentListPatient.this.index - FragmentListPatient.this.step;
                FragmentListPatient.this.updateList();
            }
        }
    }

    class C01523 implements OnClickListener {
        C01523() {
        }

        public void onClick(View arg0) {
            if (FragmentListPatient.this.index != 1) {
                FragmentListPatient.this.index = 1;
                FragmentListPatient.this.updateList();
            }
        }
    }

    class C01534 implements OnClickListener {
        C01534() {
        }

        public void onClick(View arg0) {
            if (FragmentListPatient.this.index + FragmentListPatient.this.step < FragmentListPatient.this.max + 1) {
                FragmentListPatient.this.index = (FragmentListPatient.this.max - (FragmentListPatient.this.max % FragmentListPatient.this.step)) + 1;
                FragmentListPatient.this.updateList();
            }
        }
    }

    class C01545 implements OnKeyListener {
        C01545() {
        }

        public boolean onKey(View view, int keyCode, KeyEvent event) {
            if (event.getAction() != 0 || keyCode != 66) {
                return false;
            }
            FragmentListPatient.this.updateList();
            return true;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_patient, container, false);
        this.f6c = v.getContext();
        initComponents(v);
        setEvents();
        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateList();
        updatePagerNumbering();
    }

    public void updateList() {
        List<Patient> objs = Patient.SelectByUser(getActivity(), Session.getUser().getId() + "");
        setListAdapter(new AdapterPatient(getActivity(), 17367049, objs));
        this.countItems = objs.size();
    }

    private void setEvents() {
        this.next.setOnClickListener(new C01501());
        this.previous.setOnClickListener(new C01512());
        this.first.setOnClickListener(new C01523());
        this.last.setOnClickListener(new C01534());
        this.mSearch.setOnKeyListener(new C01545());
    }

    private void initComponents(View v) {
        this.first = (Button) v.findViewById(R.id.bt_listpatient_first);
        this.last = (Button) v.findViewById(R.id.bt_listpatient_last);
        this.next = (Button) v.findViewById(R.id.bt_listpatient_suiv);
        this.previous = (Button) v.findViewById(R.id.bt_listpatient_prec);
        this.mCount = (TextView) v.findViewById(R.id.tv_listpatient_syncinfo);
        this.mPaging = (TextView) v.findViewById(R.id.tv_listpatient_pager);
        this.mSearch = (EditText) v.findViewById(R.id.sv_listpatient_search);
    }

    private void updatePagerNumbering() {
        this.mPaging.setText("1/" + (((long) this.countItems) / this.step));
    }
}
