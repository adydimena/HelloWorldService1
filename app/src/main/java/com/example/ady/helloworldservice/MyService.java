package com.example.ady.helloworldservice;


import android.app.IntentService;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;


import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Ady on 11/21/2017.
 */

public class MyService extends IntentService {
    String Action = "action";

    public MyService() {
        super("TheThread");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String URL = "http://1tvs492zptzq380hni2k8x8p-wpengine.netdna-ssl.com/wp-content/uploads/2015/10/deco-barcelona.jpg";
        ResultReceiver resultReceiver = intent.getParcelableExtra("magic");

        DownloadImage downloadImage = new DownloadImage(URL,resultReceiver);
        if (intent != null) {
            String ACTION = intent.getAction();
            if(Action == ACTION)
            downloadImage.downloadPerforming();
        }



    }
}

