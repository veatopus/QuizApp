package com.example.quizapp.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

public class ConnectionLiveData extends LiveData<Boolean> {
    BroadcastReceiver networkReceiver;
    Context context;

    public ConnectionLiveData( Context context) {
        this.context = context;
        this.networkReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                postValue(isOnline(context));
            }
        };
    }

    protected void onActive() {
        super.onActive();
        this.context.registerReceiver((BroadcastReceiver)this.networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    protected void onInactive() {
        super.onInactive();
        try {
            this.context.unregisterReceiver((BroadcastReceiver)this.networkReceiver);
        } catch (Exception ignored) { }
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }

}
