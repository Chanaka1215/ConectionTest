package com.example.chanakafernando.conectiontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {

    private NetworkStateReceiver networkStateReceiver;

    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text =  (TextView) findViewById(R.id.text2);

        networkStateReceiver = NetworkStateReceiver.getInstanse();
        networkStateReceiver.addListner(this);


    }

    @Override
    public void onConnectivityChanged(boolean connected) {
        Toast.makeText(SecondActivity.this,connected+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        networkStateReceiver.removeListener(this);
    }
}
