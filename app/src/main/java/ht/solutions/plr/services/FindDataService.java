package ht.solutions.plr.services;

import android.app.IntentService;
import android.content.Intent;
import ht.solutions.plr.webservices.FindSuivisFacade;

public class FindDataService extends IntentService {
    public FindDataService() {
        super("suivipatient.finddata");
    }

    protected void onHandleIntent(Intent intent) {
        FindSuivisFacade findSuivisFacade = new FindSuivisFacade(getBaseContext());
    }
}
