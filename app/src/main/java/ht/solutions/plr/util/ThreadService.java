package ht.solutions.plr.util;

import android.content.Context;
import ht.solutions.plr.webservices.FindSuivisFacade;

public class ThreadService extends Thread {
    private Context context;

    public ThreadService(Context context) {
        this.context = context;
        start();
    }

    public void run() {
        while (true) {
            FindSuivisFacade findSuivisFacade = new FindSuivisFacade(this.context);
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
