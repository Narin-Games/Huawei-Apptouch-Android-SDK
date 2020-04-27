package com.sana.apptouchsdk.unity;

import android.util.Log;

import com.sana.apptouchsdk.unity.wrapper.ApptouchUnitySender;
import com.sdk.commplatform.CallbackListener;
import com.sdk.commplatform.Commplatform;
import com.sdk.commplatform.ErrorCode;
import com.sdk.commplatform.entry.AppInfo;

public class ApptouchSDK extends ApptouchSDKBase {

    public static void initSDK(String appId, String appKey) {
        Commplatform.getInstance().onCreate(getCurrentActivity().getApplication().getBaseContext(), null);

        AppInfo appInfo = new AppInfo();
        appInfo.setAppId(appId);
        appInfo.setAppKey(appKey);
        appInfo.setCtx(getCurrentActivity());

        Commplatform.getInstance().Init(0, appInfo, new CallbackListener<Integer>() {
            @Override
            public void callback(int paramInt, Integer paramT) {
                if (paramInt == com.sdk.commplatform.ErrorCode.COM_PLATFORM_SUCCESS) {
                    Log.d("apptouch", "SDK Initialized Successfully");
                    ApptouchUnitySender.initCallback(ResultCode.SUCCESS);

                } else if (paramInt == com.sdk.commplatform.ErrorCode.COM_PLATFORM_ERROR_FORCE_CLOSE) {
                    //init failure
                    Log.d("apptouch", "SDK Failed Initialized");
                    ApptouchUnitySender.initCallback(ResultCode.FAILED_FORCE_CLOSE);

                } else if (paramInt == ErrorCode.COM_PLATFORM_ERROR_ONLINE_CHECK_FAILURE) {
                    //network not connect
                    Log.d("apptouch", "You're not connected!");
                    ApptouchUnitySender.initCallback((ResultCode.NOT_CONNECTED));
                }
            }
        });
    }

    public static void getSKUDetails() {

    }

    public static void purchaseProduct() {

    }
}
