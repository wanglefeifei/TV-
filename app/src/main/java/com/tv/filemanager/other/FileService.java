package com.tv.filemanager.other;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.IBinder;
import android.util.Log;

import com.tv.filemanager.utils.SettingUtil;

/**
 * 功能描述：文件服务
 * 开发状况：正在开发中
 */

public class FileService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("wanglf", "FileService onCreate: ");
        super.onCreate();
        registerUsbReceiver();
    }

    /**
     * 注册Usb插入广播
     */
    private void registerUsbReceiver() {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        registerReceiver(mUsbStateReceiver, filter);
    }

    /**
     * usb状态接收器
     */
    private final BroadcastReceiver mUsbStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            Log.d("wanglf", "onReceive: fileservice" +action);
            if(UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                SettingUtil.setUsbAttached(context, true);
            }
        }
    };
}
