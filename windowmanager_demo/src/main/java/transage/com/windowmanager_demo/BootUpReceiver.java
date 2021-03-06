package transage.com.windowmanager_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by dongrp on 2017/2/20.
 * 开机广播接收器
 */

public class BootUpReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Log.d("BootUpReceiver", "already_boot_up");
            //接收到开机广播后，开启PointService
            Intent intent1 = new Intent(context,PointService.class);
            context.startService(intent1);
        }
    }
}
