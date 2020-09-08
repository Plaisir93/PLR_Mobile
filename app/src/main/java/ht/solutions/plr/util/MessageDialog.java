package ht.solutions.plr.util;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class MessageDialog {

    static class C01361 implements OnClickListener {
        C01361() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    }

    public static void message(Context c, String titre, String message) {
        AlertDialog alertDialog = new Builder(c).create();
        alertDialog.setMessage(message);
        alertDialog.setButton(-3, "OK", new C01361());
        alertDialog.show();
    }
}
