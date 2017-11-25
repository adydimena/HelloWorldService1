package com.example.ady.helloworldservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Ady on 11/24/2017.
 */

public class MyDynamicReceiver extends BroadcastReceiver {

    String path = "ola";



    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        switch (action){
            case "done":

                String data = intent.getStringExtra("data");
                Toast.makeText(context,data,Toast.LENGTH_LONG).show();

                break;

        }
    }
}