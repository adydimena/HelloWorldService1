package com.example.ady.helloworldservice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyService.class);
        intent.setAction("action");
        startService(intent);


    }

    private static class getback implements MyResultreceiver.ResultReceiverCallBack {


        private static final String TAG = "onSucess" ;

        @Override
        public void onSuccess(Object data) {
            Log.d(TAG, "onSuccess: SUCESSSOSOSOSOSOSOSOSOSOS");



        }

        @Override
        public void onError(Exception exception) {

        }
    }





}
