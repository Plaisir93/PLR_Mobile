package ht.solutions.plr.entities;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

public class DeviceInformation {
    private String imei;
    private String model;
    private String macaddress;
    private String manufacturer;
    private String version;
	private String phoneNumberLine1;

	public void initialize(Context c){
    	try{
    		WifiManager wm = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
    		version = android.os.Build.VERSION.RELEASE;
    	  	model = android.os.Build.MODEL;
    	    manufacturer=android.os.Build.MANUFACTURER;
    	    macaddress = wm.getConnectionInfo().getMacAddress();   
    	    TelephonyManager tm = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
    	   try{
               imei = tm.getDeviceId();
           }catch (Exception xx){}
    	 	if(imei == null)
				imei = macaddress;
			phoneNumberLine1 = tm.getLine1Number();
    	}catch(Exception e){}
    }

	public String getPhoneNumberLine1() {
		return phoneNumberLine1;
	}

	public void setPhoneNumberLine1(String phoneNumberLine1) {
		this.phoneNumberLine1 = phoneNumberLine1;
	}

	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMacaddress() {
		return macaddress;
	}
	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
    
    
    
}
