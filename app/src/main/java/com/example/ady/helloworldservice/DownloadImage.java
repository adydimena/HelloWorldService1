package com.example.ady.helloworldservice;


import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Ady on 11/21/2017.
 */

class DownloadImage {
    String URL;
    String Path;
    Bitmap bmap;

    Context context;
    ImageView image;
    ResultReceiver receiver;
    Bundle bundle = new Bundle();


    public DownloadImage(String URL, ResultReceiver resultReceiver) {
        this.URL = URL;
        this.receiver = resultReceiver;
    }

    public String getPath() {
        return Path;
    }
    public void setPath(String path) {
        Path = path;
    }

    public Bitmap getBmap() {
        return bmap;
    }

    public void downloadPerforming(){
        Request request;
        OkHttpClient client;
        Response response;
        client = new OkHttpClient();
        // downloading image
        try {
            request = new Request.Builder().url(URL).build();
            response = client.newCall(request).execute();
            InputStream out = response.body().byteStream();
            Bitmap bmap = BitmapFactory.decodeStream(out);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // saving image
        File HelloFile = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + "com.example.ady.helloworldservice"
                + "/Files");
        Path = HelloFile.getPath();
        setPath(HelloFile.getPath());

        if (! HelloFile.exists()){
            if (! HelloFile.mkdirs()){
                return;
            }
        }
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName="MI_"+ timeStamp +".jpg";
        mediaFile = new File(HelloFile.getPath() + File.separator + mImageName);


        if (mediaFile == null) {
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(mediaFile);
            bmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        bundle.putSerializable("magic",getPath());
        receiver.send(100,bundle);

    }
}













