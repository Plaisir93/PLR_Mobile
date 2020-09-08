package ht.solutions.plr.util;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat.Builder;


import ht.solutions.plr.R;
import ht.solutions.plr.MainActivity;


public class SendNotification {

	
	public static void SendNotification(Context contexte,CharSequence tickerText,int idNotification) {
		int icon = R.drawable.msppicon;
		Builder builder = new Builder(contexte);
		Notification notification = builder.setContentTitle("Visite")
				.setContentText(tickerText)
				.setSmallIcon(icon)
				.setWhen(System.currentTimeMillis())
				.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
				.build();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;		
		Intent notificationIntent = new Intent(contexte, MainActivity.class);
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent contentIntent = PendingIntent.getActivity(contexte, 0,notificationIntent, 0);
	 	notification.setLatestEventInfo(contexte, "Visite", tickerText,contentIntent);
		NotificationManager manager = (NotificationManager) contexte
				.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(idNotification, notification);
	}
}
