package com.example.ady.helloworldservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Ady on 11/21/2017.
 */

public class MyResultreceiver<t> extends ResultReceiver {
    private static final int RESULT_CODE_OK = 100 ;
    ResultReceiverCallBack mreceiver;
    public MyResultreceiver(Handler handler) {
        super(handler);
    }
    public void setReceiver(ResultReceiverCallBack receiver) {
        mreceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (mreceiver != null) {
            if(resultCode == 100)
            mreceiver.onSuccess(resultData.getSerializable("magic"));
        }
    }

    public interface ResultReceiverCallBack<T>{
        public void onSuccess(T data);
        public void onError(Exception exception);
    }
}
