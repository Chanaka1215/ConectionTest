package com.example.chanakafernando.conectiontest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chanaka Fernando on 10/4/2017.
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    private static NetworkStateReceiver networkStateReceiver;
    protected Set<NetworkStateReceiverListener> listners;
    protected Boolean connected;

    private NetworkStateReceiver(){
        listners = new HashSet<NetworkStateReceiverListener>();
        connected = null;
    }

    public static synchronized NetworkStateReceiver getInstanse(){
        if(networkStateReceiver == null){
            networkStateReceiver = new NetworkStateReceiver();
        }
        return networkStateReceiver;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent == null || intent.getExtras() == null){
            return;
        }
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        notyfyStateToAll();

    }

    private void notyfyStateToAll(){
        for(NetworkStateReceiverListener listner : listners){
            notifyState(listner);
        }
    }


    private void notifyState(NetworkStateReceiverListener listner){
        if(connected == null || listner == null){
            return;
        } else {
            listner.onConnectivityChanged(connected);
        }
    }

    public void removeListener(NetworkStateReceiverListener l){
        listners.remove(l);
    }

    public void addListner(NetworkStateReceiverListener l){
        listners.add(l);
    }

    public interface NetworkStateReceiverListener {
//        void networkAvailable();
//        void networkUnavailable();
        void onConnectivityChanged(boolean connected);
    }


}
