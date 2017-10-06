package com.example.chanakafernando.conectiontest;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {
    private NetworkStateReceiver networkStateReceiver;
    private String name =null;
    private Button click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkStateReceiver = NetworkStateReceiver.getInstanse();
        networkStateReceiver.addListner(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        click = (Button) findViewById(R.id.btn_check);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(newIntent);

            }
        });
    }


//    @Override
//    public void networkAvailable() {
//        Toast.makeText(MainActivity.this,"Connected",Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void networkUnavailable() {
//        Toast.makeText(MainActivity.this,"DisConnected",Toast.LENGTH_SHORT).show();
//
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);
    }

    @Override
    public void onConnectivityChanged(boolean connected) {
        name = "OnConnectivity";
        if(connected){
            name = "true";
            Toast.makeText(MainActivity.this,"Connected",Toast.LENGTH_SHORT).show();
        }else {
            name = "32222";
            Toast.makeText(MainActivity.this,"Disconnected",Toast.LENGTH_SHORT).show();
        }
    }
}
