package com.example.wanandroid.model.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class NetworkUtil {

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            Network[] allNetworks = mConnectivityManager.getAllNetworks();
            for (int i=0;i <= allNetworks.length;i++){
                NetworkInfo networkInfo = mConnectivityManager.getNetworkInfo(allNetworks[i]);
                if (networkInfo.isConnected()){
                    return true;
                }
            }
        }
        return false;
    }
}
