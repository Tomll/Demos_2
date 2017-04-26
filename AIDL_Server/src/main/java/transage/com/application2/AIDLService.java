package transage.com.application2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import transage.com.aidl.IMyAidlInterface;

/**
 * Created by dongrp on 2017/2/9.
 */

public class AIDLService extends Service {

    private static IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes() throws RemoteException {
            Log.d("dongrp","hahahahah");
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
