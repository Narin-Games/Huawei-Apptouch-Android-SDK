package com.sana.apptouchsdk.unity.wrapper;
import com.sana.apptouchsdk.unity.ApptouchSDK;


public class ApptouchUnityReceiver {
    public static void initSDK(String appId, String appKey) {
        ApptouchSDK.initSDK(appId, appKey);
    }

    public static void getSKUDetail() {
        ApptouchSDK.getSKUDetails();
    }

    public static void purchaseProduct(String skuDetailJson) {
        ApptouchSDK.getSKUDetails();
    }
}
