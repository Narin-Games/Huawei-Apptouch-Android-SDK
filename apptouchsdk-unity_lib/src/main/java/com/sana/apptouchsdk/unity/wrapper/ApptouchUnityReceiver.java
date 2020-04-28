package com.sana.apptouchsdk.unity.wrapper;

import com.google.gson.Gson;
import com.sana.apptouchsdk.unity.ApptouchSDK;
import com.sana.apptouchsdk.unity.ApptouchSDKLogger;
import com.sdk.commplatform.entry.SkuDetail;


public class ApptouchUnityReceiver {
    public static void initSDK(String appId, String appKey) {
        ApptouchSDK.initSDK(appId, appKey);
    }

    public static void getSKUDetail() {
        ApptouchSDK.getSKUDetails();
    }

    public static void purchaseProduct(String skuDetailJson) {
        Gson gson = new Gson();
        ApptouchSDKLogger.logDebug("Received json from unity for purchasing: " + gson.fromJson(skuDetailJson, SkuDetail.class).productId);
        ApptouchSDK.purchaseProduct(gson.fromJson(skuDetailJson, SkuDetail.class));
    }

    public static void setDebug(boolean enabled) {
        ApptouchSDKLogger.DEBUG = enabled;
    }
}
