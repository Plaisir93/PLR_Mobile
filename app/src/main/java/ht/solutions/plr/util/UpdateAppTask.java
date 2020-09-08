package ht.solutions.plr.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import ht.solutions.plr.data.Session;


public class UpdateAppTask extends AsyncTask<String, Integer, String> {
	String _message="";
	private Button sync;
	private Context context;
	private ProgressDialog mProgressDialog;
	//private ProgressDialog mProgressDialog;
	public UpdateAppTask(Context c,Button bt){
		this.context = c;
		this.sync = bt;
		mProgressDialog = new ProgressDialog(context);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setProgress(0);
        mProgressDialog.setMax(100);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
	}
	 	 @Override
     protected void onPostExecute(String action) {
//         // 'mImageView.setImageBitmap(result);
//         mProgressDialog.setProgress(100);
//         mProgressDialog.dismiss();
//         super.onPostExecute("");
//         if (_message.equalsIgnoreCase("")){
//
//           //  MessageToShow();
//         }else{
//             MessageToShow();
//         }
     }
	     @Override
	        protected void onProgressUpdate(Integer...progress) {
	            mProgressDialog.setProgress(progress[0]);
	        }
	     @Override
	     protected void onPreExecute() {
	    	 mProgressDialog.setMessage("Téléchargment en cours ...");
	    	 mProgressDialog.show();
	     }
	@Override
	protected String doInBackground(String... arg0) {
		 Log.v("directory",(Environment.getExternalStorageDirectory().getPath()));
         InputStream input = null;
         OutputStream output = null;
         HttpURLConnection connection = null;
         try {
             URL url = null;;
			try {
				url = new URL(Session.getUrlAPK());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             try {
				connection = (HttpURLConnection) url.openConnection();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             connection.connect();
             
             // expect HTTP 200 OK, so we don't mistakenly save error report
             // instead of the file
             if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            	    Log.v("directory", connection.getResponseCode()
                            + " " + connection.getResponseMessage());
                 return "Server returned HTTP " + connection.getResponseCode()
                         + " " + connection.getResponseMessage();
             
                 
             }

             // this will be useful to display download percentage
             // might be -1: server did not report the length
             int fileLength = connection.getContentLength();

             // download the file
             input = connection.getInputStream();

             try{
            	 File file = new File(Environment.getExternalStorageDirectory().getPath()+"/Mobile.apk");
                  boolean deleted = file.delete();

             }catch(Exception ex){

             }

             output = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+"/Mobile.apk");

             byte data[] = new byte[4096];
             long total = 0;
             int count;
             while ((count = input.read(data)) != -1) {
                 // allow canceling with back button
                 if (isCancelled()) {
                     input.close();
                     return null;
                 }
                 total += count;
                 // publishing the progress....
                 if (fileLength > 0) // only if total length is known
                	 	publishProgress((int) (total * 100 / fileLength));
                 output.write(data, 0, count);
             }
             if (connection != null)
                 connection.disconnect();

             
                 Intent intent = new Intent();
                 intent.setAction(Intent.ACTION_VIEW);
                 File file = new File(Environment.getExternalStorageDirectory().getPath()+"/Mobile.apk");
                 intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                 context.startActivity(intent);
        
          }catch( UnknownHostException uhe ){
        	  final String mess = uhe.getMessage();
        	  this.sync.post(new Runnable(){
					@Override
					public void run() {
						 Toast.makeText(context, "Verifiez la connexion INTERNET " + mess, Toast.LENGTH_LONG).show();
					}
				});
        	   
                
          } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//          catch (Exception e) {
//        	  final String mess = e.getMessage() + " ::"+ e.getCause().toString();
//        	  this.sync.post(new Runnable(){
//					@Override
//					public void run() {
//						 Toast.makeText(context, "Reesayer "+ mess, Toast.LENGTH_LONG).show();
//					}
//				});
//      	   
//             return e.toString();
//         } 
         finally {
             try {
                 if (output != null)
                     output.close();
                 if (input != null)
                     input.close();
             } catch (IOException ignored) {
             }

        
         }
         return null;

		 
	}
}
