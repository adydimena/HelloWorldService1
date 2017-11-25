package com.example.ady.helloworldservice;


import android.app.IntentService;

import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


/**
 * Created by Ady on 11/21/2017.
 */

public class MyService extends IntentService {
    MyDynamicReceiver myDynamicReceiver;


    public MyService() {
        super("TheThread");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        new DownloadImage().execute("http://1tvs492zptzq380hni2k8x8p-wpengine.netdna-ssl.com/wp-content/uploads/2015/10/deco-barcelona.jpg");

        File file = getApplicationContext().getFileStreamPath("my_image.jpeg");
        String picPath = file.getAbsolutePath();
        Toast.makeText(this,picPath,Toast.LENGTH_LONG).show();





        Intent broadcast = new Intent();
        broadcast.setAction("done");

        broadcast.putExtra("Data", picPath);
        sendBroadcast(broadcast);


    }

    

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("done");

        registerReceiver(myDynamicReceiver,intentFilter);

    }

    public void saveImage(Context context, Bitmap b, String imageName) {
        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, foStream);
            foStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 2, Something went wrong!");
            e.printStackTrace();
        }
    }
    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        private String TAG = "DownloadImage";
        private Bitmap downloadImageBitmap(String sUrl) {
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
                inputStream.close();
            } catch (Exception e) {
                Log.d(TAG, "Exception 1, Something went wrong!");
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImageBitmap(params[0]);
        }

        protected void onPostExecute(Bitmap result) {
            saveImage(getApplicationContext(), result, "my_image.png");
        }
    }
    public Bitmap loadImageBitmap(Context context, String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            fiStream    = context.openFileInput(imageName);
            bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 3, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }


}

