package com.sefagurel.rsshaber_sondakika.tools;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sefagurel.rsshaber_sondakika.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

/**
 * Created by Sefa on 17.12.2015.
 */
public class Tools {

    public static void saveErrors(Exception e) {

//        ACRA.getErrorReporter().handleSilentException(e);
        e.printStackTrace();

        // Mint.logException(e);

        // String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // ExceptionFeedBack exfb = new ExceptionFeedBack();
        // exfb.setCUSTOM_DATA(Log.getStackTraceString(e));
        // // exfb.setERROR(Integer.toString(Log.getStackTraceString(e).length()));
        // exfb.setERROR(e.fillInStackTrace().toString());
        // exfb.setUId(Integer.toString(Info.UserId));
        // exfb.setUSER_CRASH_DATE(date);
        // exfb.setIsCompleted(false);
        // exfb.Insert();

    }



    public static String getPhoneImei(Context c) {
        TelephonyManager TelephonyMgr = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneIMEI = TelephonyMgr.getDeviceId();
        return phoneIMEI;
    }



    public static String getSimCardNumber(Context c) {

        TelephonyManager phoneManager = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
        return phoneManager.getSimSerialNumber();

    }

    public static boolean isTablet(Context c) {
        return (c.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static void toggleGPS(Context c, boolean enable) {
        String provider = Settings.Secure.getString(c.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);

        if (provider.contains("gps") == enable) {
            return; // the GPS is already in the requested state
        }

        final Intent poke = new Intent();
        poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
        poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
        poke.setData(Uri.parse("3"));
        c.sendBroadcast(poke);
    }



    public static boolean isConnectingToInternet(Context c) {
        ConnectivityManager connectivity = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public static Boolean isGPSEnabled(Context c) {
        LocationManager locationManager = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }



    public static void copyFiles(File sourceLocation, File targetLocation) throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            File[] files = sourceLocation.listFiles();
            for (File file : files) {
                InputStream in = new FileInputStream(file);
                OutputStream out = new FileOutputStream(targetLocation + "/" + file.getName());

                // Copy the bits from input stream to output stream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                file.delete();
            }
        }
    }

    public static void clearDirectory(File targetDirectory) throws IOException {

        if (targetDirectory.isDirectory()) {
            File[] files = targetDirectory.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
    }

    public static String getNetworkType(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isConnected())
            return "Internet Yok"; // not connected
        if (info.getType() == ConnectivityManager.TYPE_WIFI)
            return "WIFI";
        if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return "2G";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return "3G";
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return "4G";
                default:
                    return "?";
            }
        }
        return "?";
    }


}